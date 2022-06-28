package com.proyecto.kekema.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.proyecto.kekema.R;
import com.proyecto.kekema.adapter.CarouselRecipeAdapter;
import com.proyecto.kekema.adapter.RecipeAdapter;
import com.proyecto.kekema.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Recipe> recipes;
    private List<Recipe> carouselRecipes;
    private RecipeAdapter adapter;
    private FeatureCoverFlow coverFlow;
    private CarouselRecipeAdapter carouselAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String idVideo;
    private String recipeName;
    private String description;
    private String elaboration;
    private ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        recyclerView = findViewById(R.id.recipe_recycler_view);
        carouselRecipes = new ArrayList<Recipe>();
        coverFlow = (FeatureCoverFlow) findViewById(R.id.recipe_carousel_coverflow);
        recipes = new ArrayList<Recipe>();
        ingredients = new ArrayList<>();
        getRecipesFromDataBase();
        buildRecyclerRecipes();
        List<Recipe> favRecipes = recipes.stream().filter(fav -> fav.getFav() == R.drawable.corazon_fav_lleno).collect(Collectors.toList());
        if (!favRecipes.isEmpty()) {
            carouselRecipes.addAll(favRecipes);
        }
        buildRecyclerCarousel();
    }

    private List<Recipe> getRecipesFromDataBase() {
        db.collection("recetas").document("arroz-con-pollo").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                idVideo = documentSnapshot.get("idVideo").toString();
                recipeName = documentSnapshot.get("nombre").toString();
                description = "Hoy os explicamos cómo hacer arroz con pollo, un plato muy sencillo y económico con pocos ingredientes, que va a triunfar en vuestra casa.";
                elaboration = "1. Salteamos el pollo." + "\n" + "2. Incorporamos el arroz" + "\n" + "3. Cocinamos a fuego lento durante 18 minutos";
                ingredients.add("Pollo troceado");
                ingredients.add("Ajo");
            }
        });
        recipes.add(new Recipe(recipeName, description, elaboration, ingredients, R.drawable.pollo_con_arroz, R.drawable.corazon_fav_lleno, idVideo));
        return recipes;
    }

    private void buildRecyclerRecipes() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecipeAdapter((ArrayList<Recipe>) recipes);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(RecipeActivity.this, RecipeDescriptionActivity.class).putParcelableArrayListExtra("recipe", (ArrayList<? extends Parcelable>) recipes));
            }
        });
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
                Log.v("RecipeActivity", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("RecipeActivity", "scrolling");
            }
        };
    }
}