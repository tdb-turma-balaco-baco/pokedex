package br.ufpr.tads.mobile.pokedex.service;

import java.util.List;

import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonService {
    @GET("/pokemon/top/habilidades")
    Call<List<String>> recuperarTopHabilidades();
    @GET("/pokemon/top/tipos")
    Call<List<String>> recuperarTopTipos();
    @GET("/pokemon/quantidade")
    Call<Integer> recuperarQuantidadePokemonsCadastrados();
    @GET("/pokemon/{id}")
    Call<Pokemon> buscarPokemonPorId(@Path("id") long id);
    @GET("/pokemon")
    Call<List<Pokemon>> buscarTodosPokemons();
    @GET("/pokemon/buscar")
    Call<List<Pokemon>> buscarPokemonsPorTipo(@Query("tipo") String tipo);
    @GET("/pokemon/buscar")
    Call<List<Pokemon>> buscarPokemonsPorHabilidade(@Query("habilidade") String habilidade);
    @POST("/pokemon")
    Call<Pokemon> cadastrarPokemon(@Body Pokemon pokemon);
    @PUT("/pokemon/{id}")
    Call<Pokemon> atualizarPokemon(@Path("id") long id, @Body Pokemon pokemon);
    @DELETE("/pokemon/{id}")
    Call<Pokemon> removerPokemon(long id);
}
