package com.example.blindfoldedcube.CubeVisualization;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            view = _inflater.inflate(R.layout.grid_sticker_paintable, viewGroup, false);
        }

        TextView aTextView = view.findViewById(R.id.textView);
        LinearLayout aLayout = view.findViewById(R.id.stickerLL);

        switch (faceCube.charAt(i))
        {
            case 'U':
                aTextView.setBackgroundColor(Color.WHITE);
                aTextView.setText("U");
                break;
            case 'D':
                aTextView.setBackgroundColor(Color.YELLOW);
                aTextView.setText("D");
                break;
            case 'R':
                aTextView.setBackgroundColor(Color.RED);
                aTextView.setText("R");
                break;
            case 'L':
                aTextView.setBackgroundColor(Color.rgb(255, 165, 0)); //ORANGE
                aTextView.setText("L");
                break;
            case 'F':
                aTextView.setBackgroundColor(Color.GREEN);
                aTextView.setText("F");
                break;
            case 'B':
                aTextView.setBackgroundColor(Color.BLUE);
                aTextView.setText("B");
                break;
            case '*':

                aTextView.setBackgroundColor(Color.TRANSPARENT);
                aLayout.setBackgroundColor(Color.TRANSPARENT);
                aTextView.setText(" ");
                break;
            default:
                break;
        }

        return view;
    }


}
