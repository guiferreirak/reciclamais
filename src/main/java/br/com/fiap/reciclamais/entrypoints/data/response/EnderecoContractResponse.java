package br.com.fiap.reciclamais.entrypoints.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoContractResponse {

    private String cep;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
}
