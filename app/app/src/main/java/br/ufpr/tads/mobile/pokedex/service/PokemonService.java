package br.ufpr.tads.mobile.pokedex.service;

import java.util.List;

import br.ufpr.tads.mobile.pokedex.exception.ExternalAPIException;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;

public interface PokemonService {
    Pokemon buscarPokemonPorId(int id) throws ExternalAPIException;
    List<Pokemon> buscarTodosPokemons() throws ExternalAPIException;
    List<Pokemon> buscarPokemonsPorTipo(String tipo) throws ExternalAPIException;
    List<Pokemon> buscarPokemonsPorHabilidade(String habilidade) throws ExternalAPIException;
    void cadastrarPokemon(Pokemon pokemon) throws ExternalAPIException;
    void atualizarPokemon(Pokemon pokemon) throws ExternalAPIException;
    void removerPokemon(int id) throws ExternalAPIException;
}
