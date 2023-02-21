package br.ufpr.tads.mobile.pokedex.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
import br.ufpr.tads.mobile.pokedex.model.Usuario;
import br.ufpr.tads.mobile.pokedex.service.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    TextView quantidadePokemonsCadastradosView;
    ListView listaTopHabilidadesView;
    ListView listaTopTiposView;
    ArrayAdapter<String> topHabilidades;
    ArrayAdapter<String> topTipos;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable(AppConstants.USUARIO_EXTRA);

        inicializarComponentes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.cadastrar_pokemon) {
            iniciarActivity(CadastrarPokemonActivity.class);
        } else if (itemId == R.id.listar_pokemons) {
            iniciarActivity(ListarPokemonActivity.class);
        } else if (itemId == R.id.pesquisar_habilidade) {
            iniciarActivity(PesquisarPorHabilidadeActivity.class);
        } else if (itemId == R.id.pesquisar_tipo) {
            iniciarActivity(PesquisarPorTipoPokemonActivity.class);
        } else if (itemId == R.id.exit_app) {
            finishAndRemoveTask();
        }

        return super.onOptionsItemSelected(item);
    }

    private void inicializarComponentes() {
        quantidadePokemonsCadastradosView = findViewById(R.id.qtdTotalPokemons);
        listaTopHabilidadesView = findViewById(R.id.listaTopHabilidades);
        listaTopTiposView = findViewById(R.id.listaTopTipos);

        topHabilidades = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.list_black_text,
                recuperarTopHabilidades());

        topTipos = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.list_black_text,
                recuperarTopTipos());

        listaTopHabilidadesView.setAdapter(topHabilidades);
        listaTopTiposView.setAdapter(topTipos);

        recuperarQuantidadePokemonsCadastrados();
    }

    private <T> void iniciarActivity(Class<T> activity) {
        Intent intent = new Intent(getApplicationContext(), activity);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.USUARIO_EXTRA, usuario);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private List<String> recuperarTopHabilidades() {
        List<String> lista = new ArrayList<>(3);
        Call<List<String>> call = new RetrofitConfig().getPokemonService().recuperarTopHabilidades();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && !response.body().isEmpty()) {
                    lista.addAll(response.body());
                } else {
                    lista.add(AppConstants.Informacoes.SEM_POKEMONS_CADASTRADOS);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                lista.add(AppConstants.Informacoes.SEM_POKEMONS_CADASTRADOS);
            }
        });

        return lista;
    }

    private List<String> recuperarTopTipos() {
        List<String> lista = new ArrayList<>(3);
        Call<List<String>> call = new RetrofitConfig().getPokemonService().recuperarTopTipos();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && !response.body().isEmpty()) {
                    lista.addAll(response.body());
                } else {
                    lista.add(AppConstants.Informacoes.SEM_POKEMONS_CADASTRADOS);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                lista.add(AppConstants.Informacoes.SEM_POKEMONS_CADASTRADOS);
            }
        });

        return lista;
    }

    private void recuperarQuantidadePokemonsCadastrados() {
        Call<Integer> call = new RetrofitConfig().getPokemonService().recuperarQuantidadePokemonsCadastrados();
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                String quantidade = String.valueOf(response.body());
                quantidadePokemonsCadastradosView.setText(quantidade);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("ERRO", t.toString());
            }
        });
    }
}