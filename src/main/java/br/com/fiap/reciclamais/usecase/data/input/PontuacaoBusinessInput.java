package br.com.fiap.reciclamais.usecase.data.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PontuacaoBusinessInput {


    private Double reciclagem;
    private Integer total;
    private Double percentual;
    private List<HistoricoBusinessInput> historico;
}
