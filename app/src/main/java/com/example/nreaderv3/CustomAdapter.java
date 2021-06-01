package com.example.nreaderv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<StructureOfElement> {
    public CustomAdapter(Context context, int resource, List<StructureOfElement> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.structure_element, null);
        }
        StructureOfElement p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txtTitle = (TextView) view.findViewById(R.id.titleElement);
            txtTitle.setText(p.title);
            ImageView imageView = view.findViewById(R.id.imageElement);
            Picasso.with(getContext()).load(p.image).into(imageView);
        }
        return view;
    }
}
