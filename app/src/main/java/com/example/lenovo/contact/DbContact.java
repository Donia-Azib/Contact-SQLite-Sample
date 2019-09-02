package com.example.lenovo.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DbContact extends SQLiteOpenHelper{

    private static final String DB_NAME = "dbcontact";
    private static final int VERSION = 2;


    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    private static final String DB_TABLE = "contact";

    public DbContact(Context context) {
        super(context,DB_NAME,null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = " CREATE TABLE "+DB_TABLE+" ( "+KEY_ID+" integer PRIMARY KEY  , "+KEY_NAME+" text , "+KEY_PHONE+" integer ); ";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       String delete_query =" DROP TABLE IF EXISTS "+DB_TABLE+";";
        db.execSQL(delete_query);
        onCreate(db);


    }

    public  void AddContact(Contact contact){
        SQLiteDatabase db =getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE,contact.getPhone());

        db.insert(DB_TABLE,null,values);

    }

    public ArrayList<Contact> getAllContact(){

        ArrayList<Contact> contacts = new ArrayList<>();

        String query=" SELECT * FROM "+DB_TABLE+";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));
                Contact contact = new Contact(id,name,phone);

                contacts.add(contact);

                }while (cursor.moveToNext());
        }

        return contacts;
    }



    public Contact getContactById(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String query="select * from "+DB_TABLE+" where id = "+id;
        Cursor cursor = db.rawQuery(query,null);
        Contact contact =null ;
        if (cursor.moveToFirst())
        {
            int id_c=cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name_c=cursor.getString(cursor.getColumnIndex(KEY_NAME));
            int phone_c=cursor.getInt(cursor.getColumnIndex(KEY_PHONE));
            contact= new Contact(id_c,name_c,phone_c);
        }

        return contact;
    }

    public void UpdateContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE,contact.getPhone());

        db.update(DB_TABLE,values,"id=?",new String[]{String.valueOf(contact.getId())});

    }

    public void DeleteContact(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DB_TABLE,"id=?",new String[]{String.valueOf(id)});

    }
}
