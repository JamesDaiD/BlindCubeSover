package com.example.blindfoldedcube;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.blindfoldedcube.CubeDataStructure.Scramble;

import java.util.List;

public class ScrambleListAdapter extends BaseAdapter
{
    List<Scramble> scrambles;

    public ScrambleListAdapter(List<Scramble> scrambleList)
    {
        this.scrambles = scrambleList;
    }

    @Override
    public int getCount()
    {
        return scrambles.size();
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public Object getItem(int i)
    {
        return scrambles.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater anInflater = LayoutInflater.from(viewGroup.getContext());
            view = anInflater.inflate(R.layout.scramble_list_model, viewGroup, false);
        }

        /* bind text View */
        TextView txtViewLevel = view.findViewById(R.id.scrambleText);
        txtViewLevel.setText(scrambles.get(i).getScramble());
        txtViewLevel.setGravity(Gravity.CENTER);

        return view;
    }
}
