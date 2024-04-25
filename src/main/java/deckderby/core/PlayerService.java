package deckderby.core;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    

    public PlayerDTO createPlayer(PlayerDTO player) {
        // var product = new Player();
        // product.setName(productDTO.getName());
        // product.setPrice(productDTO.getPrice());

        // var savedProduct = this.productRepository.save(product);

        // return savedProduct.asDto();
        return player;
    }
}
