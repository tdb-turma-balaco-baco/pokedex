package br.ufpr.tads.mobile.pokedex.repository;

import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    @Query("SELECT p.tipo FROM Pokemon p GROUP BY p.tipo ORDER BY COUNT(p.tipo) DESC LIMIT 3")
    List<String> recuperarTopTipos();
}
