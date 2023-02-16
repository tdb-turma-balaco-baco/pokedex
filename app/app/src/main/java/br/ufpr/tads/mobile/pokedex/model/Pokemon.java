package br.ufpr.tads.mobile.pokedex.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private String id;
    private String nome;
    private Bitmap imageBitmap;
    private String tipo;
    private List<String> habilidades;

    public Pokemon() {}

    public Pokemon(String id, String nome, Bitmap imageUrl, String tipo, List<String> habilidades) {
        this.id = id;
        this.nome = nome;
        this.imageBitmap = imageUrl;
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

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
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
                ", imageBitmap='" + imageBitmap + '\'' +
                ", tipo='" + tipo + '\'' +
                ", habilidades=" + habilidades +
                '}';
    }
}
