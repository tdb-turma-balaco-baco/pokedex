package br.ufpr.tads.mobile.pokedex.constant;

public final class AppConstants {

    public interface WebService {
        String API_BASE_URL = "http://10.0.2.2:8080/";
    }

    public interface Database {
        String DATABASE_NAME = "pokedex.db";
        String TABLE_NAME = "usuarios";
        String ID = "id";
        String NOME = "nome";
        String LOGIN = "login";
    }

    public interface Tempo {
        int DELAY = 3000;
    }

}
