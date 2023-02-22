package br.ufpr.tads.mobile.pokedex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufpr.tads.mobile.pokedex.R;
import br.ufpr.tads.mobile.pokedex.model.Pokemon;
import br.ufpr.tads.mobile.pokedex.util.ImageHelper;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.ViewHolder> {

    private List<Pokemon> listaPokemons;

    public AdapterPokemon(List<Pokemon> pokemons) {
        listaPokemons = pokemons;
    }

    // Innerclass contendo informações das células na lista
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView tipo;
        TextView habilidades;
        TextView usuario;
        ImageView foto;

        public ViewHolder(View view) {
            super(view);
            nome = view.findViewById(R.id.nomePokemonView);
            tipo = view.findViewById(R.id.tipoPokemonView);
            habilidades = view.findViewById(R.id.habilidadesView);
            foto = view.findViewById(R.id.fotoPokemonView);
            usuario = view.findViewById(R.id.usuarioCriadorView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list_pokemons, parent, false);
        return new ViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = listaPokemons.get(position);

        holder.nome.setText(pokemon.getNome());
        holder.tipo.setText(String.format("Tipo: %s", pokemon.getTipo()));
        holder.habilidades.setText(pokemon.getHabilidadesTexto());
        holder.usuario.setText(String.format("Criado por: %s", pokemon.getUsuario().getNome()));

        if (pokemon.getImageBase64() != null) {
            holder.foto.setImageBitmap(ImageHelper.decodeBase64ToBitmap(pokemon.getImageBase64()));
        } else {
            holder.foto.setImageBitmap(null);
        }
    }

    @Override
    public int getItemCount() {
        return listaPokemons.size();
    }
}
