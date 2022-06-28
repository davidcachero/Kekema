package com.proyecto.kekema.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.kekema.R;
import com.proyecto.kekema.adapter.IngredientsAdapter;
import com.proyecto.kekema.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeDescriptionActivity extends AppCompatActivity {
    private List<String> ingredients;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private IngredientsAdapter adapter;
    private ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_description);
        recipes = getIntent().getParcelableArrayListExtra("recipe");
        ingredients = recipes.stream().flatMap(recipe -> recipe.getIngredients().stream()).collect(Collectors.toList());
        recyclerView = findViewById(R.id.ingredient_recycler_view);
        buildRecyclerIngredients();
        ImageView imageView = findViewById(R.id.image_recipe_description);
        imageView.setImageResource(R.drawable.pollo_con_arroz);
        TextView textViewDescription = findViewById(R.id.description_text_view);
        TextView textViewElaboration = findViewById(R.id.elaboration_text_view);
        textViewDescription.setText("Hoy os explicamos cómo hacer arroz con pollo, un plato muy sencillo y económico con pocos ingredientes, que va a triunfar en vuestra casa.");
        textViewElaboration.setText("1. Salteamos el pollo.\n2. Incorporamos el arroz.\n3. Cocinamos a fuego lento durante 18 minutos.");
    }

    private void buildRecyclerIngredients() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new IngredientsAdapter((ArrayList<String>) ingredients);
        recyclerView.setAdapter(adapter);
    }
}