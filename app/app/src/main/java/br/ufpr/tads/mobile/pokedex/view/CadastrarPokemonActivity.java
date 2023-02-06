package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.util.FormularioHelper;

public class CadastrarPokemonActivity extends AppCompatActivity {
    private EditText nomeText;
    private EditText tipoText;
    private EditText habilidadeText;
    private RecyclerView habilidadesRecycler;
    private Button addHabilidadeBtn;
    private Button continuarBtn;
    private Pokemon pokemon;
    private List<String> habilidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pokemon);

        this.initComponents();
    }

    private void initComponents() {
        this.nomeText = findViewById(R.id.nomeText);
        this.tipoText = findViewById(R.id.tipoText);
        this.habilidadeText = findViewById(R.id.habilidadeText);
        this.habilidadesRecycler = findViewById(R.id.habilidadesRecycler);
        this.addHabilidadeBtn = findViewById(R.id.addHabilidadeBtn);
        this.continuarBtn = findViewById(R.id.continuarBtn);
        this.habilidades = new ArrayList<>();
    }

    public void addHabilidade(View view) {
        final String novaHabilidade = FormularioHelper.recuperarText(this.habilidadeText);

        if (this.habilidades.size() < 3 && novaHabilidade.length() > 0) {
            this.habilidades.add(novaHabilidade);
        } else {
            Toast.makeText(this, "É permitido no máx. 3 habilidades!", Toast.LENGTH_SHORT).show();
        }
    }

    public void continuarCadastro(View view) {
        this.pokemon = this.criarPokemon(
                FormularioHelper.recuperarText(this.nomeText),
                FormularioHelper.recuperarText(this.tipoText)
        );

        if (this.pokemon == null) {
            Toast.makeText(this, "Preencha os campos corretamente e tente novamente!", Toast.LENGTH_SHORT).show();
        } else {
            // TODO: Ir para a tela com upload de foto
            System.out.println(this.pokemon.toString());
        }
    }

    private Pokemon criarPokemon(String nome, String tipo) {
        if (nome.length() > 0 && tipo.length() > 0 && !this.habilidades.isEmpty()) {
            final Pokemon novoPokemon = new Pokemon();
            novoPokemon.setNome(nome);
            novoPokemon.setTipo(tipo);
            novoPokemon.setHabilidades(this.habilidades);
            return novoPokemon;
        }
        return null;
    }
}