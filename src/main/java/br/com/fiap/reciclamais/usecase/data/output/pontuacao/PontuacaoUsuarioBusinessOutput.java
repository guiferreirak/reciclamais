package br.com.fiap.reciclamais.usecase.data.output.pontuacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PontuacaoUsuarioBusinessOutput {

    private String nome;
    private String cpf;
    private Integer pontuacaoTotal;
    private Integer reciclagemTotal;
}
