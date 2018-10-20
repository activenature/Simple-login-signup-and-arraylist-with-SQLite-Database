package com.example.shourov.androidapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Userdata extends AppCompatActivity {

    private ListView listView;
    private ListViewDatabasehelper listViewDatabasehelper;
    ArrayAdapter<String> adapter;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        //title set
        this.setTitle("User Details");

        //backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView = findViewById(R.id.listviewid);
        listViewDatabasehelper  = new ListViewDatabasehelper(this);

        refreshLayout = findViewById(R.id.swipeRefreshLayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });

        loadData();

    }
    //backbutton method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){

            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
    //search icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchid);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //data load method
    public void loadData(){

        ArrayList<String> arrayList = new ArrayList<>();//data load kore arraylist e asbe

        Cursor cursor = listViewDatabasehelper.listshowdata();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data available", Toast.LENGTH_SHORT).show();

        }else{

            while(cursor.moveToNext()){

                arrayList.add(cursor.getString(0) + "\t" + cursor.getString(1) +"\t"+ cursor.getString(2) );
            }
        }

         adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.textviewid,arrayList);
        listView.setAdapter(adapter);

        //when someone click list data
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectvalue = adapter.getItem(position).toString();
                Toast.makeText(getApplicationContext(), "Selected value : "+selectvalue, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //refresh content
    public void refreshContent(){

        refreshLayout.setRefreshing(false);
        adapter.add("Shourov");
        adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Data Added", Toast.LENGTH_SHORT).show();

    }


}
