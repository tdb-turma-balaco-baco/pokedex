package br.ufpr.tads.mobile.pokedex.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
import br.ufpr.tads.mobile.pokedex.model.Habilidade;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.model.Usuario;
import br.ufpr.tads.mobile.pokedex.service.RetrofitConfig;
import br.ufpr.tads.mobile.pokedex.util.FormularioHelper;
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
        Bitmap bitmap = pokemon.getImageBase64() != null ? ImageHelper.decodeBase64ToBitmap(pokemon.getImageBase64()) : null;
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

    public void atualizarPokemon(View view) {
        pokemon.setNome(FormularioHelper.recuperarText(editNomePokemonView));
        pokemon.setTipo(FormularioHelper.recuperarText(editTipoPokemonView));

        if (Pokemon.isPokemonInvalido(pokemon)) {
            Toast.makeText(
                            this,
                            "Informações inválidas, verifique e tente novamente!",
                            Toast.LENGTH_SHORT)
                    .show();
        } else {
            Call<Pokemon> call = new RetrofitConfig().getPokemonService().atualizarPokemon(
                    pokemon.getId(),
                    pokemon
            );
            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(
                                        DetalhePokemonActivity.this,
                                        "Pokemon atualizado com sucesso!",
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
                                    "Erro ao atualizar o pokemon",
                                    Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }

    public void alterarImagem(View view) {
        recuperarImagemGaleria();
    }

    private void recuperarImagemGaleria() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).setType("image/*");
        startActivityForResult(galeria, AppConstants.Galeria.PICK_IMAGE_REQUEST);
    }

    private void salvarImagemSelecionada(Intent data) {
        try {
            Uri imagemSelecionada = data.getData();
            imagemPokemon.setImageURI(imagemSelecionada);
            Cursor selecaoNomeArquivo = getContentResolver().query(imagemSelecionada, null, null, null, null);

            selecaoNomeArquivo.moveToFirst();
            String idArquivo = selecaoNomeArquivo.getString(0);
            idArquivo = idArquivo.substring(idArquivo.lastIndexOf(":") + 1);
            selecaoNomeArquivo.close();

            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ?", new String[]{idArquivo}, null);
            cursor.moveToFirst();
            String logCaminhoImagem = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            Log.i("INFO", "Path imagem selecionada: " + logCaminhoImagem);
            cursor.close();

            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagemSelecionada);
            String base64 = ImageHelper.encodeBitmapToBase64(bitmap);
            pokemon.setImageBase64(base64);
        } catch (IOException e) {
            Log.e("ERRO", "Problema ao tentar salvar a imagem selecionada");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
                salvarImagemSelecionada(data);
                Toast.makeText(this, "Imagem salva com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}