package com.proyecto.kekema.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.kekema.R;
import com.proyecto.kekema.model.Recipe;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private ArrayList<Recipe> recipeList;
    private OnItemClickListener mListener;

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView textDescription;

        public RecipeViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textDescription = itemView.findViewById(R.id.text_view_recipe_description);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }

    public RecipeAdapter(ArrayList<Recipe> itemList) {
        this.recipeList = itemList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe currentItem = recipeList.get(position);
        holder.textDescription.setText(currentItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
