package com.example.prasonalhealthassistance;

import android.content.Intent;
import android.content.SharedPreferences;
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
    UserModel user = new UserModel();
    boolean[] isTrue = new boolean[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        findViewById(R.id.signupSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input username from the user
                EditText editUserName = (EditText) findViewById(R.id.signupUserName);
                user.User = editUserName.getText().toString().toLowerCase();
                //input email from the user
                EditText editEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
                user.Email = editEmail.getText().toString().toLowerCase();

                //Email validation
                editEmail.getText().toString().toLowerCase();
                if (!isEmailValid(user.Email)) {
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                //input password from the user
                EditText editPassword = (EditText) findViewById(R.id.textPasswordSignup);
                user.Password = editPassword.getText().toString();
                EditText editPassword1 = (EditText) findViewById(R.id.textPassword2Signup);
                String password1 = editPassword1.getText().toString();

                if (!(user.Password.equals(password1)) || user.Password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Invalid Password or Password don't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Check if username or mail are not exsit in the DB
                readData(new FireBaseCallback() {
                    @Override
                    public void onCallback(boolean flag) {
                        isTrue[0] = flag;
                    }
                },  user.User, user.Email);

                //Add new child node to Users in DB as new registered user.
                if(!isTrue[0])
                    return;

                DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");;
                database.child(user.User).setValue(user);
                database.child(user.User).child("product").setValue(new RecyclerUtils("1", "1", 1));

                Intent intent = new Intent (SignupPage.this,LoginPage.class);
                startActivity(intent);
            }
        });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void readData(FireBaseCallback fireBaseCallback, String userInput, String emailInput){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean flag = true;
                try {
                    UserModel user  = (UserModel)snapshot.child(userInput).getValue(UserModel.class);
                    if(user != null)
                    {
                        Toast.makeText(getApplicationContext(), "User is exist", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }

                    if(flag)
                    {
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            if (snap.child("Email").getValue().toString().equals(emailInput)) {
                                Toast.makeText(getApplicationContext(), "Email already exist", Toast.LENGTH_SHORT).show();
                                flag = false;
                            }
                        }
                    }
                }

                catch (Exception error)
                {
                    Toast.makeText(getApplicationContext(), "Invalid Email the Email is exist", Toast.LENGTH_SHORT).show();
                    flag = false;
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

//Check if username or mail are not exsit in the DB
//                readData(new FireBaseCallback() {
//                    @Override
//                    public void onCallback(boolean flag) {
//                        if(!flag) {
//                            return;
//                        }
//
//                        //Save new user to the DB
//                        //userToAddRef.child(user.User).push().setValue(user);
////                        userToAddRef.setValue(user);
////                        userToAddRef.child(user.User).setValue(user);
////                        userToAddRef.child(user.User.toString()).setValue(user);
//
//
//                    }
//                },  user.User, user.Email);