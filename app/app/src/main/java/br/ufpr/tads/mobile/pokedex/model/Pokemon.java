package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private String id;
    private String nome;
    private String imageUrl;
    private String tipo;
    private List<String> habilidades;

    public Pokemon() {}

    public Pokemon(String id, String nome, String imageUrl, String tipo, List<String> habilidades) {
        this.id = id;
        this.nome = nome;
        this.imageUrl = imageUrl;
        this.tipo = tipo;
        this.habilidades = habilidades;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public String getHabilidadesTexto() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String habilidade : habilidades) {
            stringBuilder.append(habilidade);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public void setHabilidades(List<String> habilidades) {
        if (habilidades.size() > 3) {
            throw new IllegalArgumentException("[ERRO] setHabilidades -> Não pode ultrapassar 3 habilidades!");
        } else {
            this.habilidades = habilidades;
        }
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", tipo='" + tipo + '\'' +
                ", habilidades=" + habilidades +
                '}';
    }
}
