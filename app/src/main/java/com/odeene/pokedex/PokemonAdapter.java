package com.odeene.pokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    ArrayList<Pokemon> pokemons;

    public PokemonAdapter(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonAdapter.PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_view, parent, false));
        return pokemonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.imagen.setImageResource(pokemon.getImagen());
        holder.nombre.setText(pokemon.getNombre());
        holder.tipos.setText(pokemon.getTipos().length == 2 ? pokemon.getTipos()[0].toString() + "/" + pokemon.getTipos()[1].toString() : pokemon.getTipos()[0].toString());
        holder.numero.setText(String.valueOf(pokemon.getNumero()));
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView nombre;
        TextView tipos;
        TextView numero;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imageView);
            nombre = itemView.findViewById(R.id.name);
            tipos = itemView.findViewById(R.id.types);
            numero = itemView.findViewById(R.id.number);
        }
    }
}
