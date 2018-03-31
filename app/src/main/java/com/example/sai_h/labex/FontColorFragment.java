package com.example.sai_h.labex;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FontColorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FontColorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FontColorFragment extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_font_color,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView t = (TextView)v.findViewById(R.id.txt);
        v.findViewById(R.id.red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setBackgroundColor(Color.RED);
            }
        });
        v.findViewById(R.id.green).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setBackgroundColor(Color.GREEN);
            }
        });
        v.findViewById(R.id.yellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setBackgroundColor(Color.YELLOW);
            }
        });
    }
}
