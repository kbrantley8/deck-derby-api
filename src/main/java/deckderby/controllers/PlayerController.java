package deckderby.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import deckderby.model.IPlayerResponse;
import deckderby.model.dto.PlayerLoginRequestDTO;
import deckderby.model.dto.PlayerRegisterRequestDTO;
import deckderby.services.PlayerService;
import jakarta.validation.Valid;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    private static final Logger LOG = LogManager.getLogger();

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @PostMapping("/register")
    ResponseEntity<IPlayerResponse> register(@Valid @RequestBody PlayerRegisterRequestDTO player) {
        LOG.info("Received request to create player: {}", player);

        var createdPlayer = playerService.createPlayer(player);

        LOG.info("Player successfully created: {}", createdPlayer);
        return ResponseEntity.ok(createdPlayer);
    }

    @PostMapping("/login")
    ResponseEntity<IPlayerResponse> login(@Valid @RequestBody PlayerLoginRequestDTO player) {
        LOG.info("Received request to login player: {}", player);

        var loggedInPlayer = playerService.loginPlayer(player);

        LOG.info("Player logged in successfully: {}", loggedInPlayer);
        return ResponseEntity.ok(loggedInPlayer);
    }

    @GetMapping("/player/{username}")
    ResponseEntity<IPlayerResponse> getPlayer(@PathVariable String username) {
        LOG.info("Received request to get player: {}", username);

        var loggedInPlayer = playerService.getPlayer(username);

        LOG.info("Player: {}", loggedInPlayer);
        return ResponseEntity.ok(loggedInPlayer);
    }
}
