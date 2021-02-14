package com.example.prasonalhealthassistance;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HealthActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<PersonUtils> personUtilsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPerson);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        personUtilsList = new ArrayList<>();

        //Adding Data into ArrayList
        personUtilsList.add(new PersonUtils("Todd Miller","Project Manager","src\\main\\res\\drawable\\food_menu_pic.jpg"));
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

        mAdapter = new MyAdapter(this, personUtilsList);

        recyclerView.setAdapter(mAdapter);

    }
}