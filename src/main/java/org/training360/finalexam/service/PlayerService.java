package org.training360.finalexam.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.training360.finalexam.command.CreatePlayerCommand;
import org.training360.finalexam.dto.PlayerDTO;
import org.training360.finalexam.entity.Player;
import org.training360.finalexam.exception.EntityNotFoundException;
import org.training360.finalexam.repository.PlayerRepository;

import java.lang.reflect.Type;
import java.util.List;

@AllArgsConstructor
@Service
public class PlayerService {

    private ModelMapper modelMapper;

    private PlayerRepository playerRepo;

    public List<PlayerDTO> listAll() {
        List<Player> players = playerRepo.findAll();
        Type targetType = new TypeToken<List<PlayerDTO>>(){}.getType();
        return modelMapper.map(players, targetType);
    }

    public PlayerDTO save(CreatePlayerCommand command) {
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPositionType());
        playerRepo.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }

    public void deleteById(long id) {
        Player player = playerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("players"));
        playerRepo.delete(player);
    }
}
