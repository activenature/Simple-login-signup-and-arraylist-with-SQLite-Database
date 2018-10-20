package com.example.shourov.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class ListViewDatabasehelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ListViewUserDetails_db";
    private static final String TABLE_NAME = "listview_userdetails";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String PHONE = "Phone";
    private static final int VERSION_NUMBER = 2;
    //create database
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(50) NOT NULL, "+PHONE+" INTEGER NOT NULL);";
    //DROP_TABLE
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
    private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;

    private Context context;
    public ListViewDatabasehelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();

        }

    }
    //listview user data
    public long listuserdata(String id, String name, String phone){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(PHONE,phone);
        long rowid = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        return rowid;

    }
    //show user data
    public Cursor listshowdata(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT,null);

        return cursor;
    }
    //update data
    public Boolean updateData(String id, String name, String phone){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(PHONE,phone);
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ? ",new  String[] {id});
        return true;
    }
    //delete data
    public int deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       int value = sqLiteDatabase.delete(TABLE_NAME,ID+" = ? ",new  String[] {id});

        return value;
    }


}
