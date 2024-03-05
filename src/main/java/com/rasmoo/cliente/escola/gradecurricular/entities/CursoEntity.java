package com.rasmoo.cliente.escola.gradecurricular.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_curso")
@Data
@NoArgsConstructor
public class CursoEntity implements Serializable {

    private static final long serialVersionUID = 1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "descricao")
    private String name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "codigo")
    private String code;

    // One - refere-se ao curso  To   Many -> Referente lista de Materias
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private List<MateriaEntity> materias;


}
