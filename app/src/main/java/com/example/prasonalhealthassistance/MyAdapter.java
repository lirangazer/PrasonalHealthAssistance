package com.example.prasonalhealthassistance;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class MyAdapter extends ArrayAdapter<String>  {
    public MyAdapter(@NonNull Context context, int resource) {
        super(context, R.layout.listviwe_item);
    }
}
