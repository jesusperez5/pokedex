package com.odeene.pokedex;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

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

        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>(Arrays.asList(
                new Pokemon("Charmander",1, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "hola", R.drawable.charmander),
                new Pokemon("Charmeleon", 2, new Pokemon.tipo[]{Pokemon.tipo.FUEGO}, "La evolución de Charmander.", R.drawable.charmeleon),
                new Pokemon("Charizard", 3, new Pokemon.tipo[]{Pokemon.tipo.FUEGO, Pokemon.tipo.VOLADOR}, "Un Pokémon dragón de fuego.", R.drawable.charizard),
                new Pokemon("Bulbasaur", 4, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Un Pokémon de tipo planta y veneno.", R.drawable.bulbasaur),
                new Pokemon("Ivysaur", 5, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "La evolución de Bulbasaur.", R.drawable.ivysaur),
                new Pokemon("Venusaur", 6, new Pokemon.tipo[]{Pokemon.tipo.PLANTA, Pokemon.tipo.VENENO}, "Un Pokémon de tipo planta y veneno.", R.drawable.venusaur)
                ));

        PokemonAdapter pokemonAdapter = new PokemonAdapter(pokemons);

        RecyclerView recyclerView = findViewById(R.id.pokedex);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(pokemonAdapter);
    }
}