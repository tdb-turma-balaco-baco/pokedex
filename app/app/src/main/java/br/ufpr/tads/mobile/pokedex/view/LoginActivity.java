package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.security.auth.login.LoginException;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.database.UsuarioDAO;
import br.ufpr.tads.mobile.pokedex.exception.DatabaseException;
import br.ufpr.tads.mobile.pokedex.exception.ExternalAPIException;
import br.ufpr.tads.mobile.pokedex.model.Login;
import br.ufpr.tads.mobile.pokedex.model.Usuario;
import br.ufpr.tads.mobile.pokedex.service.RetrofitConfig;
import br.ufpr.tads.mobile.pokedex.util.FormularioHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText editTextLogin;
    EditText editTextSenha;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.initComponents();
    }

    private void initComponents() {
        this.editTextLogin = findViewById(R.id.editTextLogin);
        this.editTextSenha = findViewById(R.id.editTextSenha);
    }

    public void login(final View view) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Realizando login...");
        progressDialog.show();

        final String login = FormularioHelper.recuperarText(this.editTextLogin);
        final String senha = FormularioHelper.recuperarText(this.editTextSenha);

        if (login.length() > 0 && senha.length() > 0) {
            iniciarDashboardActivity();

//            Login loginRequest = new Login(login, senha);
//
//            Call<Usuario> call = new RetrofitConfig().getLoginService().efetuarLogin(loginRequest);
//            call.enqueue(new Callback<Usuario>() {
//                @Override
//                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
//                    usuario = response.body();
//
//                    iniciarDashboardActivity();
//                }
//
//                @Override
//                public void onFailure(Call<Usuario> call, Throwable t) {
//                    progressDialog.dismiss();
//                    new AlertDialog.Builder(LoginActivity.this)
//                            .setTitle("Erro")
//                            .setMessage("Problema ao realizar o login")
//                            .show();
//                }
//            });
//
//            if (usuario != null) {
//                try {
//                    UsuarioDAO dao = new UsuarioDAO(this);
//                    dao.insert(usuario);
//                    Log.i("INFO", dao.fetch().toString());
//                } catch (DatabaseException e) {
//                    Log.e("ERROR", e.getMessage());
//                }
//            }
        } else {
            progressDialog.dismiss();
            this.toastLoginInvalido().show();
        }
    }

    private void iniciarDashboardActivity() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("usuario", usuario);
        intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }

    private Toast toastLoginInvalido() {
        return Toast.makeText(this, "Login ou senha inválidos!", Toast.LENGTH_SHORT);
    }
}