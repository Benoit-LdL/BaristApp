package com.example.baristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HoofdMenuActivity extends AppCompatActivity {
    private static final String LOG = HoofdMenuActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoofdmenu);
        Log.d(LOG, "-------");
        Log.d(LOG, "Hoofdmenu");
    }
    public void launchLijst(View view) {
        Log.d(LOG, "Button clicked!");
        Intent intent = new Intent(this, LijstCocktailsActivity.class);
        startActivity(intent);
        Log.d(LOG, "-------");
        Log.d(LOG, "onCreate Lijst");
    }
    public void launchEigenLijst(View view) {
        Log.d(LOG, "Button clicked!");
        Intent intent = new Intent(this, EigenLijstCocktailActivity.class);
        startActivity(intent);
        Log.d(LOG, "-------");
        Log.d(LOG, "onCreate EigenLijst");
    }
    public void launchMaken(View view) {
        Log.d(LOG, "Button clicked!");
        Intent intent = new Intent(this, MakenCocktailActivity.class);
        startActivity(intent);
        Log.d(LOG, "-------");
        Log.d(LOG, "onCreate Maken");
    }
}