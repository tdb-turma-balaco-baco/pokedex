package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private String id;
    private String nome;
    private String imagemBase64;
    private String tipo;
    private List<String> habilidades;

    public Pokemon() {}

    public Pokemon(String id, String nome, String imagemBase64, String tipo, List<String> habilidades) {
        this.id = id;
        this.nome = nome;
        this.imagemBase64 = imagemBase64;
        this.tipo = tipo;
        this.habilidades = habilidades;
    }

    public static boolean isPokemonInvalido(Pokemon pokemon) {
        return (
                pokemon.getImagemBase64() == null
                        || pokemon.getImagemBase64().equalsIgnoreCase("")
                        || pokemon.getHabilidades().isEmpty()
                        || pokemon.getHabilidades().size() > 3
                        || pokemon.getNome().equalsIgnoreCase("")
                        || pokemon.getNome() == null
                        || pokemon.getTipo().equalsIgnoreCase("")
                        || pokemon.getTipo() == null
        );
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

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
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
                ", imageBitmap='" + imagemBase64.length() + '\'' +
                ", tipo='" + tipo + '\'' +
                ", habilidades=" + habilidades +
                '}';
    }
}
