package com.example.baristapp.PersonalList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.baristapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PersonalList_MainActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    private RecyclerView cocktailsList;
    private GridLayoutManager gridLayoutManager;
    private DatabaseReference cocktailsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_list_main_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        cocktailsList = findViewById(R.id.main_cocktail_list);

        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        cocktailsList.setHasFixedSize(true);
        cocktailsList.setLayoutManager(gridLayoutManager);
        cocktailsList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null)
            cocktailsDB = FirebaseDatabase.getInstance().getReference().child("PersonalList").child(fAuth.getCurrentUser().getUid());
        updateUI();
        loadData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void loadData()
    {
        Query query = cocktailsDB.orderByValue();
        FirebaseRecyclerOptions<CocktailModel> options = new FirebaseRecyclerOptions.Builder<CocktailModel>()
                .setQuery(query, CocktailModel.class)
                .setLifecycleOwner(this)
                .build();

        FirebaseRecyclerAdapter<CocktailModel, CocktailViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<CocktailModel, CocktailViewHolder>(options) {
                    @Override
                    public CocktailViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
                    {
                        return new CocktailViewHolder(LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.personal_list_single_note, parent, false));
                    }

                    @Override
                    protected void onBindViewHolder(
                            @NonNull final CocktailViewHolder viewHolder,
                            int position,
                            @NonNull CocktailModel model)
                    {
                        final String noteId = getRef(position).getKey();
                        cocktailsDB.child(noteId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild("name") && dataSnapshot.hasChild("category")&& dataSnapshot.hasChild("timestamp"))
                                {
                                    String name = dataSnapshot.child("name").getValue().toString();
                                    String category = dataSnapshot.child("category").getValue().toString();
                                    String timestamp = dataSnapshot.child("timestamp").getValue().toString();

                                    viewHolder.setCocktailname(name);
                                    viewHolder.setCocktailCategory(category);

                                    GetTime getTime = new GetTime();
                                    viewHolder.setCocktailTime(getTime.getTime(Long.parseLong(timestamp),getApplicationContext()));

                                    viewHolder.CocktailCard.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(PersonalList_MainActivity.this, PersonalList_NewCocktailActivity.class);
                                            intent.putExtra("noteId",noteId);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });
                    }
                };
        cocktailsList.setAdapter(firebaseRecyclerAdapter);
    }

    private void updateUI()
    {
        if (fAuth.getCurrentUser() != null)
            Log.i("MainActivity","fAuth != null");
        else
        {
           // Intent startIntent = new Intent(MainActivity.this,StartActivity.class);
          //  startActivity(startIntent);
         //   finish();
            Log.i("MainActivity","fAuth == null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.personal_list_main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.btn_createCocktail:
                Intent newIntent = new Intent(PersonalList_MainActivity.this, PersonalList_NewCocktailActivity.class);
                startActivity(newIntent);
                break;
        }
        return true;
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
