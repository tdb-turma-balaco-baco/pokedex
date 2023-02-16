package br.ufpr.tads.mobile.pokedex.constant;

public final class AppConstants {
    private AppConstants() {}

    public static final class WebService {
        private WebService() {}
        public static final String API_BASE_URL = "http://10.0.2.2:8080/";
    }

    public static final class Database {
        private Database() {}
        public static final String DATABASE_NAME = "pokedex.db";
        public static final String TABLE_NAME = "usuarios";
        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String LOGIN = "login";
    }

    public static final class Informacoes {
        public static final String SEM_POKEMONS_CADASTRADOS = "Não há pokemóns cadastrados";
    }

    public static final class Galeria {
        public static final int PICK_IMAGE_CODE = 100;
        public static final int PICK_IMAGE_REQUEST = 1307;
        public static final int TAKE_PHOTO_REQUEST = 0304;
        private static final int REQUEST_PERMISSIONS = 0402;
    }

    public static final class Tempo {
        private Tempo() {}
        public static final int DELAY = 3000;
    }

}
