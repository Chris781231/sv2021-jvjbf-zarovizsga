package org.training360.finalexam.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.command.CreatePlayerCommand;
import org.training360.finalexam.dto.PlayerDTO;
import org.training360.finalexam.service.PlayerService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private PlayerService playerService;

    @GetMapping
    public List<PlayerDTO> listAll() {
        return playerService.listAll();
    }

    @PostMapping
    public PlayerDTO save(@RequestBody @Valid CreatePlayerCommand command) {
        return playerService.save(command);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        playerService.deleteById(id);
    }
}
