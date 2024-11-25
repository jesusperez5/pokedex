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
                new Pokemon("Ninetales", 38, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Evolución de Vulpix, hacer referencia al Kyubi de la mitología japonesa.", R.drawable.ninetales),
                new Pokemon("Oddish", 43, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Su nombre proviene del ingles odd (raro) y radish (rábano)", R.drawable.oddish),
                new Pokemon("Gloom", 44, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Evolución de Oddish, posee en su cabeza una flor parecida a la rafflesia pricei", R.drawable.gloom),
                new Pokemon("Vileplume", 45, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Evoluciona de Gloom utilizando una piedra hoja.", R.drawable.vileplume),
                new Pokemon("Paras", 46, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.BICHO}, "Su nombre es un truncamiento de la palabra inglesa parasite (parásito).", R.drawable.paras),
                new Pokemon("Parasect", 47, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.BICHO}, "Evolución de Paras, su diseño esta basado en un canguejo ermitaño", R.drawable.parasect),
                new Pokemon("Psyduck",  54, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Su nombre proviene del ingles psychic (psiquico) y duck (pato).", R.drawable.psyduck),
                new Pokemon("Golduck", 55, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Evolución de psyduck, se dice que tiene los nombre invertidos.", R.drawable.golduck),
                new Pokemon("Growlithe", 58, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Su diseño está basado en un Shisa, de la mitología japonesa.", R.drawable.growlithe),
                new Pokemon("Arcanine", 59, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Evolución de Growlithe, su llama puede alcanzer los 3000 ºC.", R.drawable.arcanine),
                new Pokemon("Poliwag", 60, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Tiene aspecto de renacuajo azul", R.drawable.poliwag),
                new Pokemon("Poliwhirl", 61, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Evolución de Poliwag, la espiral de su vientre cambia de sentido.", R.drawable.poliwhirl),
                new Pokemon("Poliwrath", 62, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.LUCHA}, "Evolución de Poliwrath, al evolucionar gana el tipo lucha.", R.drawable.poliwrath),
                new Pokemon("Bellsprout", 69, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "El nombre viene de las palabras en inglés bell (campana) y sprout (brote).", R.drawable.bellsprout),
                new Pokemon("Weepinbell", 70, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Evolución de Bellsprout, tiene dos hojas afiladas que utiliza para comer.", R.drawable.weepinbell),
                new Pokemon("Victreebell", 71, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Evolución de Weepinbell, esta basado en una planta carnívora.", R.drawable.victreebel),
                new Pokemon("Tentacool", 72, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.VENENO}, "Está basado en una medusa con rasgos cefalópodos.", R.drawable.tentacool),
                new Pokemon("Tentacruel", 73, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.VENENO}, "Evolución de Tentacool, también conocido como el gangster del mar.", R.drawable.tentacruel),
                new Pokemon("Ponyta", 77, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Al nacer es un poco débil y lento, pero a los pocos minutos se hacen fuerte.", R.drawable.ponyta),
                new Pokemon("Rapidash", 78, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Evolución de Ponyta, está basado en un unicornio con llamas", R.drawable.rapidash),
                new Pokemon("Slowpoke", 79, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.PSIQUICO}, "Su nombre proviene del ingles Slowpoke (persona lenta de movimientos).", R.drawable.slowpoke),
                new Pokemon("Slowbro", 80, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.PSIQUICO}, "Evolución de Slowbro, tiene un Shellder en la cola.", R.drawable.slowbro),
                new Pokemon("Seel", 86, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Está basado en una cría de foca pía.", R.drawable.seel),
                new Pokemon("Dewgong", 87, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.HIELO}, "Evolución de seel, es totalmente inmune al frío intenso.", R.drawable.dewgong),
                new Pokemon("Shellder", 90, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Suele utilizar tenaza para aferrarse a la cola de algún Slowpoke.", R.drawable.shellder),
                new Pokemon("Cloyster", 91, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.HIELO}, "Evolucion de shellder, se dice que su concha es más dura que el diamante.", R.drawable.cloyster),
                new Pokemon("Kraby", 98, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Su nombre proviene del inglés crab (cangrejo) y crabby (gruñón).", R.drawable.krabby),
                new Pokemon("Kingler", 99, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Evolución de Krabby, tiene una pinza 3 veces maás grande que la otra.", R.drawable.kingler),
                new Pokemon("Exeggcute", 102, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.PSIQUICO}, "Su nombre proviene del ingles execute (ejecutar) y egg (huevo).", R.drawable.exeggcute),
                new Pokemon("Exeggutor", 103, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.PSIQUICO}, "Evolución de Exeggcute,su diseño esta basado en un árbol Jinmenju.", R.drawable.exeggutor),
                new Pokemon("Tangela", 114, new Pokemon.tipo[]{Pokemon.tipo.PLANTA}, "Su diseño esta basado en la deidad Medusa de la mitología griega.", R.drawable.tangela),
                new Pokemon("Horsea", 116, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Su nombre proviene del ingles seahorse (caballito de mar).", R.drawable.horsea),
                new Pokemon("Seadra", 117, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Evolución de Horsea, su naturaleza se vuelve cruel al evolucionar.", R.drawable.seadra),
                new Pokemon("Gooldeen", 118, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Su nombre viene de las palabras goldfish (carpa dorada) y queen (reina).", R.drawable.goldeen),
                new Pokemon("Seaking", 119, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Evolucion de Gooldeen, En su boca ahora sobresalen dos colmillos.", R.drawable.seaking),
                new Pokemon("Staryu", 120, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Su diseño esta basado en una estrella de mar.", R.drawable.staryu),
                new Pokemon("Starmie", 121, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.PSIQUICO}, "Evolución de Staryu, si está débil su núcleo empezará a parpadear.", R.drawable.starmie),
                new Pokemon("Magmar", 126, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Está basado en la criatura mitológica Karura.", R.drawable.magmar),
                new Pokemon("Magikarp", 129, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Está inspirado en una antigua leyenda china conocida como \"Li yu tiao Long men\".", R.drawable.magikarp),
                new Pokemon("Gyarados", 130, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.VOLADOR}, "Evolución de Magikarp, se asemeja a un dragón marino.", R.drawable.gyarados),
                new Pokemon("Lapras", 131, new Pokemon.tipo[]{Pokemon.tipo.AGUA, Pokemon.tipo.HIELO}, "Está basado tanto en un plesiosaurio como en el monstruo del Lago Ness.", R.drawable.lapras),
                new Pokemon("Vaporeon", 134, new Pokemon.tipo[]{Pokemon.tipo.AGUA}, "Evolución de Eevee, su nombre proviene del español vapor.", R.drawable.vaporeon),
                new Pokemon("Flareon", 136, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "Evolución de Eevee, puede almacenar enegía termica en su interior.", R.drawable.flareon),
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