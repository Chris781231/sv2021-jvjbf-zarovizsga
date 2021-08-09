package org.training360.finalexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training360.finalexam.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
