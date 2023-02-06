package br.ufpr.tads.mobile.pokedex.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterHabilidades extends RecyclerView.Adapter<AdapterHabilidades.HabilidadesHolder> {

    // Innerclass contendo informações das células na lista
    public class HabilidadesHolder extends RecyclerView.ViewHolder {
        TextView titulo;

        public HabilidadesHolder(View view) {
            super(view);
        }
    }

    @NonNull
    @Override
    public HabilidadesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HabilidadesHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
