package br.com.fiap.reciclamais.entrypoints.data.request.pontuacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrocaPontuacaoContractRequest {

    private String cpf;
    private Integer voucher;
}
