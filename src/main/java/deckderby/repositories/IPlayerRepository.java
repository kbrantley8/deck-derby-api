package deckderby.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import deckderby.model.entities.Player;

public interface IPlayerRepository extends JpaRepository<Player, Long> {
    Player findByUsername(String username);
}
