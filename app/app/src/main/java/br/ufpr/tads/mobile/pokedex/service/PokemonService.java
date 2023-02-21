package br.ufpr.tads.mobile.pokedex.service;

import java.util.List;

import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PokemonService {
    @GET("/pokemon/top/habilidades")
    Call<List<String>> recuperarTopHabilidades();
    @GET("/pokemon/top/tipos")
    Call<List<String>> recuperarTopTipos();
    @GET("/pokemon/quantidade")
    Call<Integer> recuperarQuantidadePokemonsCadastrados();
//    @GET("/pokemon/x")
//    Pokemon buscarPokemonPorId(int id);
    @GET("/pokemon")
    Call<List<Pokemon>> buscarTodosPokemons();
//    @GET("/pokemon?tipo=")
//    List<Pokemon> buscarPokemonsPorTipo(String tipo);
//    @GET("/pokemon?habilidade=")
//    List<Pokemon> buscarPokemonsPorHabilidade(String habilidade);
//    @POST("/pokemon")
//    void cadastrarPokemon(Pokemon pokemon);
//    @PUT("/pokemon/x")
//    void atualizarPokemon(Pokemon pokemon);
//    @DELETE("/pokemon/x")
//    void removerPokemon(int id);
}
