package com.odeene.pokedex;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

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
        Log.i(MainActivity.this.getString(R.string.comprobacion), getString(R.string.creando_activity));
        PokedexFragment pokedexFragment = new PokedexFragment();
        NextFragment nextFragment = new NextFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView3, pokedexFragment)
                .commit();
        BottomNavigationView menu = findViewById(R.id.nav);
        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menuPokedex) {
                    if (savedInstanceState == null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainerView3, pokedexFragment)
                                .commit();
                    }
                    return true;
                } else if (item.getItemId() == R.id.menuProx) {
                    if (savedInstanceState == null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainerView3, nextFragment)
                                .commit();
                    }
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MainActivity.this.getString(R.string.comprobacion), getString(R.string.parando_aplicacion));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MainActivity.this.getString(R.string.comprobacion), getString(R.string.saliendo_de_la_aplicacion));
    }
}