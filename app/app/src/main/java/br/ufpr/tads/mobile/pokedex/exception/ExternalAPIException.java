package br.ufpr.tads.mobile.pokedex.exception;

public class ExternalAPIException extends Exception {
    public ExternalAPIException(String message) {
        super(String.format("[ERRO] Falha no consumo de API/WS Externo: %s", message));
    }

    public ExternalAPIException(String message, Throwable cause) {
        super(String.format("[ERRO] Falha no consumo de API/WS Externo: %s - [LOG]=%s", message, cause));
    }

    public ExternalAPIException(Throwable cause) {
        super(cause);
    }
}
