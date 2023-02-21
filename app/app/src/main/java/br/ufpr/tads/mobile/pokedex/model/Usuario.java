package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String id;
    private String nome;
    private String login;

    public Usuario() {}

    public Usuario(String id, String nome, String login) {
        this.setId(id);
        this.setNome(nome);
        this.setLogin(login);
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
