package br.com.fiap.reciclamais.usecase.data.input.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginBusinessInput {

    private String cpf;
    private String senha;
}
