package br.com.fiap.reciclamais.entrypoints.data.request.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginContractRequest {

    @NotNull(message = "Campo cpf nao pode ser nulo")
    private String cpf;

    @NotNull(message = "Campo senha nao pode ser nulo")
    private String senha;
}
