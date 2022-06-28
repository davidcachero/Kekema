package com.proyecto.kekema.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.kekema.R;

import java.util.ArrayList;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {
    private ArrayList<String> ingredientList;

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        public TextView textIngredient;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            textIngredient = itemView.findViewById(R.id.text_view_ingredient);
        }

    }

    public IngredientsAdapter(ArrayList<String> itemList) {
        this.ingredientList = itemList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        IngredientViewHolder viewHolder = new IngredientViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        String currentItem = ingredientList.get(position);
        holder.textIngredient.setText("-" + currentItem);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
}
