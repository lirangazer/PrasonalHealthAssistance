package com.example.prasonalhealthassistance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HealthActivity extends AppCompatActivity {

    private String[] imageNames= {"Chicken Quinoa", "Chile Rellenos", "Rice", "Tuna Pasta", "Best Salad", "Chicken Salad", "Israel Salad", "Salad Savannah"};
    private String[] imageDescreption = {"Great taste", "Note recommend", "My favorite", "Great for diet", "Must try", "Very tasty", "My home", "One last meal"};
    private int[] images = {R.drawable.chicken_quinoa, R.drawable.chile_rellenos, R.drawable.rice, R.drawable.tuna_pasta, R.drawable.best_salad, R.drawable.chicken_salad, R.drawable.israel_salad, R.drawable.salads_savannah };

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<PersonUtils> personUtilsList = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPerson);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        prepareTheList();
        mAdapter = new MyAdapter(this, personUtilsList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);

        //personUtilsList = new ArrayList<>();

        //Adding Data into ArrayList
        //personUtilsList.add(new PersonUtils("Todd Miller","Project Manager","src\\main\\res\\drawable\\food_menu_pic.jpg"));
//        personUtilsList.add(new PersonUtils("Bradley Matthews","Senior Developer"));
//        personUtilsList.add(new PersonUtils("Harley Gibson","Lead Developer"));
//        personUtilsList.add(new PersonUtils("Gary Thompson","Lead Developer"));
//        personUtilsList.add(new PersonUtils("Corey Williamson","UI/UX Developer"));
//        personUtilsList.add(new PersonUtils("Samuel Jones","Front-End Developer"));
//        personUtilsList.add(new PersonUtils("Michael Read","Backend Developer"));
//        personUtilsList.add(new PersonUtils("Robert Phillips","Android Developer"));
//        personUtilsList.add(new PersonUtils("Albert Stewart","Web Developer"));
//        personUtilsList.add(new PersonUtils("Wayne Diaz","Junior Developer"));
//        personUtilsList.add(new PersonUtils("Wayne Diaz","Junior Developer"));
//        personUtilsList.add(new PersonUtils("Wayne Diaz","Junior Developer"));
//        personUtilsList.add(new PersonUtils("Wayne Diaz","Junior Developer"));
//        personUtilsList.add(new PersonUtils("Wayne Diaz","Junior Developer"));
//        personUtilsList.add(new PersonUtils("Wayne Diaz","Junior Developer"));

//        mAdapter = new MyAdapter(this, personUtilsList);
//
//        recyclerView.setAdapter(mAdapter);

    }

    private void prepareTheList()
    {
        int count = 0;
        for (String imageName : imageNames)
        {
            PersonUtils pu = new PersonUtils(imageName, imageDescreption[count] ,images[count]);
            personUtilsList.add(pu);
            count++;
        }
    }
}