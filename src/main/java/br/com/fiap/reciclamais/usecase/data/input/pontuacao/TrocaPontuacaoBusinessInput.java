package br.com.fiap.reciclamais.usecase.data.input.pontuacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrocaPontuacaoBusinessInput {

    private String cpf;
    private Integer voucher;
}
