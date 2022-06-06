package com.proyecto.kekema.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.proyecto.kekema.R;
import com.proyecto.kekema.model.Recipe;

import java.util.ArrayList;

public class CarouselRecipeAdapter extends BaseAdapter {
    private ArrayList<Recipe> recipeArrayList;
    private AppCompatActivity activity;

    public CarouselRecipeAdapter(AppCompatActivity context, ArrayList<Recipe> objects) {
        this.activity = context;
        this.recipeArrayList = objects;
    }
    @Override
    public int getCount() {
        return recipeArrayList.size();
    }

    @Override
    public Recipe getItem(int i) {
        return recipeArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.carousel_recipe_item, null, false);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.recipeImage.setImageResource(recipeArrayList.get(i).getImage());
        viewHolder.recipeName.setText(recipeArrayList.get(i).getName());

        view.setOnClickListener(onClickListener(i));

        return view;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(activity);
                dialog.setContentView(R.layout.dialog_recipe_info);
                dialog.setCancelable(true); // dimiss when touching outside
                dialog.setTitle("Recipe Details");

                TextView text = (TextView) dialog.findViewById(R.id.name);
                text.setText(getItem(position).getName());
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(getItem(position).getImage());

                dialog.show();
            }
        };
    }


    private static class ViewHolder {
        private TextView recipeName;
        private ImageView recipeImage;

        public ViewHolder(View v) {
            recipeImage = (ImageView) v.findViewById(R.id.image);
            recipeName = (TextView) v.findViewById(R.id.name);
        }
    }
}
