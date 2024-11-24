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
    OnItemClickListener listener; // Indicamos que tendra un evento click

    public interface OnItemClickListener{
        void onItemClick(Pokemon pokemon);
    }

    public PokemonAdapter(ArrayList<Pokemon> pokemons, OnItemClickListener listener) {
        this.pokemons = pokemons;
        this.listener = listener;
    }

    @NonNull
    @Override //Creamos la vista para los elementos de la lista pasandole el documento xml
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonAdapter.PokemonViewHolder pokemonViewHolder = new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_view, parent, false));
        return pokemonViewHolder;
    }

    @Override //Carga las propiedades de cada elemento en el xml
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
        ImageView imagen; //Elementos de la vista
        TextView nombre;
        TextView tipos;
        TextView numero;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imageView);
            nombre = itemView.findViewById(R.id.name);
            tipos = itemView.findViewById(R.id.types);
            numero = itemView.findViewById(R.id.number);
            itemView.setOnClickListener(new View.OnClickListener() { //Establecemos el metodo on click, despues de tiene que implementar donde se cree el adaptador
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onItemClick(pokemons.get(getAdapterPosition())); //El método tendra como atributo un pokemon
                }
            });
        }
    }
}
