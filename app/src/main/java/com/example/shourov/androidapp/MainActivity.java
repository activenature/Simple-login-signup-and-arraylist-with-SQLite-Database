package com.example.shourov.androidapp;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEditText,PasswordEdittext;
    private Button loginButton,createnewAccountButton;

    userDetails UserDetails;
    LoginSignUpDatabaseHelper loginSignUpDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginSignUpDatabaseHelper = new LoginSignUpDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = loginSignUpDatabaseHelper.getWritableDatabase();

        usernameEditText = findViewById(R.id.loginUsernameid);
        PasswordEdittext = findViewById(R.id.loginpasswordid);
        loginButton = findViewById(R.id.loginButtonid);
        createnewAccountButton = findViewById(R.id.newAccountButtonid);

        UserDetails = new userDetails();//userdetails class er object

        loginButton.setOnClickListener(this);
        createnewAccountButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String loginUsername = usernameEditText.getText().toString();
        String loginPassword = PasswordEdittext.getText().toString();

        if(v.getId() == R.id.loginButtonid){

            Boolean result = loginSignUpDatabaseHelper.findDetails(loginUsername,loginPassword);

            if(result == true){
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                finish();

            }else{
                Toast.makeText(getApplicationContext(), "invalid input", Toast.LENGTH_SHORT).show();
            }


        }
        else if(v.getId() == R.id.newAccountButtonid){

            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);

        }

    }
}
