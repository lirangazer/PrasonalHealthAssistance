package com.example.prasonalhealthassistance;

import android.os.Bundle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupPage extends AppCompatActivity {
    final boolean[] flag = new boolean[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        findViewById(R.id.signupSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editUserName = (EditText) findViewById(R.id.signupUserName);
                String userName = editUserName.getText().toString().toLowerCase();
                EditText editEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
                String email = editEmail.getText().toString().toLowerCase();
                if (!isEmailValid(email)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                EditText editPassword = (EditText) findViewById(R.id.textPasswordSignup);
                String password = editPassword.getText().toString();
                EditText editPassword1 = (EditText) findViewById(R.id.textPassword2Signup);
                String password1 = editPassword1.getText().toString();
                if (!(password.equals(password1)) || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Invalid Password or password don't match", Toast.LENGTH_SHORT).show();
                    return;
                }
                detailCheck(userName,email);
                if(flag[0]){
                    Toast.makeText(getApplicationContext(), "its works", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void detailCheck(String name, String email) {

        //flag[0]=true;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userToAddRef2 = database.getReference("users");
        //userlist = new ArrayList<>();
        userToAddRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren()) {
                    if(snap.child("name").getValue().toString().equals(name)){
                        Toast.makeText(getApplicationContext(), "Invalid Username the user is exist " ,Toast.LENGTH_SHORT).show();
                        flag[0] =true;

                    }
                    if(snap.child("email").getValue() .toString().equals(email)){
                        Toast.makeText(getApplicationContext(), "Invalid Email the Email is exist " ,Toast.LENGTH_SHORT).show();
                        flag[0] =true;
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        //return flag[0];

    }
//    private boolean handleLogic(boolean b) {
//        return b;
//    }
    
//    private void readData(FireBaseCallback a){
//        
//    }
//
//    private interface FireBaseCallback {
//        void onCallback(boolean b);
//    }



}