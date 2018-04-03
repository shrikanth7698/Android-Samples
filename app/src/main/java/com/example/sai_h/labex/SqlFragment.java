package com.example.sai_h.labex;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class SqlFragment extends Fragment implements View.OnClickListener{
    View v;
    int fl= 0;
    SQLiteDatabase db;
    Button insertBTN,updateBTN,deleteBTN,displayBTN,clearBTN;
    EditText name,phno,email,deletephno,displayphno;
    public SqlFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ViewGroup to be inflated into the activity
        v = inflater.inflate(R.layout.fragment_sql,container,false);
        //DataBase is created
        db=this.getActivity().openOrCreateDatabase("testdb", Context.MODE_PRIVATE,null);
        //Table Creation
        db.execSQL("CREATE TABLE IF NOT EXISTS detail(name VARCHAR,phone VARCHAR,email VARCHAR);");
        //All the view elements of the fragment are identified using the viewgroup. Here viewgroup is stored in variable v
        //InCase of activity this is not necessary. Eg: Button b = findViewById(R.id.BTN);
        name = v.findViewById(R.id.name);
        phno = v.findViewById(R.id.phoneno);
        email = v.findViewById(R.id.email);
        deletephno = v.findViewById(R.id.deletephno);
        displayphno = v.findViewById(R.id.displayphno);
        insertBTN = v.findViewById(R.id.insertBTN);
        updateBTN = v.findViewById(R.id.updateBTN);
        deleteBTN = v.findViewById(R.id.deleteBTN);
        displayBTN = v.findViewById(R.id.displayBTN);
        clearBTN = v.findViewById(R.id.clearBTN);
        //onClick listeners are set for the various Buttons(Operations)
        insertBTN.setOnClickListener(this);
        updateBTN.setOnClickListener(this);
        deleteBTN.setOnClickListener(this);
        displayBTN.setOnClickListener(this);
        clearBTN.setOnClickListener(this);
        return v; //Returns the Viewgroup to the activity class for inflation
    }
    @Override
    public void onClick(View view) {
        //Insert Operation
        if(view==insertBTN){
            if(name.getText().toString().trim().length()==0&&phno.getText().toString().trim().length()==0&&email.getText().toString().trim().length()==0){
                Toast.makeText(getContext(),"Enter all the details",Toast.LENGTH_LONG).show();
            }
            else{
                Cursor c = db.rawQuery("SELECT * FROM detail;",null);
                c.moveToFirst();
                do{
                    System.out.println(c.getString(1));
                    if(c.getString(c.getColumnIndex("phone")).equals(phno.getText().toString())){
                        fl = 1;
                        break;
                    }
                }while(c.moveToNext());
                if(fl==0){
                    db.execSQL("INSERT INTO detail values ('"+name.getText().toString()+"','"+phno.getText().toString()+"','"+email.getText().toString()+"');");
                    Toast.makeText(getContext(),"Entry has been inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(),"Entry already Exists",Toast.LENGTH_SHORT).show();
                    fl=0;
                }
            }
        }
        //Update Operation
        if(view==updateBTN){
            if(name.getText().toString().trim().length()==0&&phno.getText().toString().trim().length()==0&&email.getText().toString().trim().length()==0){
                Toast.makeText(getContext(),"Enter all the details",Toast.LENGTH_LONG).show();
            }
            else{
                Cursor c = db.rawQuery("SELECT * FROM detail;",null);
                c.moveToFirst();
                do{
                    System.out.println(c.getString(1));
                    if(c.getString(1).equals(phno.getText().toString())){
                        System.out.println("Entered");
                        fl = 1;
                        break;
                    }
                }while(c.moveToNext());
                if(fl==1){
                    db.execSQL("UPDATE detail SET name='"+name.getText().toString()+"',email='"+email.getText().toString()+"' WHERE phone ='"+phno.getText().toString()+"';");
                    Toast.makeText(getContext(),"Entry has been updated",Toast.LENGTH_SHORT).show();
                    fl=0;
                }
                else{
                    Toast.makeText(getContext(),"Entry does not Exist",Toast.LENGTH_SHORT).show();
                }
            }
        }
        //Delete Operation
        if(view==deleteBTN){
            if(deletephno.getText().toString().trim().length()==0){
                Toast.makeText(getContext(),"Enter the Phone Number",Toast.LENGTH_SHORT);
            }
            else{
                Cursor c = db.rawQuery("SELECT * FROM detail;",null);
                c.moveToFirst();
                do{
                    if(c.getString(c.getColumnIndex("phone")).equals(deletephno.getText().toString())){
                        fl = 1;
                        break;
                    }
                }while(c.moveToNext());
                if(fl==1){
                    db.execSQL("DELETE FROM detail WHERE phone='"+deletephno.getText().toString()+"';");
                    Toast.makeText(getContext(),"Entry deleted successfully",Toast.LENGTH_SHORT).show();
                    fl=0;
                }
                else{
                    Toast.makeText(getContext(),"Entry does not Exist",Toast.LENGTH_SHORT).show();
                }
            }
        }
        //Select Operation
        if(view==displayBTN){
            if(displayphno.getText().toString().trim().length()==0){
                Toast.makeText(getContext(),"Enter the Phone Number",Toast.LENGTH_SHORT);
            }
            else{
                Cursor c = db.rawQuery("SELECT * FROM detail;",null);
                c.moveToFirst();
                do{
                    if(c.getString(c.getColumnIndex("phone")).equals(displayphno.getText().toString())){
                        fl = 1;
                        break;
                    }
                }while(c.moveToNext());
                if(fl==1){
                    Toast.makeText(getContext(),"Name: "+c.getString(0)+"\nPhone Number: "+c.getString(1)+"\nE-mail: "+c.getString(2),Toast.LENGTH_LONG).show();
                    fl=0;
                }
                else{
                    Toast.makeText(getContext(),"Entry does not Exist",Toast.LENGTH_SHORT).show();
                }
            }
        }
        //Clear Fields Operation
        if(view==clearBTN){
            ((EditText)v.findViewById(R.id.name)).setText("");
            ((EditText)v.findViewById(R.id.phoneno)).setText("");
            ((EditText)v.findViewById(R.id.email)).setText("");
            ((EditText)v.findViewById(R.id.deletephno)).setText("");
            ((EditText)v.findViewById(R.id.displayphno)).setText("");
            Toast.makeText(getContext(),"Fields are cleared",Toast.LENGTH_SHORT).show();
        }

    }
}
