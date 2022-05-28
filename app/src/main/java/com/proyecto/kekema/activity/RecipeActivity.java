package com.proyecto.kekema.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.proyecto.kekema.R;
import com.proyecto.kekema.adapter.RecipeAdapter;
import com.proyecto.kekema.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Recipe> recipes;
    private RecipeAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        recyclerView = findViewById(R.id.recipe_recycler_view);
        recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("Pollito con arroz"));
        recipes.add(new Recipe("Albóndigas con tomate"));
        recipes.add(new Recipe("Ensalada de lentejas"));
        recipes.add(new Recipe("Pasta carbonara"));
        recipes.add(new Recipe("Conejo en salsa"));
        buildRecycler();
    }

    private void buildRecycler(){
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipeAdapter((ArrayList<Recipe>) recipes);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecipeActivity.this, recipes.get(position).getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}