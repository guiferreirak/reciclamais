package br.com.fiap.reciclamais.usecase.data.output.pontuacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroPontuacaoBusinessOutput {

    private List<String> usuarios;
    private String descricao;
}
