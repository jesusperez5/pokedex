package com.odeene.pokedex;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {
    ArrayList<Pokemon> pokemons;
    OnItemClickListener listener; // Indicamos que tendra un evento click
    private Map<Pokemon.tipo, Integer> tipoColorMap = new HashMap<>(); //Mapa para guardar colores

    public interface OnItemClickListener{
        void onItemClick(Pokemon pokemon);
    }

    // Constructor del adapter
    public PokemonAdapter(ArrayList<Pokemon> pokemons, OnItemClickListener listener) {
        this.pokemons = pokemons;
        this.listener = listener;
        //Añadimos los colores al mapa
        tipoColorMap.put(Pokemon.tipo.ROCA, Color.parseColor("#8B4513"));
        tipoColorMap.put(Pokemon.tipo.PLANTA, Color.GREEN);
        tipoColorMap.put(Pokemon.tipo.FUEGO, Color.RED);
        tipoColorMap.put(Pokemon.tipo.AGUA, Color.BLUE);
        tipoColorMap.put(Pokemon.tipo.VENENO, Color.MAGENTA);
        tipoColorMap.put(Pokemon.tipo.VOLADOR, Color.parseColor("#009ACD"));
        tipoColorMap.put(Pokemon.tipo.BICHO, Color.LTGRAY);
        tipoColorMap.put(Pokemon.tipo.LUCHA, Color.parseColor("#8B0000"));
        tipoColorMap.put(Pokemon.tipo.PSIQUICO, Color.parseColor("#FF69B4"));
        tipoColorMap.put(Pokemon.tipo.HIELO, Color.parseColor("#00BFFF"));
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
        holder.tipos.setText(pintarTipos(pokemon.getTipos()));
        holder.numero.setText(String.valueOf(pokemon.getNumero()));
    }

    // Metodo para pintar los tipos del Pokemon
    private SpannableStringBuilder pintarTipos(Pokemon.tipo[] tipos){
        //Creamos un SpannableStringBuilder para los tipos ya que haremos modificaciones en la cadena añadiendole colores
        SpannableStringBuilder ss = new SpannableStringBuilder(tipos.length == 2? tipos[0].toString() + "/" + tipos[1].toString() : tipos[0].toString());
        int startIndex = 0;
        for (Pokemon.tipo tipo : tipos) {
            ss.setSpan(new ForegroundColorSpan(tipoColorMap.get(tipo)), startIndex, startIndex + tipo.toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            startIndex += tipo.toString().length() + 1;
        }
        return ss;
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder{
        //Elementos de la vista
        ImageView imagen;
        TextView nombre;
        TextView tipos;
        TextView numero;
        // Constructor donde añadimos a la vista los valores de cada uno de los Pokemon
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imageView);
            nombre = itemView.findViewById(R.id.name);
            tipos = itemView.findViewById(R.id.types);
            numero = itemView.findViewById(R.id.number);
            //Establecemos el metodo on click, despues de tiene que implementar donde se cree el adaptador
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onItemClick(pokemons.get(getAdapterPosition())); //El método tendra como atributo un pokemon
                }
            });
        }
    }
}
