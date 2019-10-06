package br.com.fiap.reciclamais.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PerfilUsuarioEnum {

    CLIENTE(1),
    FUNCIONARIO(2),
    ADMINISTRADOR(3);

    private Integer codigo;

}
