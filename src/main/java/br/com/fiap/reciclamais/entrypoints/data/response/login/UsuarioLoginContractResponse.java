package br.com.fiap.reciclamais.entrypoints.data.response.login;

import br.com.fiap.reciclamais.utils.enums.PerfilUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginContractResponse {

    private String nome;
    private PerfilUsuarioEnum perfil;
}
