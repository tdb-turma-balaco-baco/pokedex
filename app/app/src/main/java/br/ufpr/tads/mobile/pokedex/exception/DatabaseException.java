package br.ufpr.tads.mobile.pokedex.exception;

public class DatabaseException extends Exception {
    public DatabaseException() {
        super(String.format("[ERRO] Falha no banco de dados"));
    }

    public DatabaseException(String message) {
        super(String.format("[ERRO] Falha no banco de dados: %s", message));
    }

    public DatabaseException(String message, Throwable cause) {
        super(String.format("[ERRO] Falha no banco de dados: %s - [LOG]=%s", message, cause));
    }
}
