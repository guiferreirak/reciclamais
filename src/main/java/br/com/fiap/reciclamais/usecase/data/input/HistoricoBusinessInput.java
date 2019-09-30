package br.com.fiap.reciclamais.usecase.data.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoBusinessInput {

    private LocalDate data;
    private Double ponto;
}
