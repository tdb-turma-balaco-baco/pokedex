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

public class PesquisarPorHabilidadeActivity extends AppCompatActivity {
    private RecyclerView recyclerPokemonsPorHabilidade;
    private List<Pokemon> listaPokemonsPorHabilidade = new ArrayList<>();
    private AdapterPokemon adapterPokemon;
    private EditText buscarPorHabilidadeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_por_habilidade);

        buscarPorHabilidadeView = findViewById(R.id.buscarPorHabilidadeView);
        recyclerPokemonsPorHabilidade = findViewById(R.id.recyclerPokemonsPorHabilidade);

        adapterPokemon = new AdapterPokemon(listaPokemonsPorHabilidade);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPokemonsPorHabilidade.setLayoutManager(layoutManager);
        recyclerPokemonsPorHabilidade.setHasFixedSize(true);
        recyclerPokemonsPorHabilidade.setAdapter(adapterPokemon);

    }

    public void buscarPokemonsPorHabilidade(View view) {
        String habilidade = buscarPorHabilidadeView.getText().toString().trim();
        Call<List<Pokemon>> call = new RetrofitConfig().getPokemonService().buscarPokemonsPorHabilidade(habilidade);

        listaPokemonsPorHabilidade.clear();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaPokemonsPorHabilidade.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Toast.makeText(
                        PesquisarPorHabilidadeActivity.this,
                        "Erro ao buscar por habilidade",
                        Toast.LENGTH_SHORT).show();
            }
        });

        adapterPokemon.notifyDataSetChanged();
    }
}