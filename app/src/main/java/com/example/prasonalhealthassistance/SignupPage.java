package com.example.prasonalhealthassistance;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        findViewById(R.id.signupSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editUserName = (EditText) findViewById(R.id.signupUserName);
                String userName = editUserName.getText().toString();
                EditText editEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
                String email = editEmail.getText().toString();
                if(!isEmailValid(email)){
                    Toast.makeText(getApplicationContext(),"Invalid Email" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                EditText editPassword = (EditText) findViewById(R.id.textPasswordSignup);
                String password = editPassword.getText().toString();
                EditText editPassword1 = (EditText) findViewById(R.id.textPassword2Signup);
                String password1 = editPassword1.getText().toString();
                if(!(password.equals(password1)) || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Invalid Password or password don't match" ,Toast.LENGTH_SHORT).show();
                    return;
                }

            }});
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}