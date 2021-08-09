package org.training360.finalexam.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.entity.PositionType;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlayerCommand {

    @NotBlank
    private String name;

    private LocalDate birthDate;

    private PositionType positionType;
}
