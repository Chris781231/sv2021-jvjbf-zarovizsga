package org.training360.finalexam.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.finalexam.command.CreatePlayerCommand;
import org.training360.finalexam.command.CreateTeamCommand;
import org.training360.finalexam.command.UpdateWithExistingPlayerCommand;
import org.training360.finalexam.dto.PlayerDTO;
import org.training360.finalexam.dto.TeamDTO;
import org.training360.finalexam.entity.Player;
import org.training360.finalexam.entity.Team;
import org.training360.finalexam.exception.EntityNotFoundException;
import org.training360.finalexam.repository.PlayerRepository;
import org.training360.finalexam.repository.TeamRepository;

import java.lang.reflect.Type;
import java.util.List;

@AllArgsConstructor
@Service
public class TeamService {

    private ModelMapper modelMapper;

    private TeamRepository teamRepo;

    private PlayerRepository playerRepo;

    public List<TeamDTO> listAll() {
        List<Team> teams = teamRepo.findAll();
        Type targetType = new TypeToken<List<TeamDTO>>(){}.getType();
        return modelMapper.map(teams, targetType);
    }

    public TeamDTO save(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepo.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    public void deleteById(long id) {
        Team team = teamRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        teamRepo.delete(team);
    }

    @Transactional
    public TeamDTO addNewPlayerToTeamById(long id, CreatePlayerCommand command) {
        Team team = teamRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPositionType());
        team.addPlayer(player);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO addExistingPlayerToTeamById(long id, UpdateWithExistingPlayerCommand command) {
        Team team  = teamRepo.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Player player = playerRepo.findById(command.getPlayerId())
                .orElseThrow(() -> new IllegalArgumentException("Nincs ilyen id-jű játékos"));
        if (isValidTransfer(player, team)) {
            team.addPlayer(player);
        } else {
            throw new IllegalArgumentException("A játékos leigazolása sikertelen.");
        }
        return modelMapper.map(team, TeamDTO.class);
    }

    private boolean isValidTransfer(Player player, Team team) {
        long count = team.getPlayers().stream()
                .filter(p -> p.getPositionType().equals(player.getPositionType()))
                .count();
        return count < 2 && player.getTeam() == null;
    }
}
