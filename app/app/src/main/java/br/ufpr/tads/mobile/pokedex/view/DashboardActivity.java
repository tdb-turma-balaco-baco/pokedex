package br.ufpr.tads.mobile.pokedex.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.constant.AppConstants;

public class DashboardActivity extends AppCompatActivity {
    TextView quantidadePokemonsCadastradosView;
    ListView listaTopHabilidadesView;
    ListView listaTopTiposView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        String[] listaSemPokemon = {
                AppConstants.Informacoes.SEM_POKEMONS_CADASTRADOS,
                AppConstants.Informacoes.SEM_POKEMONS_CADASTRADOS,
                AppConstants.Informacoes.SEM_POKEMONS_CADASTRADOS
        };

        ArrayAdapter<String> listaVazia = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                listaSemPokemon);

        listaTopHabilidadesView.setAdapter(listaVazia);
        listaTopTiposView.setAdapter(listaVazia);

        quantidadePokemonsCadastradosView.setText("1");
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
    }

    private <T> void iniciarActivity(Class<T> activity) {
        Intent intent = new Intent(getApplicationContext(), activity);
        startActivity(intent);
        finish();
    }
}