package br.com.fiap.reciclamais.usecase.data.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoBusinessOutput {

    private String cep;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
}
