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
public class UsuarioLoginBusinessOutput {

    private String nome;
    private PerfilUsuarioEnum perfil;
}
