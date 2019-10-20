package br.com.fiap.reciclamais.entrypoints;

import br.com.fiap.reciclamais.entrypoints.converter.UsuarioHttpConverter;
import br.com.fiap.reciclamais.entrypoints.data.request.atualiza.UsuarioAtualizaContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.cadastro.UsuarioCadastroContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.request.login.UsuarioLoginContractRequest;
import br.com.fiap.reciclamais.entrypoints.data.response.UsuarioContractResponse;
import br.com.fiap.reciclamais.usecase.UsuarioUseCase;
import br.com.fiap.reciclamais.usecase.data.input.UsuarioBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.atualiza.UsuarioAtualizaBusinessInput;
import br.com.fiap.reciclamais.usecase.data.input.login.UsuarioLoginBusinessInput;
import br.com.fiap.reciclamais.usecase.data.output.UsuarioBusinessOutput;
import br.com.fiap.reciclamais.usecase.data.output.login.UsuarioLoginBusinessOutput;
import br.com.fiap.reciclamais.utils.converter.HttpConverterWrapper;
import br.com.fiap.reciclamais.utils.data.HttpContractResponseWrapper;
import br.com.fiap.reciclamais.utils.exception.LoginInvalidoException;
import br.com.fiap.reciclamais.utils.exception.UsuarioExistenteException;
import br.com.fiap.reciclamais.utils.exception.UsuarioInexistenteException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioUseCase usuarioUseCase;
    private final UsuarioHttpConverter usuarioConverter;
    private final HttpConverterWrapper httpConverter;

    @PostMapping("login")
    public ResponseEntity<HttpContractResponseWrapper> login(@RequestBody UsuarioLoginContractRequest usuario) {

        try {
            UsuarioLoginBusinessInput usuarioBusinessInput = usuarioConverter.toUsuarioLoginBusinessInput(usuario);

            UsuarioLoginBusinessOutput usuarioBusinessOutput = usuarioUseCase.login(usuarioBusinessInput);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(usuarioBusinessOutput));
        } catch (LoginInvalidoException e){
            return ResponseEntity.badRequest().body(httpConverter.toHttpException(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @PostMapping("cadastrar")
    public ResponseEntity<HttpContractResponseWrapper> cadastrarUsuario(@RequestBody UsuarioCadastroContractRequest usuario) {

        try {

            UsuarioBusinessInput usuarioBusinessInput = usuarioConverter.toUsuarioBusinessInput(usuario);

            usuarioUseCase.cadastrarUsuario(usuarioBusinessInput);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess("Usuario cadastrado com sucesso"));
        } catch (UsuarioExistenteException e){
            return ResponseEntity.badRequest().body(httpConverter.toHttpException(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<HttpContractResponseWrapper> getUsuario(@PathVariable("id") String cpf) {

        try {

            UsuarioBusinessOutput usuarioBusiness = usuarioUseCase.getUsuario(cpf);

            UsuarioContractResponse usuarioResponse = usuarioConverter.toUsuarioContractResponse(usuarioBusiness);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess(usuarioResponse));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @PatchMapping("atualizar")
    public ResponseEntity<HttpContractResponseWrapper> atualizarUsuario(@RequestBody @Valid UsuarioAtualizaContractRequest request) {

        try {

            UsuarioAtualizaBusinessInput usuarioBusinessInput = usuarioConverter.toUsuarioAtualizaBusinessInput(request);

            usuarioUseCase.atualizarUsuario(usuarioBusinessInput);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess("Usuario atualizado com sucesso"));
        } catch (UsuarioInexistenteException e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpContractResponseWrapper> deletarUsuario(@PathVariable("id") String cpf) {

        try {
            usuarioUseCase.deletarUsuario(cpf);

            return ResponseEntity.ok().body(httpConverter.toHttpSuccess("Usuario excluido com sucesso"));
        }catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(httpConverter.toHttpException(e.getMessage()));
        }
    }

}
