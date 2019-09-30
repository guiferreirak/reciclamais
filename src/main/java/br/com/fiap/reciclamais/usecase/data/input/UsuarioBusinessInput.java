package br.com.fiap.reciclamais.usecase.data.input;

import br.com.fiap.reciclamais.utils.enums.PerfilUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioBusinessInput {

    private String uuid;
    private String nome;
    private String email;
    private String cpf;
    private PerfilUsuarioEnum perfil;
    private String senha;
    private EnderecoBusinessInput endereco;
    private PontuacaoBusinessInput pontuacao;

}
