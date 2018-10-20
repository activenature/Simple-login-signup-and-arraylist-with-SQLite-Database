package com.example.shourov.androidapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private EditText idEditText, nameEditText, phoneEditText;
    private Button saveButton, showButton, updateButton, deleteButton;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    ListViewDatabasehelper listViewDatabasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        idEditText = findViewById(R.id.idId);
        nameEditText = findViewById(R.id.nameId);
        phoneEditText = findViewById(R.id.phoneeditextId);
        saveButton = findViewById(R.id.saveButtonid);
        showButton = findViewById(R.id.showButtonid);
        updateButton = findViewById(R.id.updateButtonId);
        deleteButton = findViewById(R.id.deleteButtonid);

        listViewDatabasehelper = new ListViewDatabasehelper(this);
        SQLiteDatabase sqLiteDatabase = listViewDatabasehelper.getWritableDatabase();

        drawerLayout = findViewById(R.id.drawerId);
        NavigationView navigationView = findViewById(R.id.navigationid);
        navigationView.setNavigationItemSelectedListener(this);
        //toggle creating
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        //toggle listener
        drawerLayout.addDrawerListener(toggle);//toggle passing
        toggle.syncState();
        //display toggle
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    //toggle working process
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);
    }
    //menu item selected
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.homemenuid){

            Intent intent = getIntent();
            finish();
            startActivity(intent);

        }
        if(menuItem.getItemId()==R.id.profilemenuid){

            Intent intent = new Intent(SecondActivity.this,newactivity.class);
            startActivity(intent);

        }
        if(menuItem.getItemId()==R.id.chatmenuid){

            Toast.makeText(getApplicationContext(), "Chat", Toast.LENGTH_SHORT).show();
        }
        if(menuItem.getItemId()==R.id.sharemenuid){

            Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();
        }
        if(menuItem.getItemId()==R.id.contactmenuid){
            Toast.makeText(getApplicationContext(), "Contact", Toast.LENGTH_SHORT).show();

        }

        return false;
    }

    //listview process start
    public void listviewButton(View view){
        String id = idEditText.getText().toString();
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if(view.getId() == R.id.saveButtonid){

            if(id.equals("") && name.equals("") && phone.equals("")){
                Toast.makeText(getApplicationContext(), "Please enter input", Toast.LENGTH_SHORT).show();
            }
            else{

                long rowid = listViewDatabasehelper.listuserdata(id,name,phone);
                if(rowid > -1){
                    Toast.makeText(getApplicationContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    idEditText.setText("");
                    nameEditText.setText("");
                    phoneEditText.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                }

            }


        }else if(view.getId() == R.id.showButtonid){

            Intent intent = new Intent(SecondActivity.this,Userdata.class);
            startActivity(intent);


        }else if(view.getId() == R.id.updateButtonId){

            Boolean result = listViewDatabasehelper.updateData(id,name,phone);

            if(result == true){

                Toast.makeText(getApplicationContext(), "Data is updated", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getApplicationContext(), "Data is not updated", Toast.LENGTH_SHORT).show();
            }


        }else if(view.getId() == R.id.deleteButtonid){

            int value = listViewDatabasehelper.deleteData(id);
            if(value < 0){
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
            }

        }



    }

}
