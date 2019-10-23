package br.com.fiap.reciclamais.entrypoints;

import br.com.fiap.reciclamais.entrypoints.converter.PontuacaoHttpConverter;
import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.RegistroPontuacaoContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.pontuacao.TrocaPontuacaoContractRequest;
import br.com.fiap.reciclamais.usecase.PontuacaoUseCase;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.RegistroPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.pontuacao.TrocaPontuacaoBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.HistoricoBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.PontuacaoUsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.pontuacao.RegistroPontuacaoBusinessOutput;
import br.com.fiap.reciclamais.utils.converter.HttpConverterWrapper;
import br.com.fiap.reciclamais.utils.data.HttpContractResponseWrapper;
import br.com.fiap.reciclamais.utils.exception.PontuacaoInsuficienteException;
import br.com.fiap.reciclamais.utils.exception.UsuarioInexistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("pontuacao")
public class PontuacaoController {

    private final HttpConverterWrapper httpConverter;
    private final PontuacaoHttpConverter pontuacaoConverter;
    private final PontuacaoUseCase pontuacaoUseCase;

    @GetMapping("{id}")
    public ResponseEntity<HttpContractResponseWrapper> getPontuacao(@PathVariable("id") String cpf) {

        try {

            PontuacaoUsuarioBusinessOutput pontuacaoUsuario = pontuacaoUseCase.getPontuacao(cpf);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(pontuacaoUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @PostMapping("salvar")
    public ResponseEntity<HttpContractResponseWrapper> registrarPontuacoes(@RequestBody List<RegistroPontuacaoContractRequest> pontuacoesRequest) {

        try {
            List<RegistroPontuacaoBusinessInput> pontuacoesBusiness = pontuacaoConverter.toListRegistroPontuacaoBusinessInput(pontuacoesRequest);

            RegistroPontuacaoBusinessOutput pontuacao = pontuacaoUseCase.registrarPontuacoes(pontuacoesBusiness);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(pontuacao));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @GetMapping("{id}/historico")
    public ResponseEntity<HttpContractResponseWrapper> getHistoricoPontuacao(@PathVariable("id") String cpf) {

        try {

            List<HistoricoBusinessOutput> historicoPontuacao = pontuacaoUseCase.getHistoricoPontuacao(cpf);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(historicoPontuacao));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @PostMapping("trocar")
    public ResponseEntity<HttpContractResponseWrapper> trocarPontuacao(@RequestBody TrocaPontuacaoContractRequest request) throws UsuarioInexistenteException, PontuacaoInsuficienteException {

        try {
            TrocaPontuacaoBusinessInput pontuacaoBusiness = pontuacaoConverter.toTrocaPontuacaoBusinessInput(request);

            pontuacaoUseCase.trocarPontuacao(pontuacaoBusiness);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess("Troca efetuada com sucesso"));
        } catch (UsuarioInexistenteException e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        } catch (PontuacaoInsuficienteException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

}
