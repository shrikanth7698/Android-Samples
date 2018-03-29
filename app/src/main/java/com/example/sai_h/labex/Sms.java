package com.example.sai_h.labex;
import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Sms extends Fragment {
    View v;
    int c = 160;
    String phno;
    boolean c1=false;
    List<String> phnnumbers;
    List<String>names;
    AutoCompleteTextView ac;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_sms,container,false);
        phno = null;
        ((TextView)v.findViewById(R.id.hint)).setText(c+" characters left");
        ContentResolver cr = getActivity().getContentResolver();
        Cursor c = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        phnnumbers = new ArrayList<String>();
        names = new ArrayList<String>();

        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber =  c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if(name!=null&&phoneNumber!=null) {
                    names.add(name);
                    phnnumbers.add(phoneNumber);
                }
            }
        }
        ac = (AutoCompleteTextView)v.findViewById(R.id.contact);
        ArrayAdapter<String> a = new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,names);
        ac.setAdapter(a);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String)adapterView.getItemAtPosition(i);
                int i1 = names.indexOf(name);
                phno = phnnumbers.get(i1);
                c1 = true;
            }
        });
        ((EditText)v.findViewById(R.id.msg)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                c--;
                ((TextView)v.findViewById(R.id.hint)).setText(c+" characters left");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ((Button)v.findViewById(R.id.send)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(c1==false){
                    Toast.makeText(getContext(),"Select a Contact",Toast.LENGTH_SHORT).show();
                }
                else if(((EditText)v.findViewById(R.id.msg)).getText()==null){
                    Toast.makeText(getContext(),"Enter the message",Toast.LENGTH_SHORT).show();
                }
                else{
                    SmsManager s = SmsManager.getDefault();
                    s.sendTextMessage(phno,null,((EditText)v.findViewById(R.id.msg)).getText().toString(),null,null);
                    Toast.makeText(getContext(),"Message Sent",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
