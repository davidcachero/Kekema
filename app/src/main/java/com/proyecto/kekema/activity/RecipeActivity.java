package com.proyecto.kekema.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.kekema.R;
import com.proyecto.kekema.adapter.CarouselRecipeAdapter;
import com.proyecto.kekema.adapter.RecipeAdapter;
import com.proyecto.kekema.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Recipe> recipes;
    private List<Recipe> carouselRecipes;
    private RecipeAdapter adapter;
    private FeatureCoverFlow coverFlow;
    private CarouselRecipeAdapter carouselAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        recyclerView = findViewById(R.id.recipe_recycler_view);
        coverFlow = (FeatureCoverFlow) findViewById(R.id.recipe_carousel_coverflow);
        recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("Pollito con arroz", "Descrpción", R.drawable.pollo_con_arroz));
        recipes.add(new Recipe("Albóndigas con tomate", "Descrpción", R.drawable.albondigas_tomate));
        recipes.add(new Recipe("Ensalada de lentejas", "Descrpción", R.drawable.ensalada_lentejas));
        recipes.add(new Recipe("Pasta carbonara", "Descrpción", R.drawable.pasta_carbonara));
        recipes.add(new Recipe("Conejo en salsa", "Descrpción", R.drawable.conejo_salsa));
        buildRecyclerRecipes();
        dummyData();
        buildRecyclerCarousel();
    }

    private void buildRecyclerRecipes(){
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

    private void dummyData() {
        carouselRecipes = new ArrayList<Recipe>();
        carouselRecipes.add(new Recipe("Pollito con arroz", "Descrpción", R.drawable.pollo_con_arroz));
        carouselRecipes.add(new Recipe("Albóndigas con tomate", "Descrpción", R.drawable.albondigas_tomate));
        carouselRecipes.add(new Recipe("Ensalada de lentejas", "Descrpción", R.drawable.ensalada_lentejas));
        carouselRecipes.add(new Recipe("Pasta carbonara", "Descrpción", R.drawable.pasta_carbonara));
        carouselRecipes.add(new Recipe("Conejo en salsa", "Descrpción", R.drawable.conejo_salsa));
    }

    private void buildRecyclerCarousel(){
        carouselAdapter = new CarouselRecipeAdapter(this, (ArrayList<Recipe>) carouselRecipes);
        coverFlow.setAdapter(carouselAdapter);
        coverFlow.setOnScrollPositionListener(onScrollListener());
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("RecipeActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("RecipeActivity", "scrolling");
            }
        };
    }
}