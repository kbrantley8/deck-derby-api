package deckderby;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import deckderby.core.PlayerDTO;
import deckderby.core.PlayerService;

import javax.validation.Valid;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    private static final Logger LOG = LogManager.getLogger();

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @PostMapping("/player")
    ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO player) {
        LOG.info("Received message: {}", player);

        var createdPlayer = playerService.createPlayer(player);

        return ResponseEntity.ok(createdPlayer);
    }
}
