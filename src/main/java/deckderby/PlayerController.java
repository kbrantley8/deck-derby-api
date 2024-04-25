package deckderby;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import deckderby.core.PlayerDTO;
import deckderby.core.PlayerService;
import deckderby.dto.PlayerResponseDTO;
import jakarta.validation.Valid;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    private static final Logger LOG = LogManager.getLogger();

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @PostMapping("/player")
    ResponseEntity<PlayerResponseDTO> createPlayer(@Valid @RequestBody PlayerDTO player) {
        LOG.info("Received request to create player: {}", player);

        var createdPlayer = playerService.createPlayer(player);

        LOG.info("Player successfully created: {}", createdPlayer);
        return ResponseEntity.ok(createdPlayer);
    }
}
