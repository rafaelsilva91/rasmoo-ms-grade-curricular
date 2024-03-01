package com.rasmoo.cliente.escola.gradecurricular.dto;

import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MateriaDto {

    public MateriaDto(MateriaEntity materiaEntity) {
        this.id = materiaEntity.getId();
        this.name = materiaEntity.getName();
        this.hours = materiaEntity.getHours();
        this.code = materiaEntity.getCode();
        this.frequency = materiaEntity.getFrequency();
    }

    private Long id;

    @NotBlank(message = "Informe o nome da MATERIA.")
    private String name;

    @Min(value = 34, message = "Permitido o mínimo de 34 horas por matéria")
    @Max(value = 120, message = "Permitido o máximo de 120 horas por matéria")
    private int hours;

    @NotBlank(message = "Informe o CODIGO da MATERIA.")
    private String code;

    @Min(value = 1, message = "Permitido o mínimo de 1 vez ao ano.")
    @Max(value = 2, message = "Permitido o máximo de 2 vezes ao ano")
    private int frequency;

}
