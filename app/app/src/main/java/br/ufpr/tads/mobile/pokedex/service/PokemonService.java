package br.ufpr.tads.mobile.pokedex.service;

import java.util.Collection;

import br.ufpr.tads.mobile.pokedex.exception.ExternalAPIException;
import br.ufpr.tads.mobile.pokedex.model.Habilidade;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.model.Tipo;

public interface PokemonService {
    Pokemon buscarPokemonPorId(int id) throws ExternalAPIException;
    Collection<Pokemon> buscarTodosPokemons() throws ExternalAPIException;
    Collection<Pokemon> buscarPokemonsPorTipo(String tipo) throws ExternalAPIException;
    Collection<Pokemon> buscarPokemonsPorHabilidade(String habilidade) throws ExternalAPIException;
    void cadastrarPokemon(Pokemon pokemon) throws ExternalAPIException;
    void atualizarPokemon(Pokemon pokemon) throws ExternalAPIException;
    void removerPokemon(int id) throws ExternalAPIException;
}
