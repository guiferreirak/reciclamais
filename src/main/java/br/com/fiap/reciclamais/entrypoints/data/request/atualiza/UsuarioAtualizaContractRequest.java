package br.com.fiap.reciclamais.entrypoints.data.request.atualiza;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UsuarioAtualizaContractRequest {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String cep;
    private String rua;
    private Integer numero;
    private String bairro;
    private String estado;
    private String cidade;
}
