package br.com.fiap.reciclamais.entrypoints.converter;

import br.com.fiap.reciclamais.usecase.data.input.EnderecoBusinessInput;

public interface EnderecoHttpConverter {

    EnderecoBusinessInput parseEndereco(String cep, String rua, Integer numero, String estado, String cidade);
}
