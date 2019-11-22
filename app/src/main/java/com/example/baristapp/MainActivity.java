package com.example.baristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String LOG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void launchHoofdmenu(View view) {
        Log.d(LOG, "Button clicked!");
        Intent intent = new Intent(this, HoofdMenuActivity.class);
        startActivity(intent);
        Log.d(LOG, "-------");
        Log.d(LOG, "onCreate Hoofdmeneu");
    }

}
