package com.example.sai_h.labex;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    DrawerLayout d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Intent service = new Intent(this, SmsReceiver.class);
        startService(service);
        if(Build.VERSION.SDK_INT>=23){
            if((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)) {
                Log.v(TAG,"Permission is granted");
                //File write logic here
            }
            else{
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.SEND_SMS,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},101);
            }
        }
        FragmentTransaction f = getSupportFragmentManager().beginTransaction();
        f.replace(R.id.fragment,new BasicUIFragment());
        android.support.v7.widget.Toolbar t1 = findViewById(R.id.toolbar);
        setSupportActionBar(t1);
        NavigationView n = (NavigationView)findViewById(R.id.nav);
        n.setItemIconTintList(null);
        android.support.v7.app.ActionBar a = getSupportActionBar();
        a.setDisplayHomeAsUpEnabled(true);
        a.setHomeAsUpIndicator(R.drawable.ic_menu);
        d = (DrawerLayout)findViewById(R.id.drawer);
        f.commit();
        n.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ex1: getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new BasicUIFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.ex1b: getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new FontColorFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.ex2a: getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new TableLayoutFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.ex2b: getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new EventListenerFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.ex3: getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new NativeCalculatorFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.ex4 : getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new GraphicsPrimitiveFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.sms: getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new SmsFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.file: getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new WriteStorageFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.gpstrac: getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new GPSLocationFragment()).commit();
                                    d.closeDrawer(GravityCompat.START);
                                    break;
                    case R.id.multhr :getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new MultithreadingFragment()).commit();
                                        d.closeDrawer(GravityCompat.START);
                                        break;
                }
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home: d.openDrawer(GravityCompat.START);
                                    break;
        }
        return super.onOptionsItemSelected(item);
    }

}
