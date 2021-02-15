package com.example.prasonalhealthassistance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HealthActivity extends AppCompatActivity {

    private String[] imageNames= {"Chicken Quinoa", "Chile Rellenos", "Rice", "Tuna Pasta", "Best Salad", "Chicken Salad", "Israel Salad", "Salad Savannah"};
    private String[] imageDescription = {"Great taste", "Note recommend", "My favorite", "Great for diet", "Must try", "Very tasty", "My home", "One last meal"};
    private int[] images = {R.drawable.chicken_quinoa, R.drawable.chile_rellenos, R.drawable.rice, R.drawable.tuna_pasta, R.drawable.best_salad, R.drawable.chicken_salad, R.drawable.israel_salad, R.drawable.salads_savannah };

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<RecyclerUtils> recyclerUtilsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        recyclerView = findViewById(R.id.recyclerViewHealth);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        prepareTheList();
        recyclerAdapter = new RecyclerAdapter(this, recyclerUtilsList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    //Initialize the Recycler list object to hold the image and the relevant text
    private void prepareTheList()
    {
        int count = 0;
        for (String imageName : imageNames)
        {
            RecyclerUtils pu = new RecyclerUtils(imageName, imageDescription[count] ,images[count]);
            recyclerUtilsList.add(pu);
            count++;
        }
    }
}