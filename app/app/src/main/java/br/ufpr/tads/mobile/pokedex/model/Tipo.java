package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;

public class Tipo implements Serializable {
    private int id;
    private String titulo;

    public Tipo() {}

    public Tipo(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
