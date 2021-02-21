package com.example.prasonalhealthassistance;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserView extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<RecyclerUtils> recyclerUtilsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        String userName = SharedPref.read("user", "");
        readData(new FireBaseCallback() {
            @Override
            public void onCallback(DataSnapshot snap) {

                for(DataSnapshot snapshot : snap.child("Products").getChildren())
                {
                    RecyclerUtils product = snapshot.getValue(RecyclerUtils.class);
                    recyclerUtilsList.add(product);

                }
                setupLayOut();
            }

        },  userName);

    }

    private void readData(UserView.FireBaseCallback fireBaseCallback, String userName) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference UsersDB = database.getReference("Users").child(userName);

        UsersDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fireBaseCallback.onCallback(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private interface FireBaseCallback {
        void onCallback(DataSnapshot snapshot);
    }

    private void setupLayOut()
    {
        recyclerView = findViewById(R.id.recyclerViewUserView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerAdapter = new RecyclerAdapter(this, recyclerUtilsList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}