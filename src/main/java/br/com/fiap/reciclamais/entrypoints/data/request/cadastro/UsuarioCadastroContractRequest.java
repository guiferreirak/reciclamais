package br.com.fiap.reciclamais.entrypoints.data.request.cadastro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCadastroContractRequest {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String cep;
    private String rua;
    private Integer numero;
    private String estado;
    private String cidade;
}
