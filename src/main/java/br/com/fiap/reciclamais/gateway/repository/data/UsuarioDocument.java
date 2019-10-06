package br.com.fiap.reciclamais.gateway.repository.data;

import br.com.fiap.reciclamais.utils.enums.PerfilUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Usuario")
public class UsuarioDocument {

    @Id
    private String uuid;
    private String nome;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String cpf;
    private PerfilUsuarioEnum perfil;
    private String senha;
    private EnderecoDocument endereco;
    private PontuacaoDocument pontuacao;

}
