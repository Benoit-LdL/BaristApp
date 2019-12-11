package com.example.baristapp.PersonalList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baristapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PersonalList_NewCocktailActivity extends AppCompatActivity {

    private Button btnCreate;
    private EditText etName,etID,etCategory,etIngredients,etImage;
    private Toolbar toolbar;

    private FirebaseAuth fAuth;
    private DatabaseReference fCocktailDB;

    private Menu mainMenu;
    private String cocktailId = "";

    private boolean isExisting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_list_new_note_activity);

        btnCreate = findViewById(R.id.btn_createCocktail);

        etName =  findViewById(R.id.new_cocktail_name);
        etCategory = findViewById(R.id.new_cocktail_category);
        etIngredients =  findViewById(R.id.new_cocktail_ingredients);
        //etID =
        //etImage =

        toolbar = findViewById(R.id.new_cocktail_toolbar);

        try
        {
            cocktailId = getIntent().getStringExtra("noteId");
            if (!cocktailId.trim().equals(""))
                isExisting=true;
            else
                isExisting=false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fAuth = FirebaseAuth.getInstance();
        fCocktailDB = FirebaseDatabase.getInstance().getReference().child("PersonalList").child(fAuth.getCurrentUser().getUid());

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String ID = "none";
                String category = etCategory.getText().toString().trim();
                String ingredients = etIngredients.getText().toString().trim();
                //String image

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(category) && !TextUtils.isEmpty(ingredients))
                    createNote(name,ID,category,ingredients); //add image
                else
                    Snackbar.make(view,"Fill empty fields!", Snackbar.LENGTH_SHORT).show();
            }
        });
        putData();
    }

    private void putData()
    {
        if (isExisting) {
            fCocktailDB.child(cocktailId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("name")
                            && dataSnapshot.hasChild("cocktail_id")
                            && dataSnapshot.hasChild("category")
                            && dataSnapshot.hasChild("ingredients")
                            //&& dataSnapshot.hasChild("image"))
                    ) {
                        String name = dataSnapshot.child("name").getValue().toString();
                        String ID = dataSnapshot.child("cocktail_id").getValue().toString();
                        String category = dataSnapshot.child("category").getValue().toString();
                        String ingredients = dataSnapshot.child("ingredients").getValue().toString();
                        //String image = dataSnapshot.child("image").getValue().toString();
                        etName.setText(name);
                        etCategory.setText(category);
                        etIngredients.setText(ingredients);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });
        }
    }

    private void createNote(String name,String ID,String category, String ingredients){
        if (fAuth.getCurrentUser() != null)
        {
            if (isExisting) //UPDATE NOTE
            {
                Map updateMap = new HashMap();
                updateMap.put("name",etName.getText().toString().trim());
                //updateMap.put("cocktail_id",etID.getText().toString().trim());
                updateMap.put("category",etCategory.getText().toString().trim());
                updateMap.put("ingredients",etIngredients.getText().toString().trim());
                updateMap.put("timestamp",ServerValue.TIMESTAMP);
                //updateMap.put("image",etContent.getText().toString().trim());
                fCocktailDB.child(cocktailId).updateChildren(updateMap);
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            }
            else //CREATE NEW NOTE
            {
                final DatabaseReference newNoteRef = fCocktailDB.push();
                final Map noteMap = new HashMap();
                noteMap.put("name", name);
                noteMap.put("cocktail_id", ID);
                noteMap.put("category", category);
                noteMap.put("ingredients", ingredients);
                //noteMap.put("image", content);
                noteMap.put("timestamp", ServerValue.TIMESTAMP);
                Thread mainThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        newNoteRef.setValue(noteMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())
                                    Toast.makeText(PersonalList_NewCocktailActivity.this, "Note added to Database", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(PersonalList_NewCocktailActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                mainThread.start();
            }
        }
        else
        {
            Toast.makeText(this,"User is not signed in",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.personal_list_new_note_menu,menu);
        mainMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
            case R.id.new_cocktail_delete_btn:
                if (isExisting)
                    deleteNote();
                else
                    Toast.makeText(this, "Nothing to delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    private void deleteNote()
    {
        fCocktailDB.child(cocktailId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(PersonalList_NewCocktailActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                    cocktailId = "no";
                    finish();
                }
                else
                {
                    Log.e("NewNoteActivity",task.getException().toString());
                    Toast.makeText(PersonalList_NewCocktailActivity.this, "ERROR: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
