package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.adapter.AdapterPokemon;
import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
import br.ufpr.tads.mobile.pokedex.model.Habilidade;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.model.Usuario;
import br.ufpr.tads.mobile.pokedex.service.RetrofitConfig;
import br.ufpr.tads.mobile.pokedex.util.RecyclerItemClickListerner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarPokemonActivity extends AppCompatActivity {
    private RecyclerView pokemonsCadastradosRecycler;
    private List<Pokemon> listaPokemons = new ArrayList<>();
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pokemon);

        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable(AppConstants.USUARIO_EXTRA);

        pokemonsCadastradosRecycler = findViewById(R.id.recyclerPokemons);

        Call<List<Pokemon>> call = new RetrofitConfig().getPokemonService().buscarTodosPokemons();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful() && !response.body().isEmpty()) {
                    listaPokemons.addAll(response.body());
                } else {
                    Toast.makeText(
                            ListarPokemonActivity.this,
                            AppConstants.Informacoes.SEM_POKEMONS_CADASTRADOS,
                            Toast.LENGTH_LONG
                            ).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Toast.makeText(
                        ListarPokemonActivity.this,
                        "Erro ao buscar os pokemons cadastrados",
                        Toast.LENGTH_LONG
                ).show();
                finish();
            }
        });

        AdapterPokemon adapterPokemon = new AdapterPokemon(listaPokemons);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        pokemonsCadastradosRecycler.setLayoutManager(layoutManager);
        pokemonsCadastradosRecycler.setHasFixedSize(true);
        pokemonsCadastradosRecycler.setAdapter(adapterPokemon);

        pokemonsCadastradosRecycler.addOnItemTouchListener(
            new RecyclerItemClickListerner(
                    getApplicationContext(),
                    pokemonsCadastradosRecycler,
                    new RecyclerItemClickListerner.ClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Pokemon p = listaPokemons.get(position);

                            Intent intent = new Intent(getApplicationContext(), DetalhePokemonActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(AppConstants.POKEMON_EXTRA, p);
                            bundle.putSerializable(AppConstants.USUARIO_EXTRA, usuario);

                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                        @Override
                        public void onLongClick(View view, int position) {
                            // Sem impl
                        }
                    }
            )
        );
    }
}