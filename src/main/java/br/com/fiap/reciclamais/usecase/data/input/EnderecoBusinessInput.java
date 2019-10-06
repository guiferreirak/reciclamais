package br.com.fiap.reciclamais.usecase.data.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoBusinessInput {

    private String cep;
    private String rua;
    private Integer numero;
    private String cidade;
    private String estado;
}
