package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.adapter.AdapterPokemon;
import br.ufpr.tads.mobile.pokedex.model.Habilidade;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.model.Usuario;

public class PesquisarPorTipoPokemonActivity extends AppCompatActivity {
    private RecyclerView recyclerPokemonsPorTipo;
    private List<Pokemon> listaPokemonsPorTipo = new ArrayList<>();
    private AdapterPokemon adapterPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_por_tipo_pokemon);

        recyclerPokemonsPorTipo = findViewById(R.id.recyclerPokemonsPorTipo);
        adapterPokemon = new AdapterPokemon(listaPokemonsPorTipo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPokemonsPorTipo.setLayoutManager(layoutManager);
        recyclerPokemonsPorTipo.setHasFixedSize(true);
        recyclerPokemonsPorTipo.setAdapter(adapterPokemon);
    }

    public void buscarPokemonsPorTipo(View view) {
        List<Habilidade> habilidadesMock = new ArrayList<>(3);
        habilidadesMock.add(new Habilidade(0L, "Choque"));
        habilidadesMock.add(new Habilidade(1L, "Trovão"));
        habilidadesMock.add(new Habilidade(2L, "Raio"));

        listaPokemonsPorTipo.clear();
        listaPokemonsPorTipo.add(new Pokemon("1", "Pikachu", "", "Elétrico",new Usuario("id1", "Mock", "mock"), habilidadesMock));

        adapterPokemon.notifyDataSetChanged();
    }
}