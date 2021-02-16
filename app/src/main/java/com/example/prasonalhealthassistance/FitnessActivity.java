package com.example.prasonalhealthassistance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FitnessActivity extends AppCompatActivity {

    private String[] imageNames= {"Back pull", "Dumbbell shoulders", "Dumbbell pressup", "Pullups", "Squads", "Triceps", "Triceps down"};
    private String[] imageDescription = {"For your back", "Great for shoulders", "This hard", "Not recommend", "For big legs", "For cool hands", "For cool hands 2"};
    private int[] images = {R.drawable.back_exe, R.drawable.dumbbell, R.drawable.dumbbell_pressup, R.drawable.pullup, R.drawable.squat, R.drawable.triceps, R.drawable.triceps_dwon };

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<RecyclerUtils> recyclerUtilsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        recyclerView = findViewById(R.id.recyclerViewFitness);
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