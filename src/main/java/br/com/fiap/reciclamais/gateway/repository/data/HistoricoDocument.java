package br.com.fiap.reciclamais.gateway.repository.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoDocument {

    private LocalDate data;
    private Double ponto;
}
