package com.example.eva3_examen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Rolas_adapter extends ArrayAdapter<Rolas> {

    Context context;
    int resource;
    Rolas[] Rolas;

    public Rolas_adapter(Context context, int resource, Rolas[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.Rolas = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView txtNombre, txtAutor;

        if(convertView == null){
            //Crear nuestro layout que representa una fila en la lista
            //INFLATER
            LayoutInflater lInflator = ((Activity) context).getLayoutInflater();
            convertView = lInflator.inflate(resource, parent, false);
        }

        imageView = convertView.findViewById(R.id.imageView);
        txtNombre = convertView.findViewById(R.id.tvNombre);
        txtAutor = convertView.findViewById(R.id.tvAutor);


        imageView.setImageResource(Rolas[position].getImagen());
        txtNombre.setText(Rolas[position].getNombre());
        txtAutor.setText(Rolas[position].getAutor());
        return convertView;
    }
}