package com.example.lenovo.contact;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    FloatingActionButton btn;
    DbContact db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list  = findViewById(R.id.listC);
        btn  = findViewById(R.id.fab);

        db=new DbContact(this);
        ArrayList<Contact> contact =db.getAllContact();
        AdapterContact adapterContact = new AdapterContact(this,R.layout.model,contact);
        list.setAdapter(adapterContact);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddContact.class);
                startActivity(i);
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                Intent intent = new Intent(MainActivity.this,UpdateContact.class);
                Contact c = (Contact) parent.getItemAtPosition(position);
                int id = c.getId();
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });




    }


}
