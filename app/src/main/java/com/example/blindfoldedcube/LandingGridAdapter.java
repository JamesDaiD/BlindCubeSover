package com.example.blindfoldedcube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LandingGridAdapter extends BaseAdapter {
    List<String> pageName;
    List<Integer> pageImg;

    public LandingGridAdapter(List<String> pageName, List<Integer> pageImg) {
        this.pageName = pageName;
        this.pageImg = pageImg;
    }

    @Override
    public int getCount() {
        return pageName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        //        LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (v == null) {
            LayoutInflater _inflater = LayoutInflater.from(parent.getContext());
            v = _inflater.inflate(R.layout.landing_grid_model, parent, false);
        }
        // Get the TextView and ImageView from CustomView for displaying item
        ImageView imgview = v.findViewById(R.id.pageListGridImg);
        TextView txtview = v.findViewById(R.id.pageListGridText);

        // Set the text and image for current item using data from map list
        txtview.setText(pageName.get(position));
        imgview.setImageResource(pageImg.get(position));
        return v;
    }
}