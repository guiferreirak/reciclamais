package br.com.fiap.reciclamais.entrypoints;

import br.com.fiap.reciclamais.entrypoints.converter.PontuacaoHttpConverter;
import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.RegistroPontuacaoContractRequest;
import br.com.fiap.reciclamais.usecase.UsuarioUseCase;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.PontuacaoUsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;
import br.com.fiap.reciclamais.utils.converter.HttpConverterWrapper;
import br.com.fiap.reciclamais.utils.data.HttpContractResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("pontuacao")
public class PontuacaoController {

    private final UsuarioUseCase usuarioUseCase;
    private final HttpConverterWrapper httpConverter;
    private final PontuacaoHttpConverter pontuacaoConverter;

    @GetMapping("{id}")
    public ResponseEntity<HttpContractResponseWrapper> getPontuacao(@PathVariable("id") String cpf) {

        try {

            PontuacaoUsuarioBusinessOutput pontuacaoUsuario = usuarioUseCase.getPontuacao(cpf);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(pontuacaoUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @PostMapping("salvar")
    public ResponseEntity<HttpContractResponseWrapper> registrarPontuacoes(@RequestBody List<RegistroPontuacaoContractRequest> pontuacoesRequest) {

        try {
            List<RegistroPontuacaoBusinessInput> pontuacoesBusiness = pontuacaoConverter.toListRegistroPontuacaoBusinessInput(pontuacoesRequest);

            RegistroPontuacaoBusinessOutput pontuacao = usuarioUseCase.registrarPontuacoes(pontuacoesBusiness);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(pontuacao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @GetMapping("{id}/historico")
    public ResponseEntity<HttpContractResponseWrapper> getHistoricoPontuacao(@PathVariable("id") String cpf) {

        try {

            List<HistoricoBusinessOutput> historicoPontuacao = usuarioUseCase.getHistoricoPontuacao(cpf);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(historicoPontuacao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

}
