package br.com.fiap.reciclamais.utils.converter;

import br.com.fiap.reciclamais.utils.data.HttpContractResponseWrapper;

public interface HttpConverterWrapper {

    HttpContractResponseWrapper toHttpSuccess(Object source);

    HttpContractResponseWrapper toHttpException(String e);
}
