package br.com.fiap.reciclamais.entrypoints.data.request.cadastro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCadastroContractRequest {

    @NotNull(message = "Campo nome nao pode ser nulo")
    private String nome;

    @NotNull(message = "Campo email nao pode ser nulo")
    private String email;

    @NotNull(message = "Campo senha nao pode ser nulo")
    private String senha;

    @NotNull(message = "Campo cpf nao pode ser nulo")
    private String cpf;

    @NotNull(message = "Campo cep nao pode ser nulo")
    private String cep;

    @NotNull(message = "Campo rua nao pode ser nulo")
    private String rua;

    @NotNull(message = "Campo numero nao pode ser nulo")
    private Integer numero;

    @NotNull(message = "Campo bairro nao pode ser nulo")
    private String bairro;

    @NotNull(message = "Campo estado nao pode ser nulo")
    private String estado;

    @NotNull(message = "Campo cidade nao pode ser nulo")
    private String cidade;
}
