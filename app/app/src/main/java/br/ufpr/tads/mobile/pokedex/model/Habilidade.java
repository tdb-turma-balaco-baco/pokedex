package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;

public class Habilidade implements Serializable {
    private long id;
    private String nome;

    public Habilidade() {
    }

    public Habilidade(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Habilidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
