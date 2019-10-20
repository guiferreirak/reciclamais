package br.com.fiap.reciclamais.usecase.data.input.atualiza;

import br.com.fiap.reciclamais.usecase.data.input.EnderecoBusinessInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioAtualizaBusinessInput {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private EnderecoBusinessInput endereco;
}
