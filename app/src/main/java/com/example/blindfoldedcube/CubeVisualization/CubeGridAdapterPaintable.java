package com.example.blindfoldedcube.CubeVisualization;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.blindfoldedcube.R;
import com.example.blindfoldedcube.Utilities;


//Variation on CubeGrid2DAdapter, enabling tiles to be repainted
public class CubeGridAdapterPaintable extends BaseAdapter {
    public final String solvedCube = "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB";
    String faceCube;

    public CubeGridAdapterPaintable(String faceCube)
    {
        this.faceCube = Utilities.cubeStringToGridDisplay(faceCube);
    }

    public CubeGridAdapterPaintable()
    {
        this.faceCube = Utilities.cubeStringToGridDisplay(solvedCube);
    }

    @Override
    public int getCount() {
        if (faceCube != null)
        {
            return faceCube.length();
        }
        else
            return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater _inflater = LayoutInflater.from(viewGroup.getContext());
            view = _inflater.inflate(R.layout.grid_sticker, viewGroup, false);
        }

        Button aButton = view.findViewById(R.id.stickerButton);
        LinearLayout aLayout = view.findViewById(R.id.stickerLL);

        switch (faceCube.charAt(i))
        {
            case 'U':
                aButton.setBackgroundColor(Color.WHITE);
                aButton.setText("U");
                break;
            case 'D':
                aButton.setBackgroundColor(Color.YELLOW);
                aButton.setText("D");
                break;
            case 'R':
                aButton.setBackgroundColor(Color.RED);
                aButton.setText("R");
                break;
            case 'L':
                aButton.setBackgroundColor(Color.rgb(255, 165, 0)); //ORANGE
                aButton.setText("L");
                break;
            case 'F':
                aButton.setBackgroundColor(Color.GREEN);
                aButton.setText("F");
                break;
            case 'B':
                aButton.setBackgroundColor(Color.BLUE);
                aButton.setText("B");
                break;
            case '*':
                aButton.setBackgroundColor(Color.TRANSPARENT);
                aLayout.setBackgroundColor(Color.TRANSPARENT);
                aButton.setText(" ");
                break;
            default:
                break;
        }

        return view;
    }


}
