package com.example.sai_h.labex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BasicUIFragment2 extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup to be inflated into the activity
        v = inflater.inflate(R.layout.fragment_ex12,container,false);
        return v;//Returns the Viewgroup to the activity class for inflation
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v
        v.findViewById(R.id.bex12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Bundle to pass the values from current fragment to next fragment. In activity, values are passed using Intent.
                Bundle b = new Bundle();
                TextView t1 = (TextView) v.findViewById(R.id.val1);
                b.putString("Name",t1.getText().toString());
                RadioGroup rg = (RadioGroup) v.findViewById(R.id.radio);
                switch(rg.getCheckedRadioButtonId()){
                    case R.id.m: b.putString("Gender","Male");
                                 break;
                    case R.id.fm: b.putString("Gender","Female");
                                  break;
                }
                CheckBox c1 = (CheckBox)v.findViewById(R.id.ch1);
                CheckBox c2 = (CheckBox)v.findViewById(R.id.ch2);
                CheckBox c3 = (CheckBox)v.findViewById(R.id.ch3);
                if(c1.isChecked())
                    b.putString("H1",c1.getText().toString());
                if(c2.isChecked())
                    b.putString("H2",c2.getText().toString());
                if(c3.isChecked())
                    b.putString("H3",c3.getText().toString());
                BasicUIFragment3 ui3 = new BasicUIFragment3();
                ui3.setArguments(b); //Bundle containing the arguments is set
                getFragmentManager().beginTransaction().replace(R.id.frameLayout,ui3).commit();
            }
        });
    }
}
