package br.ufpr.tads.mobile.pokedex.repository;

import br.ufpr.tads.mobile.pokedex.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    @Query("SELECT h.nome FROM Habilidade h GROUP BY h.nome ORDER BY COUNT(h.nome) LIMIT 3")
    List<String> recuperarTopHabilidades();
}
