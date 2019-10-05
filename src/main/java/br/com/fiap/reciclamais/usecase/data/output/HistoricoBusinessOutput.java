package br.com.fiap.reciclamais.usecase.data.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoBusinessOutput {

    private LocalDate data;
    private Integer ponto;
}
