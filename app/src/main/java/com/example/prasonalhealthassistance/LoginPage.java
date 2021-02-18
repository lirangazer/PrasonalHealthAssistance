package com.example.prasonalhealthassistance;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "user";

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
        SharedPref.init(this);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editUserName = (EditText) findViewById(R.id.userNameBox);
                String userName = editUserName.getText().toString().toLowerCase();
                EditText editPassword = (EditText) findViewById(R.id.passwordBox);
                String password = editPassword.getText().toString();

                //For Debug only --- Delete when its done with app --- Wihbe
                Intent intent = new Intent (LoginPage.this,MainPage.class);
                startActivity(intent);
                //For Debug only --- Delete when its done with app --- Wihbe

                //init firebase db
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userToAddRef2 = database.getReference("Users");
                userToAddRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean flag=false;
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            if((snap.child("User").getValue().toString().equals(userName)) && (snap.child("Password").getValue() .toString().equals(password)) ){

                                //Saving username to sharedperf to use it further in the program.
                                //saveData(userName);

                                SharedPref.write("user", userName);
                                String data = SharedPref.read("user","");

                                Intent intent = new Intent (LoginPage.this,MainPage.class);
                                startActivity(intent);
                                flag=true;
                                break;
                            }
                        }

                        if(!flag){
                            Toast.makeText(getApplicationContext(), "Invalid Username or password" ,Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
           }
        });

        findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginPage.this,SignupPage.class);
                startActivity(intent);
            }
        });
    }

//    private void saveData(String userName)
//    {
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(TEXT, userName);
//        editor.apply();
////        String data = sharedPreferences.getString(TEXT, "");
////        Toast.makeText(getApplicationContext(), "Invalid Username or password" ,Toast.LENGTH_SHORT).show();
//    }
}