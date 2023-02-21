package br.ufpr.tads.mobile.pokedex.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "pokemons")
public class Pokemon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Lob
    private String imageBase64;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column(nullable = false)
    private String tipo;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "pokemon_habilidade",
            joinColumns = @JoinColumn(name = "habilidade_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    )
    private List<Habilidade> habilidades;

    public Pokemon() {
    }

    public Pokemon(long id, String nome, String imageBase64, Usuario usuario, String tipo, List<Habilidade> habilidades) {
        this.id = id;
        this.nome = nome;
        this.imageBase64 = imageBase64;
        this.usuario = usuario;
        this.tipo = tipo;
        this.habilidades = habilidades;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", imageBase64=" + imageBase64 +
                ", usuario=" + usuario +
                ", tipo='" + tipo + '\'' +
                ", habilidades=" + habilidades +
                '}';
    }
}
