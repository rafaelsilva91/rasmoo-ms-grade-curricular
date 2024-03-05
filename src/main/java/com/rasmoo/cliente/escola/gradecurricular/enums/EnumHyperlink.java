package com.rasmoo.cliente.escola.gradecurricular.enums;

import lombok.Getter;

@Getter
public enum EnumHyperlink {

    CADASTRAR("INSERT"),
    ATUALIZAR("UPDATE"),
    EXCLUIR("DELETE"),
    LISTAR("GET_ALL"),
    CONSULTAR("GET");


    private final String valor;

    EnumHyperlink(String valor) {
        this.valor = valor;
    }
}
