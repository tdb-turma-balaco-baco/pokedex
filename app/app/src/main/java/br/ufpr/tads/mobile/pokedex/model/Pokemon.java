package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int id;
    private String nome;
    private String imageUrl;
    private Tipo tipo;
    private Habilidade habilidade;

    public Pokemon() {}

    public Pokemon(int id, String nome, String imageUrl, Tipo tipo, Habilidade habilidade) {
        this.id = id;
        this.nome = nome;
        this.imageUrl = imageUrl;
        this.tipo = tipo;
        this.habilidade = habilidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }
}
