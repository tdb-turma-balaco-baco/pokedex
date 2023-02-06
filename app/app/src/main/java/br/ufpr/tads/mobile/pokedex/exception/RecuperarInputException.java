package br.ufpr.tads.mobile.pokedex.exception;

public class RecuperarInputException extends Exception {
    public RecuperarInputException() {
        super("[ERRO] Falha ao recuperar o input");
    }

    public RecuperarInputException(String message) {
        super(String.format("[ERRO] Falha ao recuperar o input: %s", message));
    }

    public RecuperarInputException(String message, Throwable cause) {
        super(String.format("[ERRO] Falha ao recuperar o input: %s - [LOG]=%s", message, cause));
    }
}
