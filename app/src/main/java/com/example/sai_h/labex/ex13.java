package com.example.sai_h.labex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ex13 extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ex13,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String val = "Name:"+getArguments().getString("Name");
        val = val+"\n\n"+"Gender:"+getArguments().getString("Gender");
        val = val +"\n\n"+"Hobbies:";
        if(getArguments().getString("H1")!=null){
            val = val+"\n"+getArguments().getString("H1");
        }
        if(getArguments().getString("H2")!=null){
            val = val+"\n"+getArguments().getString("H2");
        }
        if(getArguments().getString("H3")!=null){
            val = val+"\n"+getArguments().getString("H3");
        }
        TextView t = (TextView) v.findViewById(R.id.finalex1);
        t.setText(val);
    }
}
