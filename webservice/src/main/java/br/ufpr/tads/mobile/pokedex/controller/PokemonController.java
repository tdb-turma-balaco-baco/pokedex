package br.ufpr.tads.mobile.pokedex.controller;

import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.repository.HabilidadeRepository;
import br.ufpr.tads.mobile.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PokemonController {
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @GetMapping("/pokemon")
    public List<Pokemon> recuperarTodosPokemons() {
        return pokemonRepository.findAll();
    }

    @PostMapping("/pokemon")
    public Pokemon cadastrarPokemon(@RequestBody Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @PutMapping("/pokemon/{id}")
    public Pokemon atualizarPokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        Pokemon p = pokemonRepository.findById(id).orElse(new Pokemon());
        pokemon.setId(id); // Garante que o id não é atualizado
        pokemon.setUsuario(p.getUsuario()); // Garante que é sempre o mesmo usuário criador
        return pokemonRepository.save(pokemon);
    }

    @DeleteMapping("/pokemon/{id}")
    public Pokemon removerPokemon(@PathVariable Long id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);

        if (pokemon.isPresent())
            pokemonRepository.deleteById(id);

        return pokemon.orElse(null);
    }

    @GetMapping("/pokemon/quantidade")
    public long recuperarQuantidadeTotalPokemonsCadastrados() {
        return pokemonRepository.count();
    }

    @GetMapping("/pokemon/top/habilidade")
    public List<String> recuperarTopHabilidades() {
        return habilidadeRepository.recuperarTopHabilidades();
    }

    @GetMapping("/pokemon/top/tipos")
    public List<String> recuperarTopTipos() {
        return pokemonRepository.recuperarTopTipos();
    }
}
