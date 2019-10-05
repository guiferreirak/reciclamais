package br.com.fiap.reciclamais.gateway.repository.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PontuacaoDocument {


    private Double reciclagem;
    private Integer total;
    private Double percentual;
    private List<HistoricoDocument> historico;
}
