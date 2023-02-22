package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private long id;
    private String nome;
    private String imageBase64;
    private String tipo;
    private Usuario usuario;
    private List<Habilidade> habilidades;

    public Pokemon() {}

    public Pokemon(long id, String nome, String imageBase64, String tipo, Usuario usuario, List<Habilidade> habilidades) {
        this.id = id;
        this.nome = nome;
        this.imageBase64 = imageBase64;
        this.tipo = tipo;
        this.usuario = usuario;
        this.habilidades = habilidades;
    }

    public static boolean isPokemonInvalido(Pokemon pokemon) {
        return (
                pokemon.getImageBase64() == null
                        || pokemon.getImageBase64().equalsIgnoreCase("")
                        || pokemon.getHabilidades().isEmpty()
                        || pokemon.getHabilidades().size() > 3
                        || pokemon.getNome().equalsIgnoreCase("")
                        || pokemon.getNome() == null
                        || pokemon.getTipo().equalsIgnoreCase("")
                        || pokemon.getTipo() == null
        );
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

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public String getHabilidadesTexto() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Habilidade habilidade : habilidades) {
            stringBuilder.append(habilidade.getNome());
            stringBuilder.append(",");
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", imagemBase64='" + imageBase64.length() + '\'' +
                ", tipo='" + tipo + '\'' +
                ", usuario=" + usuario +
                ", habilidades=" + habilidades +
                '}';
    }
}
