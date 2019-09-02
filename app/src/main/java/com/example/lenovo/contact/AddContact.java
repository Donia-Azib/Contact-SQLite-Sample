package com.example.lenovo.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddContact extends AppCompatActivity {

    EditText lastname,phone;
    Button submit;
    DbContact dbContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


        lastname= findViewById(R.id.lastname);
        phone= findViewById(R.id.num);
        submit= findViewById(R.id.submit);

        dbContact = new DbContact(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = lastname.getText().toString();
                int phonee = Integer.parseInt(phone.getText().toString());

                if(nom.length() != 0 && phone.getText().length()!=0) {
                    Contact contact = new Contact(nom, phonee);
                    dbContact.AddContact(contact);

                    Toast.makeText(AddContact.this, "saved ! ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddContact.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(AddContact.this, "Complete the form", Toast.LENGTH_SHORT).show();
                }
            }});

}



}
