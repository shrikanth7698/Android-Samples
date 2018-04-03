package com.example.sai_h.labex;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class BasicUIFragment3 extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup to be inflated into the activity
        v = inflater.inflate(R.layout.fragment_ex13,container,false);
        return v;//Returns the Viewgroup to the activity class for inflation
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Arguments stored in the bundle are obtained using getArguments.getString(<String key>)
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
        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v
        TextView t = (TextView) v.findViewById(R.id.finalex1);
        t.setText(val);
    }
}
