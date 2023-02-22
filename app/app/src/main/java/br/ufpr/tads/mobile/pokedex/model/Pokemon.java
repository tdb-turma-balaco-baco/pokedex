package br.ufpr.tads.mobile.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private long id;
    private String nome;
    private String imagemBase64;
    private String tipo;
    private Usuario usuario;
    private List<Habilidade> habilidades;

    public Pokemon() {}

    public Pokemon(long id, String nome, String imagemBase64, String tipo, Usuario usuario, List<Habilidade> habilidades) {
        this.id = id;
        this.nome = nome;
        this.imagemBase64 = imagemBase64;
        this.tipo = tipo;
        this.usuario = usuario;
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
                ", imagemBase64='" + imagemBase64.length() + '\'' +
                ", tipo='" + tipo + '\'' +
                ", usuario=" + usuario +
                ", habilidades=" + habilidades +
                '}';
    }
}
