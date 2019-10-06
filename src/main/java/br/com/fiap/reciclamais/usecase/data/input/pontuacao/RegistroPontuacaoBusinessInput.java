package br.com.fiap.reciclamais.usecase.data.input.pontuacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroPontuacaoBusinessInput {

    private String uuid;
    private LocalDateTime data;
}
