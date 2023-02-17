package br.ufpr.tads.mobile.pokedex.view;

import static br.ufpr.tads.mobile.pokedex.constant.AppConstants.Galeria.PICK_IMAGE_REQUEST;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.util.FormularioHelper;
import br.ufpr.tads.mobile.pokedex.util.ImageHelper;

public class CadastrarPokemonActivity extends AppCompatActivity {
    public static final String FOTO_SALVA_COM_SUCESSO = "Foto salva com sucesso";
    public static final String IMAGEM_SALVA_COM_SUCESSO = "Imagem salva com sucesso";
    public static final String MENSAGEM_MAX_HABILIDADES_CADASTRADAS = "É permitido no máx. 3 habilidades!";
    private EditText nomePokemonView;
    private EditText tipoPokemonView;
    private EditText habilidadeView;
    private ListView habilidadesCadastradasView;
    private Button addHabilidadeBtn;
    private Button salvarBtn;
    private Pokemon pokemon;
    private ArrayAdapter<String> habilidadesCadastradasAdapter;

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
        this.pokemon = new Pokemon();

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
            Toast.makeText(this, MENSAGEM_MAX_HABILIDADES_CADASTRADAS, Toast.LENGTH_SHORT).show();
        }
    }

    public void salvarPokemon(View view) {
        pokemon.setNome(FormularioHelper.recuperarText(nomePokemonView));
        pokemon.setTipo(FormularioHelper.recuperarText(tipoPokemonView));
        pokemon.setHabilidades(recuperarHabilidadesCadastradas());

        if (Pokemon.isPokemonInvalido(pokemon)) {
            Toast.makeText(this, "Preencha todos os campos corretamente e tente novamente!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show();
            Log.i("POKEMON", pokemon.toString());
        }
    }

    public void cadastrarFotoPokemon(View view) {
        final CharSequence[] opcoes = {"Tirar foto", "Escolher imagem", "Voltar"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarPokemonActivity.this);

        builder.setTitle(opcoes[1]);
        builder.setItems(opcoes, (dialog, selecionado) -> {
            if ("Tirar foto".contentEquals(opcoes[selecionado])) {
                tirarFoto();
            } else if ("Escolher imagem".contentEquals(opcoes[selecionado])) {
                recuperarImagemGaleria();
            } else {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private List<String> recuperarHabilidadesCadastradas() {
        final List<String> habilidadesCadastradas = new ArrayList<>(3);
        for (int i = 0; i < habilidadesCadastradasAdapter.getCount(); i++) {
            habilidadesCadastradas.add(habilidadesCadastradasAdapter.getItem(i));
        }
        return habilidadesCadastradas;
    }

    private void recuperarImagemGaleria() {
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galeria, AppConstants.Galeria.PICK_IMAGE_REQUEST);
    }

    private void tirarFoto() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File arquivo = new File(android.os.Environment.getExternalStorageDirectory(), AppConstants.Galeria.NOME_ARQUIVO_TEMP);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivo));
        startActivityForResult(camera, AppConstants.Galeria.TAKE_PHOTO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_REQUEST && data != null) {
                salvarImagemSelecionada(data);
                Toast.makeText(this, IMAGEM_SALVA_COM_SUCESSO, Toast.LENGTH_SHORT).show();
            } else if (requestCode == AppConstants.Galeria.TAKE_PHOTO_REQUEST) {
                File[] galeria = new File(Environment.getExternalStorageDirectory().toString()).listFiles();
                String foto = (galeria != null)
                        ? ImageHelper.recuperarAbsolutePathImagem(galeria, AppConstants.Galeria.NOME_ARQUIVO_TEMP)
                        : "";
                salvarFotoPokemon(foto);
                Toast.makeText(this, FOTO_SALVA_COM_SUCESSO, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void salvarFotoPokemon(String absolutePathImagem) {
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(
                    absolutePathImagem,
                    new BitmapFactory.Options()
            );
            String base64 = ImageHelper.encodeBitmapToBase64(bitmap);

            pokemon.setImagemBase64(base64);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarImagemSelecionada(Intent data) {
        try {
            Uri imagemSelecionada = data.getData();
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
            pokemon.setImagemBase64(base64);
        } catch (IOException e) {
            Log.e("ERRO", "Problema ao tentar salvar a imagem selecionada");
        }
    }
}