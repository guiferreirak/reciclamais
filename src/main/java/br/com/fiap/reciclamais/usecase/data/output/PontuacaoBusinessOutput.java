package br.com.fiap.reciclamais.usecase.data.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PontuacaoBusinessOutput {


    private Double reciclagem;
    private Integer total;
    private Double percentual;
    private List<HistoricoBusinessOutput> historico;
}
