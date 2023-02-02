package br.ufpr.tads.mobile.pokedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Habilidade {
    @Id
    private String id;
    @Indexed(unique = true)
    private String titulo;

    public Habilidade() {
    }

    public Habilidade(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Habilidade{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
