package br.ufpr.tads.mobile.pokedex.repository;

import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PokemonRepository extends MongoRepository<Pokemon, String> {
    @Query("{nome:'?0'}")
    Pokemon findPokemonByNomeIsLike(String nome);
}
