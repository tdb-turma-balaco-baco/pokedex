package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.adapter.AdapterPokemon;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;

public class ListarPokemonActivity extends AppCompatActivity {
    private RecyclerView pokemonsCadastradosRecycler;
    private List<Pokemon> listaPokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pokemon);

        pokemonsCadastradosRecycler = findViewById(R.id.recyclerPokemons);

        List<String> habilidadesMock = new ArrayList<>(3);
        habilidadesMock.add("Choque");
        habilidadesMock.add("Trovão");
        habilidadesMock.add("Raio");

        listaPokemons.add(
                new Pokemon("1", "Pikachu", new BitmapDrawable().getBitmap(), "Elétrico", habilidadesMock)
        );
        listaPokemons.add(
                new Pokemon("2", "Charmander", new BitmapDrawable().getBitmap(), "Fogo", habilidadesMock)
        );

        AdapterPokemon adapterPokemon = new AdapterPokemon(listaPokemons);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        pokemonsCadastradosRecycler.setLayoutManager(layoutManager);
        pokemonsCadastradosRecycler.setHasFixedSize(true);
        pokemonsCadastradosRecycler.setAdapter(adapterPokemon);
    }
}