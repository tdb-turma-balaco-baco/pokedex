package br.ufpr.tads.mobile.pokedex.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Pokemon {
    @Id
    private String id;

    @Indexed(unique = true)
    private String nome;
    private String imageUrl;
    private String criadoPor;

    @Field("tipo")
    private Tipo tipo;

    @Field("habilidade")
    private Habilidade habilidade;

    public Pokemon() {
    }

    public Pokemon(String id, String nome, String imageUrl, String criadoPor, Tipo tipo, Habilidade habilidade) {
        this.id = id;
        this.nome = nome;
        this.imageUrl = imageUrl;
        this.criadoPor = criadoPor;
        this.tipo = tipo;
        this.habilidade = habilidade;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
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

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", criadoPor='" + criadoPor + '\'' +
                ", tipo=" + tipo +
                ", habilidade=" + habilidade +
                '}';
    }
}
