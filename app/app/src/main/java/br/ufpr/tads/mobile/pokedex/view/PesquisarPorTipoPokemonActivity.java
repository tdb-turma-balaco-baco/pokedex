package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.adapter.AdapterPokemon;
import br.ufpr.tads.mobile.pokedex.model.Habilidade;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.model.Usuario;
import br.ufpr.tads.mobile.pokedex.service.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisarPorTipoPokemonActivity extends AppCompatActivity {
    private RecyclerView recyclerPokemonsPorTipo;
    private List<Pokemon> listaPokemonsPorTipo = new ArrayList<>();
    private AdapterPokemon adapterPokemon;
    private EditText buscarPorTipoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_por_tipo_pokemon);

        buscarPorTipoView = findViewById(R.id.buscarPorTipoView);

        recyclerPokemonsPorTipo = findViewById(R.id.recyclerPokemonsPorTipo);
        adapterPokemon = new AdapterPokemon(listaPokemonsPorTipo);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPokemonsPorTipo.setLayoutManager(layoutManager);
        recyclerPokemonsPorTipo.setHasFixedSize(true);
        recyclerPokemonsPorTipo.setAdapter(adapterPokemon);
    }

    public void buscarPokemonsPorTipo(View view) {
        String tipo = buscarPorTipoView.getText().toString().trim();
        Call<List<Pokemon>> call = new RetrofitConfig().getPokemonService().buscarPokemonsPorTipo(tipo);

        listaPokemonsPorTipo.clear();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaPokemonsPorTipo.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Toast.makeText(
                        PesquisarPorTipoPokemonActivity.this,
                        "Erro ao buscar por tipo",
                        Toast.LENGTH_SHORT).show();
            }
        });

        adapterPokemon.notifyDataSetChanged();
    }
}