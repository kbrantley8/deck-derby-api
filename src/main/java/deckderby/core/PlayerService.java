package deckderby.core;

import org.springframework.stereotype.Service;

import deckderby.dto.PlayerResponseDTO;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class PlayerService {

    private final IPlayerRepository playerRepository;

    public PlayerService(IPlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
    

    public PlayerResponseDTO createPlayer(PlayerDTO playerDTO) {
        Player player = new Player();
        
        String salt = this.createSalt();
        player.setUsername(playerDTO.getUsername());
        player.setEmail(playerDTO.getEmail());
        player.setPassword_hash(hashPassword(playerDTO.getPassword(), salt));
        player.setPassword_salt(salt);

        Player savedPlayer = this.playerRepository.save(player);

        PlayerResponseDTO playerResponseDTO = new PlayerResponseDTO();
        playerResponseDTO.setUserId(savedPlayer.getUserId());
        playerResponseDTO.setUsername(savedPlayer.getUsername());

        return playerResponseDTO;
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
