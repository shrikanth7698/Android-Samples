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

public class WriteStorage extends Fragment {
    View v;
    TextView t1,t2,t3;
    Button b;
    String storagetype;
    File internal;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_write_storage,container,false);
        t1 = (TextView)v.findViewById(R.id.filecontent);
        t2 = (TextView)v.findViewById(R.id.fname);
        t3 = (TextView)v.findViewById(R.id.foname);
        b = (Button)v.findViewById(R.id.save);
        internal = Environment.getExternalStorageDirectory();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText()==null){
                    Toast.makeText(getContext(),"Enter the Content for the file",Toast.LENGTH_SHORT).show();
                } else if(t3.getText()==null){
                    Toast.makeText(getContext(),"Enter the folder name",Toast.LENGTH_SHORT).show();
                }
                else if(t2.getText()==null){
                    Toast.makeText(getContext(),"Enter the File Name",Toast.LENGTH_SHORT).show();
                }
                else{
                    String s = internal.toString()+"/"+t3.getText().toString();
                    File finaldir = new File(s);
                    if(!finaldir.exists()){
                        boolean result = finaldir.mkdirs();
                        System.out.println(result);
                    }
                    else{
                        System.out.println("Wrong interpretation");
                    }
                    File f = new File(finaldir,t2.getText().toString()+".txt");
                    try {
                        f.createNewFile();
                        byte[] data = t1.getText().toString().getBytes();
                        FileOutputStream fos = new FileOutputStream(f);
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
