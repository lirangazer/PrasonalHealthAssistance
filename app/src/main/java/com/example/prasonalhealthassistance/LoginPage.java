package com.example.prasonalhealthassistance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    final String[] s = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ImageView aniView = (ImageView)findViewById(R.id.imageView2);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(aniView, "alpha", 0f, 1f);
        fadeIn.setDuration(2000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(fadeIn);
        animatorSet.start();
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editUserName = (EditText) findViewById(R.id.userNameBox);
                String userName = editUserName.getText().toString();
                EditText editPassword = (EditText) findViewById(R.id.passwordBox);
                String password = editPassword.getText().toString();
                //init firebase db
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userToAddRef2 = database.getReference("users");
                userToAddRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            if((snap.child("name").getValue().toString().equals(userName)) && (snap.child("password").getValue() .toString().equals(password)) ){
                                Intent intent = new Intent (LoginPage.this,MainPage.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Value is: good" ,Toast.LENGTH_SHORT).show();
                            }
                            //String value = snap.child("user" + i).child("name").getValue().toString();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                /*for(int i=1;i<3;i++){
                        userToAddRef2.child("user"+i+"").child("name").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue(String.class);
                            if(value.equals(userName)){
                                Toast.makeText(getApplicationContext(), "Value is: " + value,Toast.LENGTH_SHORT).show();
                            }
                            //Toast.makeText(getApplicationContext(), "Value is: " + value,Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {
                        // Failed to read value
                            Toast.makeText(getApplicationContext(), "Failed to read value." + error.toException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                        //Toast.makeText(LoginPage.this,"Hello "+value[0]+"!",Toast.LENGTH_LONG).show();
            }
         });*/


            }
/*    private String reachFromDB(String value)
    {

        //init firebase db
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("name");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                s[0] = dataSnapshot.getValue(String.class);
                //Toast.makeText(getApplicationContext(), "Value is: " + s[0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
     return s[0];*/
        });
    }
}