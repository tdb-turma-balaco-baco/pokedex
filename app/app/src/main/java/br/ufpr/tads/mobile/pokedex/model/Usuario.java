package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;

public class Usuario extends Login implements Serializable {
    private String id;
    private String nome;

    public Usuario() {}

    public Usuario(String id, String nome, String login) {
        this.id = id;
        this.nome = nome;
        this.setLogin(login);
    }

    public Usuario(String id, String login, String senha, String nome) {
        super(login, senha);
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
