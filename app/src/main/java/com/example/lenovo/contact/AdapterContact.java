package com.example.lenovo.contact;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterContact extends ArrayAdapter<Contact> {

    Context ctx;
    int item;

    public AdapterContact(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.ctx= context;
        this.item=resource;

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(item,parent,false);

        TextView name = convertView.findViewById(R.id.username);
        TextView phone = convertView.findViewById(R.id.userphone);
        TextView lettre = convertView.findViewById(R.id.lettre);

        Contact contact = getItem(position);
        name.setText(contact.getName());
        lettre.setText(name.getText().subSequence(0,1).toString().toUpperCase());
        phone.setText(String.valueOf(contact.getPhone()));


        return convertView;
    }
}
