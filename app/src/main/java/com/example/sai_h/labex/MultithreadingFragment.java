package com.example.sai_h.labex;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class MultithreadingFragment extends Fragment{
    View v;
    Handler h;
    TextView c;
    Button b;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup to be inflated into the activity
        v = inflater.inflate(R.layout.fragment_multithreading,container,false);
        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v
        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);
        c = (TextView)v.findViewById(R.id.multxt2);
        b = (Button)v.findViewById(R.id.clickme);
        b.setVisibility(View.INVISIBLE);
        //Initializing handler to run a thread after one second
        h = new Handler();
        h.postDelayed(run,1000);
        return v;  //Returns the Viewgroup to the activity class for inflation
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.co.in/"));
                startActivity(browserIntent);

            }
        });
    }
    //Thread to perform the operation
    Runnable run = new Runnable() {
        @Override
        public void run() {
            updateTime();
        }
    };
    public void updateTime(){
        c.setText(""+(Integer.parseInt(c.getText().toString())-1));
        if(Integer.parseInt(c.getText().toString())==0){
            b.setVisibility(View.VISIBLE);
        }
        else{
            h.postDelayed(run,1000);
        }
    }
}
