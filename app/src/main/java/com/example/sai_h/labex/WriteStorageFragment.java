package com.example.sai_h.labex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteStorageFragment extends Fragment {
    View v;
    TextView t1,t2,t3;
    Button b;
    String storagetype;
    File internal;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup to be inflated into the activity
        v = inflater.inflate(R.layout.fragment_write_storage,container,false);
        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v
        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);
        t1 = (TextView)v.findViewById(R.id.filecontent);
        t2 = (TextView)v.findViewById(R.id.fname);
        t3 = (TextView)v.findViewById(R.id.foname);
        b = (Button)v.findViewById(R.id.save);
        internal = Environment.getExternalStorageDirectory();
        return v; //Returns the Viewgroup to the activity class for inflation
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Listener to write file to Internal Storage
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checking whether the fields are empty or not
                if(t1.getText()==null){
                    Toast.makeText(getContext(),"Enter the Content for the file",Toast.LENGTH_SHORT).show();
                } else if(t3.getText()==null){
                    Toast.makeText(getContext(),"Enter the folder name",Toast.LENGTH_SHORT).show();
                }
                else if(t2.getText()==null){
                    Toast.makeText(getContext(),"Enter the File Name",Toast.LENGTH_SHORT).show();
                }
                else{
                    //Storing the directory as a string
                    String s = internal.toString()+"/"+t3.getText().toString();
                    //Directory is given for the file and the file is created
                    File finaldir = new File(s);
                    if(!finaldir.exists()){
                        boolean result = finaldir.mkdirs();
                        System.out.println(result);
                    }
                    //Textfile to be created is given in a new file
                    File f = new File(finaldir,t2.getText().toString()+".txt");
                    try {
                        //Text File Created
                        f.createNewFile();
                        byte[] data = t1.getText().toString().getBytes();
                        FileOutputStream fos = new FileOutputStream(f);
                        //Data written on the Text File
                        fos.write(data);
                        fos.flush();
                        fos.close();
                        Toast.makeText(getContext(), "File Created", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
