package br.ufpr.tads.mobile.pokedex.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "habilidades")
public class Habilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String nome;
    @ManyToMany(mappedBy = "habilidades")
    private List<Pokemon> pokemons;

    public Habilidade() {
    }

    public Habilidade(long id, String nome, List<Pokemon> pokemons) {
        this.id = id;
        this.nome = nome;
        this.pokemons = pokemons;
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

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @Override
    public String toString() {
        return "Habilidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", pokemons=" + pokemons +
                '}';
    }
}
