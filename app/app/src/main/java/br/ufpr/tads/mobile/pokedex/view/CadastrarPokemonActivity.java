package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.util.FormularioHelper;

public class CadastrarPokemonActivity extends AppCompatActivity {
    private EditText nomePokemonView;
    private EditText tipoPokemonView;
    private EditText habilidadeView;
    private ListView habilidadesCadastradasView;
    private Button addHabilidadeBtn;
    private Button salvarBtn;
    private Pokemon pokemon;
    private ArrayAdapter<String> habilidadesCadastradasAdapter;
    private String imageBase64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pokemon);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        this.nomePokemonView = findViewById(R.id.nomeText);
        this.tipoPokemonView = findViewById(R.id.tipoText);
        this.habilidadeView = findViewById(R.id.habilidadeText);
        this.habilidadesCadastradasView = findViewById(R.id.listaHabilidadesCadastro);
        this.addHabilidadeBtn = findViewById(R.id.addHabilidadeBtn);
        this.salvarBtn = findViewById(R.id.salvarBtn);

        habilidadesCadastradasAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                new ArrayList<>(0)
        );
    }

    public void addHabilidade(View view) {
        final String novaHabilidade = FormularioHelper.recuperarText(this.habilidadeView);

        if (habilidadesCadastradasAdapter.getCount() < 3 && novaHabilidade.length() > 0) {
            final List<String> habilidades = recuperarHabilidadesCadastradas();

            habilidades.add(novaHabilidade);

            habilidadesCadastradasAdapter.clear();
            habilidadesCadastradasAdapter.addAll(habilidades);
            habilidadesCadastradasAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "É permitido no máx. 3 habilidades!", Toast.LENGTH_SHORT).show();
        }
    }

    public void salvarPokemon(View view) {
        this.pokemon = this.criarPokemon(
                FormularioHelper.recuperarText(this.nomePokemonView),
                FormularioHelper.recuperarText(this.tipoPokemonView)
        );

        pokemon.setHabilidades(recuperarHabilidadesCadastradas());
        pokemon.setImageUrl(imageBase64);

        if (this.pokemon == null) {
            Toast.makeText(this, "Preencha os campos corretamente e tente novamente!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show();
            System.out.println(pokemon);
        }
    }

    public void cadastrarFotoPokemon(View view) {
        abrirGaleria();
        imageBase64 = "";
    }

    private Pokemon criarPokemon(String nome, String tipo) {
        if (nome.length() > 0 && tipo.length() > 0 && !this.habilidadesCadastradasAdapter.isEmpty()) {
            final Pokemon novoPokemon = new Pokemon();
            novoPokemon.setNome(nome);
            novoPokemon.setTipo(tipo);
            novoPokemon.setHabilidades(recuperarHabilidadesCadastradas());
            return novoPokemon;
        }
        return null;
    }

    private List<String> recuperarHabilidadesCadastradas() {
        final List<String> habilidadesCadastradas = new ArrayList<>(3);
        for (int i = 0; i < habilidadesCadastradasAdapter.getCount(); i++) {
            habilidadesCadastradas.add(habilidadesCadastradasAdapter.getItem(i));
        }
        return habilidadesCadastradas;
    }

    private void abrirGaleria() {
        Intent galeria = new Intent(Intent.ACTION_PICK);
        galeria.setData(MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, AppConstants.Galeria.PICK_IMAGE_CODE);
    }
}