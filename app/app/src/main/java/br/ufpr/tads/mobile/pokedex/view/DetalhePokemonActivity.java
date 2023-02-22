package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
import br.ufpr.tads.mobile.pokedex.model.Habilidade;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.model.Usuario;
import br.ufpr.tads.mobile.pokedex.service.RetrofitConfig;
import br.ufpr.tads.mobile.pokedex.util.ImageHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhePokemonActivity extends AppCompatActivity {
    private ImageView imagemPokemon;
    private EditText editNomePokemonView;
    private EditText editTipoPokemonView;
    private TextView criadoPorText;
    private EditText editHabilidadeView;
    private ListView editListHabilidadesView;
    private Pokemon pokemon;
    private Usuario usuario;
    private ArrayAdapter<String> habilidadesCadastradasAdapter;
    private List<String> listaHabilidadesCadastradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_pokemon);

        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable(AppConstants.USUARIO_EXTRA);
        pokemon = (Pokemon) bundle.getSerializable(AppConstants.POKEMON_EXTRA);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        imagemPokemon = findViewById(R.id.imagemPokemon);
        Bitmap bitmap = pokemon.getImagemBase64() != null ? ImageHelper.decodeBase64ToBitmap(pokemon.getImagemBase64()) : null;
        imagemPokemon.setImageBitmap(bitmap);

        editNomePokemonView = findViewById(R.id.editNomePokemonView);
        editNomePokemonView.setText(pokemon.getNome());

        editTipoPokemonView = findViewById(R.id.editTipoPokemonView);
        editTipoPokemonView.setText(pokemon.getTipo());

        criadoPorText = findViewById(R.id.criadoPorText);
        String criadoPor = "Criado por: " + pokemon.getUsuario().getNome();
        criadoPorText.setText(criadoPor);

        editHabilidadeView = findViewById(R.id.editHabilidadeView);
        editListHabilidadesView = findViewById(R.id.editListHabilidadesView);

        this.listaHabilidadesCadastradas = new ArrayList<>();
        for (Habilidade habilidade:pokemon.getHabilidades()) {
            listaHabilidadesCadastradas.add(habilidade.getNome());
        }

        habilidadesCadastradasAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.list_black_text,
                listaHabilidadesCadastradas
        );
        editListHabilidadesView.setAdapter(habilidadesCadastradasAdapter);
    }

    public void removerPokemon(View view) {
        Call<Pokemon> call = new RetrofitConfig().getPokemonService().removerPokemon(pokemon.getId());
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(
                                    DetalhePokemonActivity.this,
                                    "Pokemon removido com sucesso!",
                                    Toast.LENGTH_LONG)
                            .show();
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(AppConstants.USUARIO_EXTRA, usuario);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(
                        DetalhePokemonActivity.this,
                        "Erro ao remover o pokemon",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}