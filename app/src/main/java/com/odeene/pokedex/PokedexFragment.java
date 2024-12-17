package com.odeene.pokedex;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PokedexFragment extends Fragment {

    PokemonAdapter pokemonAdapter;

    public void setPokemonAdapter(PokemonAdapter pokemonAdapter) {
        this.pokemonAdapter = pokemonAdapter;
    }

    public PokedexFragment() {

    }

    public PokedexFragment(PokemonAdapter pokemonAdapter) {
        this.pokemonAdapter = pokemonAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokedex, container, false);
        RecyclerView rvComics = view.findViewById(R.id.pokedexrvf);
        rvComics.setAdapter(pokemonAdapter);
        rvComics.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

}