package br.com.fiap.reciclamais.entrypoints.converter;

import br.com.fiap.reciclamais.entrypoints.data.response.EnderecoContractResponse;
import br.com.fiap.reciclamais.usecase.data.input.EnderecoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.EnderecoBusinessOutput;

public interface EnderecoHttpConverter {

    EnderecoBusinessInput parseEndereco(String cep, String rua, Integer numero, String estado, String cidade);

    EnderecoContractResponse parseEndereco(EnderecoBusinessOutput endereco);
}
