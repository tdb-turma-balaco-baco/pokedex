package br.ufpr.tads.mobile.pokedex.service;

import java.util.concurrent.TimeUnit;

import br.ufpr.tads.mobile.pokedex.constant.AppConstants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .callTimeout(15, TimeUnit.SECONDS)
                .build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.WebService.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public LoginService getLoginService() {
        return this.retrofit.create(LoginService.class);
    }

    public PokemonService getPokemonService() {
        return this.retrofit.create(PokemonService.class);
    }
}
