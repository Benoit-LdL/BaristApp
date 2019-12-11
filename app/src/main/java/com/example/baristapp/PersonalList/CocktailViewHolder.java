package com.example.baristapp.PersonalList;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baristapp.R;

public class CocktailViewHolder extends RecyclerView.ViewHolder {

    View view;
    TextView textName,textCategory, textTime;
    CardView noteCard;

    public CocktailViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.cocktail_name);
        textCategory = itemView.findViewById(R.id.cocktail_category);
        textTime = itemView.findViewById(R.id.cocktail_time);
        noteCard = itemView.findViewById(R.id.cocktail_card);
    }

    public void setCocktailname(String name) {
        textName.setText(name);
    }

    public void setCocktailCategory(String category) {
        textCategory.setText(category);
    }

    public void setCocktailTime(String time) {
        textTime.setText(time);
    }
}
