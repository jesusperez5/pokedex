package com.odeene.pokedex;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Declaramos el array de pokemons
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>(Arrays.asList(
                new Pokemon("Bulbasaur", 1, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Starter de tipo planta", R.drawable.bulbasaur),
                new Pokemon("Ivysaur", 2, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "La evolución de Bulbasaur.", R.drawable.ivysaur),
                new Pokemon("Venusaur", 3, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Evolución de Ivysaur", R.drawable.venusaur),
                new Pokemon("Charmander",4, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Starter de tipo fuego", R.drawable.charmander),
                new Pokemon("Charmeleon", 5, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "La evolución de Charmander.", R.drawable.charmeleon),
                new Pokemon("Charizard", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO, Pokemon.tipo.VOLADOR}, "Dragon que es la evolución de Charmeleon, no es de tipo dragon :D", R.drawable.charizard),
                new Pokemon("Squirtle", 7, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Starter de tipo agua.", R.drawable.squirtle),
                new Pokemon("Wartortle", 8, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Evolución de Squirtle.", R.drawable.wartortle),
                new Pokemon("Blastoise", 9, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Evolución de Wartortle, aunque este se merece más el título de Wartortle.", R.drawable.blastoise),
                new Pokemon("Vulpix", 37, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Su nombre proviene del latín vulpes(zorro) y del ingles six(seis)", R.drawable.vulpix),
                new Pokemon("Ninetales", 38, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Evolución de vulpix, hacer referencia al Kyubi de la mitología japonesa.", R.drawable.ninetales),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard),
                new Pokemon("Ninetales", 6, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "", R.drawable.charizard)


                ));
        //creamos el adaptador
        PokemonAdapter pokemonAdapter = new PokemonAdapter(pokemons, this);
        //creamos el recyclerView
        RecyclerView recyclerView = findViewById(R.id.pokedex);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Establecemos el adaptador en el recyclerView
        recyclerView.setAdapter(pokemonAdapter);

        EditText filterInput = findViewById(R.id.filterInput);

        Button limpiar = findViewById(R.id.clearButton);
        //Evento al darle click en limpiar
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Comprobacion", "Texto del filtro antes de ser eliminado: " + filterInput.getText());
                filterInput.setText("");
                Log.i("Comprobacion", "Texto del filtro eliminado: " + filterInput.getText());
            }
        });

        Button filtrar = findViewById(R.id.filterButton);
        //evento al darle click en filtrar
        filtrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch fuego = findViewById(R.id.fireButton);
                Switch planta = findViewById(R.id.grassButton);
                Switch agua = findViewById(R.id.waterButton);
                //traemos todos los switch para ver si estan o no marcados
                ArrayList<Pokemon.tipo> tipos = new ArrayList<>(); //array de los tipos marcados
                ArrayList<Pokemon> pokemonsFiltered = new ArrayList<>(pokemons); //array de los pokemons resultantes, de primeras se inicializa con los mismos datos que el array de pokemons
                String nombreFiltro = filterInput.getText().toString();
                if(agua.isChecked()) // vamos añadiendo cada tipo al array
                    tipos.add(Pokemon.tipo.AGUA);
                if(planta.isChecked())
                    tipos.add(Pokemon.tipo.PLANTA);
                if(fuego.isChecked())
                    tipos.add(Pokemon.tipo.FUEGO);

                if(tipos.size() > 0 && tipos.size() < 3 || !nombreFiltro.equals("")){ // si esta 1 o dos marcados o bien hay un nombre para filtrar...
                    if(tipos.size() > 0 && tipos.size() < 3) { // si hay 1 o 2 tipos... (esto podria haberlo echo sin la condicion de menor de 3 pero no le veo sentido)
                       try {
                           pokemonsFiltered = pokemonsFiltered.stream() // podria haberlo filtrado con bucles pero quise usar programación funcional
                                   .filter(pokemon -> Arrays.stream(pokemon.getTipos()).anyMatch(tipos::contains))
                                   .collect(Collectors.toCollection(ArrayList::new));
                       } catch (Exception e) {
                           Log.e("ERROR", "Error filtrando por tipos, error en cuestion: " + e.getMessage());
                       }
                    }
                    if(!nombreFiltro.equals("")){ //si hay un valor en el filtro
                        try {
                            pokemonsFiltered = pokemonsFiltered.stream()
                                    .filter(pokemon -> pokemon.getNombre().toLowerCase().contains(nombreFiltro.toLowerCase()))
                                    .collect(Collectors.toCollection(ArrayList::new));
                        } catch (Exception e) {
                            Log.e("ERROR", "Error filtrando por nombre, error en cuestion: " + e.getMessage());
                        }
                    }
                    PokemonAdapter pokemonFilteredAdapter = new PokemonAdapter(pokemonsFiltered, MainActivity.this); // creamos el nuevo adapter con los pokemons filtrados
                    recyclerView.setAdapter(pokemonFilteredAdapter);
                    Toast.makeText(MainActivity.this, "Filtro realizado", Toast.LENGTH_SHORT).show();
                } else {
                    recyclerView.setAdapter(pokemonAdapter);
                }
            }
        });

    }

    @Override
    public void onItemClick(Pokemon pokemon) { // Evento para cuando clickas en un pokemon
        Log.i("Comprobacion", "Click en un pokemon");
        Toast.makeText(this, pokemon.getDescripcion(), Toast.LENGTH_SHORT).show();
    }
}