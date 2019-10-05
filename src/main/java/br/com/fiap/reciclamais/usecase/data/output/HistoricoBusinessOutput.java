package br.com.fiap.reciclamais.usecase.data.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoBusinessOutput {

    private LocalDateTime data;
    private Integer ponto;
}
