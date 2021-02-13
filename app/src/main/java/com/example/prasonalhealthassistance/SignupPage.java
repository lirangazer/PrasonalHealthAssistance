package com.example.prasonalhealthassistance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupPage extends AppCompatActivity {
    UserData user = new UserData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        findViewById(R.id.signupSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editUserName = (EditText) findViewById(R.id.signupUserName);
                //String userName = editUserName.getText().toString().toLowerCase();
                user.User = editUserName.getText().toString().toLowerCase();

                EditText editEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
                //String email = editEmail.getText().toString().toLowerCase();
                user.Email = editEmail.getText().toString().toLowerCase();

                //Email validation
                editEmail.getText().toString().toLowerCase();
                if (!isEmailValid(user.Email)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Password validation
                EditText editPassword = (EditText) findViewById(R.id.textPasswordSignup);
                //String password = editPassword.getText().toString();
                user.Password = editPassword.getText().toString();
                EditText editPassword1 = (EditText) findViewById(R.id.textPassword2Signup);
                String password1 = editPassword1.getText().toString();
                if (!(user.Password.equals(password1)) || user.Password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Invalid Password or password don't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check if username or mail are not exsit in the DB
                readData(new FireBaseCallback() {
                    @Override
                    public void onCallback(boolean flag) {
                        if(!flag) {
                            return;
                        }

                        //Save new user to the DB
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference userToAddRef = database.getReference("Users");
                        userToAddRef.child("Users").child(user.User.toString());
                        userToAddRef.child(user.User.toString()).setValue(user);

                        Intent intent = new Intent (SignupPage.this,LoginPage.class);
                        startActivity(intent);
                    }
                },  user.User, user.Email);

            }
        });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

//    public void detailCheck(String name, String email) {
//
//        //flag[0]=true;
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference userToAddRef2 = database.getReference("users");
//        //userlist = new ArrayList<>();
//        userToAddRef2.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot snap : snapshot.getChildren()) {
//                    if(snap.child("name").getValue().toString().equals(name)){
//                        Toast.makeText(getApplicationContext(), "Invalid Username the user is exist " ,Toast.LENGTH_SHORT).show();
//                        flag[0] =true;
//
//                    }
//                    if(snap.child("email").getValue() .toString().equals(email)){
//                        Toast.makeText(getApplicationContext(), "Invalid Email the Email is exist " ,Toast.LENGTH_SHORT).show();
//                        flag[0] =true;
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    
    private void readData(FireBaseCallback fireBaseCallback, String userInput, String emailInput){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userToAddRef2 = database.getReference("Users");

        userToAddRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean flag = true;
                for (DataSnapshot snap : snapshot.getChildren()) {
                    if (snap.child("User").getValue().toString().equals(userInput)) {
                        Toast.makeText(getApplicationContext(), "Invalid Username the user is exist ", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }

                    if (snap.child("Email").getValue().toString().equals(emailInput)) {
                        Toast.makeText(getApplicationContext(), "Invalid Email the Email is exist ", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }
                }

                fireBaseCallback.onCallback(flag);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private interface FireBaseCallback {
        void onCallback(boolean flag);
    }

}