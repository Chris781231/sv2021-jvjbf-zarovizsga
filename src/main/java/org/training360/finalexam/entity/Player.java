package org.training360.finalexam.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private PositionType positionType;

    @ManyToOne
    @JsonBackReference
    private Team team;

    public Player(String name, LocalDate birthDate, PositionType positionType) {
        this.name = name;
        this.birthDate = birthDate;
        this.positionType = positionType;
    }
}
