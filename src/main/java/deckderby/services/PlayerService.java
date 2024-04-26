package deckderby.services;

import org.springframework.stereotype.Service;

import deckderby.model.IPlayerResponse;
import deckderby.model.PlayerErrorResponse;
import deckderby.model.dto.PlayerLoginRequestDTO;
import deckderby.model.dto.PlayerRegisterRequestDTO;
import deckderby.model.entities.Player;
import deckderby.model.dto.PlayerAuthResponseDTO;
import deckderby.model.dto.PlayerBaseResponseDTO;
import deckderby.repositories.IPlayerRepository;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class PlayerService {

    private final IPlayerRepository playerRepository;

    public PlayerService(IPlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
    

    public IPlayerResponse createPlayer(PlayerRegisterRequestDTO playerDTO) {
        Player player = new Player();
        
        String salt = this.createSalt();
        player.setUsername(playerDTO.getUsername());
        player.setEmail(playerDTO.getEmail());
        player.setPassword_hash(hashPassword(playerDTO.getPassword(), salt));
        player.setPassword_salt(salt);

        Player savedPlayer = this.playerRepository.save(player);

        PlayerAuthResponseDTO playerResponseDTO = new PlayerAuthResponseDTO();
        playerResponseDTO.setUserId(savedPlayer.getUserId());
        playerResponseDTO.setUsername(savedPlayer.getUsername());

        return playerResponseDTO;
    }

    public IPlayerResponse loginPlayer(PlayerLoginRequestDTO playerDTO) {
        Player existingPlayer = this.playerRepository.findByUsername(playerDTO.getUsername());

        String providedPassword = playerDTO.getPassword();
        String hashedPassword = existingPlayer.getPassword_hash();
        
        if (this.verifyPassword(providedPassword, hashedPassword)) {
            PlayerAuthResponseDTO playerResponseDTO = new PlayerAuthResponseDTO();
            playerResponseDTO.setUserId(existingPlayer.getUserId());
            playerResponseDTO.setUsername(existingPlayer.getUsername());
            return playerResponseDTO;
        } else {
            PlayerErrorResponse playerErrorResponse = new PlayerErrorResponse();
            playerErrorResponse.setMessage("The provided username/password was not correct.");
            return playerErrorResponse;
        }
    }

    public IPlayerResponse getPlayer(String username) {
        Player existingPlayer = this.playerRepository.findByUsername(username);

        PlayerBaseResponseDTO playerBaseResponseDTO = new PlayerBaseResponseDTO();
        playerBaseResponseDTO.setUserId(existingPlayer.getUserId());
        playerBaseResponseDTO.setUsername(existingPlayer.getUsername());
        playerBaseResponseDTO.setTotal_winnings(existingPlayer.getTotal_winnings());

        return playerBaseResponseDTO;
    }

    private String createSalt() {
        return BCrypt.gensalt();
    }

    private String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    private boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
