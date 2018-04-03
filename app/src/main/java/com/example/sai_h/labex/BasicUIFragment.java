package com.example.sai_h.labex;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
public class BasicUIFragment extends Fragment {
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ViewGroup to be inflated into the activity
        v = inflater.inflate(R.layout.fragment_basic_ui, container, false);
        return v; //Returns the Viewgroup to the activity class for inflation
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v
        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);
        v.findViewById(R.id.bex1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText t = (EditText) v.findViewById(R.id.val1);
                t = (EditText) v.findViewById(R.id.val2);
                //Initiating a fragment transaction.
                FragmentTransaction f = getFragmentManager().beginTransaction();
                // Current fragment is replaced by another fragment
                f.replace(R.id.frameLayout,new BasicUIFragment2());
                f.commit();
            }
        });
    }
}
