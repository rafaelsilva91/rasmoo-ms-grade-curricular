package com.rasmoo.cliente.escola.gradecurricular.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tb_materia")
@Data
@NoArgsConstructor
public class Materia implements Serializable {

    private static final long serialVersionUID = 1;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "descricao")
    private String name;

    @Column(name = "hrs")
    private int hours;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "codigo")
    private String code;

    @Column(name = "frequencia")
    private int frequency;




}
