package br.com.fiap.reciclamais.utils.data;

import br.com.fiap.reciclamais.utils.enums.StatusResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpContractResponseWrapper<T> {

    private T results;
    private StatusResponseEnum status;
    private String descricao;
}
