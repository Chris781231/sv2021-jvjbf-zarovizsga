package org.training360.finalexam.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.command.CreatePlayerCommand;
import org.training360.finalexam.command.CreateTeamCommand;
import org.training360.finalexam.command.UpdateWithExistingPlayerCommand;
import org.training360.finalexam.dto.TeamDTO;
import org.training360.finalexam.service.TeamService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private TeamService teamService;

    @GetMapping
    public List<TeamDTO> listAll() {
        return teamService.listAll();
    }

    @PostMapping
    public TeamDTO save(@RequestBody @Valid CreateTeamCommand command) {
        return teamService.save(command);
    }

    @PostMapping("/{id}/players")
    public TeamDTO addNewPlayerToTeamById(@PathVariable long id, @RequestBody @Valid CreatePlayerCommand command) {
        return teamService.addNewPlayerToTeamById(id, command);
    }

    @PutMapping("/{id}/players")
    public TeamDTO addExistingPlayerToTeamById(@PathVariable long id, @RequestBody @Valid UpdateWithExistingPlayerCommand command) {
        return teamService.addExistingPlayerToTeamById(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        teamService.deleteById(id);
    }
}
