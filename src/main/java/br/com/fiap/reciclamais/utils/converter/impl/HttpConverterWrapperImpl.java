package br.com.fiap.reciclamais.utils.converter.impl;

import br.com.fiap.reciclamais.utils.converter.HttpConverterWrapper;
import br.com.fiap.reciclamais.utils.data.HttpContractResponseWrapper;
import br.com.fiap.reciclamais.utils.enums.StatusResponseEnum;
import org.springframework.stereotype.Component;

@Component
public class HttpConverterWrapperImpl implements HttpConverterWrapper {

    @Override
    public HttpContractResponseWrapper toHttpSuccess(Object source) {
        return HttpContractResponseWrapper
                .builder()
                .results(source)
                .status(StatusResponseEnum.SUCESSO)
                .build();
    }

    @Override
    public HttpContractResponseWrapper toHttpException(String message) {
        return HttpContractResponseWrapper
                .builder()
                .status(StatusResponseEnum.ERRO)
                .descricao(message)
                .build();
    }
}
