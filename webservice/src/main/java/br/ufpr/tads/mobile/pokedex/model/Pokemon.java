package br.ufpr.tads.mobile.pokedex.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pokemons")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Lob
    @Column
    private byte[] imageBase64;
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

    public Pokemon() {}

    public Pokemon(Long id, String nome, byte[] imageBase64, Usuario usuario, String tipo, List<Habilidade> habilidades) {
        this.id = id;
        this.nome = nome;
        this.imageBase64 = imageBase64;
        this.usuario = usuario;
        this.tipo = tipo;
        this.habilidades = habilidades;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(byte[] imageBase64) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) && Objects.equals(nome, pokemon.nome) && Objects.equals(usuario, pokemon.usuario) && Objects.equals(tipo, pokemon.tipo) && Objects.equals(habilidades, pokemon.habilidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuario, tipo, habilidades);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", usuario='" + usuario + '\'' +
                ", tipo=" + tipo +
                ", habilidades=" + habilidades +
                '}';
    }
}
