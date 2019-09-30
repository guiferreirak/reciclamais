package br.com.fiap.reciclamais.gateway.repository;

import br.com.fiap.reciclamais.gateway.repository.data.UsuarioDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioDocument, String> {

    UsuarioDocument findByCpf(String cpf);
    UsuarioDocument findByEmail(String email);
}
