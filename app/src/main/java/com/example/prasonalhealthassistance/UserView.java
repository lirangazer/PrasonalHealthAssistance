package com.example.prasonalhealthassistance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserView extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<RecyclerUtils> recyclerUtilsList = new ArrayList<>();
    UserModel userDB = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        String userName = SharedPref.read("user", "");

        readData(new FireBaseCallback() {
            @Override
            public void onCallback(DataSnapshot snap) {



//                //Save new user to the DB
                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference userToAddRef = database.getReference("Users");
//                userToAddRef.child("Users").child(userName);

            }
        },  userName);

        recyclerView = findViewById(R.id.recyclerViewUserView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        prepareTheList();
        recyclerAdapter = new RecyclerAdapter(this, recyclerUtilsList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    //Initialize the Recycler list object to hold the image and the relevant text
    private void prepareTheList() {
//        int count = 0;
//        for (String imageName : imageNames)
//        {
//            RecyclerUtils pu = new RecyclerUtils(imageName, imageDescription[count] ,images[count]);
//            recyclerUtilsList.add(pu);
//            count++;
//        }
    }


    private void readData(UserView.FireBaseCallback fireBaseCallback, String userName) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
       //DatabaseReference UsersDB = database.getReference("Users");
        DatabaseReference UsersDB = database.getReference("Users").child("wihbe");

        UsersDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean flag = true;
                //String ss =  snapshot.child("Password").getValue().toString();

//                for (DataSnapshot snap : snapshot.getChildren()) {
//                    if (snap.child("liran").getValue().toString().equals(userName)) {
//                        Toast.makeText(getApplicationContext(), "Invalid Username the user is exist ", Toast.LENGTH_SHORT).show();
//                        flag = false;
//                    }
//
//                    if (snap.child("Email").getValue().toString().equals(emailInput)) {
//                        Toast.makeText(getApplicationContext(), "Invalid Email the Email is exist ", Toast.LENGTH_SHORT).show();
//                        flag = false;
//                    }
//                }

                fireBaseCallback.onCallback(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private interface FireBaseCallback {
        void onCallback(DataSnapshot flag);
    }
}