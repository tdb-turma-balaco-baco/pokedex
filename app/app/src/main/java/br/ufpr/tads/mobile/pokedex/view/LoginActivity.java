package br.ufpr.tads.mobile.pokedex.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
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
            Login loginRequest = new Login(login, senha);

            Call<Usuario> call = new RetrofitConfig().getLoginService().efetuarLogin(loginRequest);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()) {
                        usuario = response.body();
                        iniciarDashboardActivity();
                    } else {
                        progressDialog.dismiss();
                        LoginActivity.this.toastLoginInvalido().show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Problema ao realizar login", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            progressDialog.dismiss();
            this.toastLoginInvalido().show();
        }
    }

    private void iniciarDashboardActivity() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.USUARIO_EXTRA, usuario);
        intent.putExtras(bundle);

        startActivity(intent);
        finish();
    }

    private Toast toastLoginInvalido() {
        return Toast.makeText(this, "Login ou senha inválidos!", Toast.LENGTH_SHORT);
    }
}