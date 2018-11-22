package com.maddoggo.mydoggoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maddoggo.mydoggoapp.Model.Adoption;
import com.squareup.picasso.Picasso;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<Adoption> {

    Context context;

    public arrayAdapter(Context context, int resourceId, List<Adoption> items) {
        super(context, resourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Adoption card_item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView dog_name = convertView.findViewById(R.id.dog_name);
        ImageView image_adoption = convertView.findViewById(R.id.image_adoption);
        //String url = "https://firebasestorage.googleapis.com/v0/b/mydoggo-project.appspot.com/o/dog1.jpg?alt=media&token=b18c2264-ba92-415e-a80a-c1f7b82c9d1d";

        dog_name.setText(card_item.getDogName());

        Picasso.with(getContext())
                .load(card_item.getDogPict())
                .into(image_adoption);

        //image_adoption.);
        //image_adoption.set = card_item.getUserId();

        return convertView;

    }

}
