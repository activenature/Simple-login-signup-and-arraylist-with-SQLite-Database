package com.example.shourov.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class LoginSignUpDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDetails.db";
    private static final String TABLE_NAME = "user_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USERNAME = "Username";
    private static final String PHONE = "Phone";
    private static final String PASSWORD = "Password";
    private static final int VERSION_NUMBER = 6;
    //create database
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) NOT NULL,"+EMAIL+" TEXT NOT NULL, "+USERNAME+" TEXT NOT NULL,"+PHONE+" INTEGER NOT NULL, "+PASSWORD+" TEXT NOT NULL);";
    //drop table
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    //select
    private static final String SELECT = "SELECT * FROM " + TABLE_NAME;

    Context context;
    public LoginSignUpDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            Toast.makeText(context, "Oncreate is called", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context, "onUpdate is called", Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    //save user details
    public long userData(userDetails detailsofuser){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,detailsofuser.getName());
        contentValues.put(EMAIL,detailsofuser.getEmail());
        contentValues.put(USERNAME,detailsofuser.getUsername());
        contentValues.put(PHONE,detailsofuser.getPhone());
        contentValues.put(PASSWORD,detailsofuser.getPassword());
        long rowid = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return rowid;
    }

    public Boolean findDetails(String loginUserName, String loginPassword){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT,null);//data searching
        boolean result = false;//initiali false

        if(cursor.getCount() == 0){

            Toast.makeText(context,"NO DATA FOUND",Toast.LENGTH_SHORT).show();

        }else{

            while(cursor.moveToNext()){

                String userName = cursor.getString(3);
                String password = cursor.getString(5);

                if(userName.equals(loginUserName) && password.equals(loginPassword)){

                    result = true;
                    break;

                }

            }

        }

        return result;
    }

}
