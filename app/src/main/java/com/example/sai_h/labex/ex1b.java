package com.example.sai_h.labex;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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
 * {@link ex1b.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ex1b#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ex1b extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ex1b,container,false);
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
