package br.ufpr.tads.mobile.pokedex.repository;

import br.ufpr.tads.mobile.pokedex.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    @Query("{login: '?0', senha: '?1'}")
    Usuario findUsuarioByLoginEqualsAndSenhaEquals(String login, String senha);
}
