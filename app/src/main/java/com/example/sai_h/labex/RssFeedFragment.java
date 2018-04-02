package com.example.sai_h.labex;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class RssFeedFragment extends Fragment {
    View v;
    EditText title,link,description;
    Button b1,b2;
    private String finalUrl="http://tutorialspoint.com/android/sampleXML.xml";
    private HandleXML obj;

    public RssFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_rss_feed, container, false);
        title = (EditText)v.findViewById(R.id.editText);
        link = (EditText)v.findViewById(R.id.editText2);
        description = (EditText)v.findViewById(R.id.editText3);
        b1=(Button)v.findViewById(R.id.fetch);
        b2=(Button)v.findViewById(R.id.result);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                obj = new HandleXML(finalUrl);
                obj.fetchXML();
                while(obj.parsingComplete);
                title.setText(obj.getTitle());
                link.setText(obj.getLink());
                description.setText(obj.getDescription());
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent in=new Intent(getContext(),RssResult.class);
                startActivity(in);
            }
        });

        return v;
    }

}
