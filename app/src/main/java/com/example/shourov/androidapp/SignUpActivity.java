package com.example.shourov.androidapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, emailEditText, usernameEditText, phoneEditText, passwordEditText;
    private Button signupButton;

    userDetails UserDetails;
    LoginSignUpDatabaseHelper loginSignUpDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginSignUpDatabaseHelper = new LoginSignUpDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = loginSignUpDatabaseHelper.getWritableDatabase();

        nameEditText = findViewById(R.id.signupNameid);
        emailEditText = findViewById(R.id.signupEmailId);
        usernameEditText = findViewById(R.id.signupUsernameid);
        phoneEditText = findViewById(R.id.signupPhoneid);
        passwordEditText = findViewById(R.id.signupPasswordid);

        signupButton = findViewById(R.id.signupButtonId);

        UserDetails = new userDetails();//userdetails class er object

        signupButton.setOnClickListener(this);

    }
    //onclick listener
    @Override
    public void onClick(View v) {

        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //data set
        UserDetails.setName(name);
        UserDetails.setEmail(email);
        UserDetails.setUsername(username);
        UserDetails.setPhone(phone);
        UserDetails.setPassword(password);

        //data insert
        long rowId = loginSignUpDatabaseHelper.userData(UserDetails);
        //data inserted or not
        if(rowId  > 0 ){
            Toast.makeText(getApplicationContext(), "SignUp Successfull", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(getApplicationContext(), "Row insertion failed", Toast.LENGTH_SHORT).show();
        }


    }
}
