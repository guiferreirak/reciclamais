package br.com.fiap.reciclamais.usecase.data.output;

import br.com.fiap.reciclamais.utils.enums.PerfilUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioBusinessOutput {

    private String uuid;
    private String nome;
    private String email;
    private String cpf;
    private PerfilUsuarioEnum perfil;
    private String senha;
    private EnderecoBusinessOutput endereco;
    private PontuacaoBusinessOutput pontuacao;

}
