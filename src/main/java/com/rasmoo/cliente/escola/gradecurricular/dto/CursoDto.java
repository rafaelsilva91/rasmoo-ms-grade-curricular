package com.rasmoo.cliente.escola.gradecurricular.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rasmoo.cliente.escola.gradecurricular.entities.CursoEntity;
import com.rasmoo.cliente.escola.gradecurricular.entities.MateriaEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CursoDto extends RepresentationModel<CursoDto> {

    private static final long serialVersionUID = 1;

    public CursoDto(CursoEntity cursoEntity) {
        this.id = cursoEntity.getId();
        this.name = cursoEntity.getName();
        this.code = cursoEntity.getCode();
    }

    private Long id;

    @NotBlank(message = "o nome do curso deve ser preenchido")
    @Size(min=10, max = 30)
    private String name;

    @NotBlank(message = "o código do curso deve ser preenchido")
    @Size(min=10, max = 30)
    private String code;

    private List<Long> materias;

}
