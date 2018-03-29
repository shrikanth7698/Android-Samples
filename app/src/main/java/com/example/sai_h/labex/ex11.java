package com.example.sai_h.labex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ex11 extends Fragment {
    View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_ex11, container, false);
        System.out.println("OnCreate");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("OnCreated");
        v.findViewById(R.id.bex1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText t = (EditText) v.findViewById(R.id.val1);
                t = (EditText) v.findViewById(R.id.val2);
                /*Bundle b = new Bundle();
                b.putString("uname",t.getText().toString());
                b.putString("pass",t.getText().toString());*/
                ex12 e = new ex12();
                //e.setArguments(b);
                FragmentTransaction f = getFragmentManager().beginTransaction();
                f.replace(R.id.fragment,new ex12());
                f.commit();
            }
        });

    }
}
