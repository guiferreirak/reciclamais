package br.com.fiap.reciclamais.entrypoints.data.request.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginContractRequest {

    private String cpf;
    private String senha;
}
