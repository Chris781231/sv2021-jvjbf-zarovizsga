package org.training360.finalexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training360.finalexam.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
