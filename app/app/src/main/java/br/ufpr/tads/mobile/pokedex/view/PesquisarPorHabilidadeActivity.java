package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.adapter.AdapterPokemon;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;

public class PesquisarPorHabilidadeActivity extends AppCompatActivity {
    private RecyclerView recyclerPokemonsPorHabilidade;
    private List<Pokemon> listaPokemonsPorHabilidade = new ArrayList<>(0);
    private AdapterPokemon adapterPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_por_habilidade);

        recyclerPokemonsPorHabilidade = findViewById(R.id.recyclerPokemonsPorHabilidade);

        adapterPokemon = new AdapterPokemon(listaPokemonsPorHabilidade);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPokemonsPorHabilidade.setLayoutManager(layoutManager);
        recyclerPokemonsPorHabilidade.setHasFixedSize(true);
        recyclerPokemonsPorHabilidade.setAdapter(adapterPokemon);
    }

    public void buscarPokemonsPorHabilidade(View view) {
        List<String> habilidadesMock = new ArrayList<>(3);
        habilidadesMock.add("Choque");
        habilidadesMock.add("Trovão");
        habilidadesMock.add("Raio");

        listaPokemonsPorHabilidade.clear();
        listaPokemonsPorHabilidade.add(new Pokemon("1", "Pikachu", new BitmapDrawable().getBitmap(), "Elétrico", habilidadesMock));
        adapterPokemon.notifyDataSetChanged();
    }
}