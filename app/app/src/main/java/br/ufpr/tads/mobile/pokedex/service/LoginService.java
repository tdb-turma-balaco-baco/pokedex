package br.ufpr.tads.mobile.pokedex.service;

import br.ufpr.tads.mobile.pokedex.model.Login;
import br.ufpr.tads.mobile.pokedex.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/login")
    Call<Usuario> efetuarLogin(@Body Login login);

    // void efetuarLogout();
}
