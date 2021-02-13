package com.example.prasonalhealthassistance;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class HealthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_single_choice);
        ListView list = (ListView) findViewById(R.id.listview1);
        adapter.add("Item 1");
        adapter.add("Item 2");
        adapter.add("Item 3");
        list.setAdapter(adapter);
    }
}