package com.example.sai_h.labex;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sai_h.labex.activity.AlarmActivity;
import com.example.sai_h.labex.activity.BasicUIActivity;
import com.example.sai_h.labex.activity.EventListenerActivity;
import com.example.sai_h.labex.activity.FontColorActivity;
import com.example.sai_h.labex.activity.GPSLocationActivity;
import com.example.sai_h.labex.activity.GraphicsPrimitivesActivity;
import com.example.sai_h.labex.activity.MultithreadingActivity;
import com.example.sai_h.labex.activity.NativeCalculatorActivity;
import com.example.sai_h.labex.activity.RSSFeedActivity;
import com.example.sai_h.labex.activity.SmsActivity;
import com.example.sai_h.labex.activity.SqlActivity;
import com.example.sai_h.labex.activity.TableLayoutActivity;
import com.example.sai_h.labex.activity.WriteStorageActivity;
import com.example.sai_h.labex.utils.CustomTypefaceSpan;
import com.example.sai_h.labex.utils.FontChanger;
import com.example.sai_h.labex.utils.TypefaceSpan;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Typeface normal,bold;
    FontChanger normalFontChanger,boldFontChanger;
    NavigationView navigationView;
    DrawerLayout drawer;
    Class fragmentClass;
    public static Fragment fragment;
    String currentFragment="";
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent in = new Intent(this,SmsReceiver.class);
        startService(in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        init();
        fragmentInit();
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            count++;
            if(count==1) {
                Toast.makeText(HomeActivity.this,"Press once again to exit !",Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        count=0;
                    }
                },5000);
            }
            else {
                if(count==2) {
                    count=0;
                    super.onBackPressed();
                }
            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.view_code){
            if(currentFragment.length()!=0){

                switch(currentFragment)
                {
                    case "Basic_UI":{
                        startActivity(new Intent(HomeActivity.this, BasicUIActivity.class));
                        break;
                    }
                    case "Font_color":{
                        startActivity(new Intent(HomeActivity.this, FontColorActivity.class));
                        break;
                    }
                    case "Table_layout":{
                        startActivity(new Intent(HomeActivity.this, TableLayoutActivity.class));
                        break;
                    }
                    case "Event_listener":{
                        startActivity(new Intent(HomeActivity.this, EventListenerActivity.class));
                        break;
                    }
                    case "Calculator":{
                        startActivity(new Intent(HomeActivity.this, NativeCalculatorActivity.class));
                        break;
                    }
                    case "Graphics_primitives":{
                        startActivity(new Intent(HomeActivity.this, GraphicsPrimitivesActivity.class));
                        break;
                    }
                    case "SQL":{
                        startActivity(new Intent(HomeActivity.this, SqlActivity.class));
                        break;
                    }
                    case "RSS":{
                        startActivity(new Intent(HomeActivity.this, RSSFeedActivity.class));
                        break;
                    }
                    case "Multi_Threading":{
                        startActivity(new Intent(HomeActivity.this, MultithreadingActivity.class));
                        break;
                    }
                    case "Location":{
                        startActivity(new Intent(HomeActivity.this, GPSLocationActivity.class));
                        break;
                    }
                    case "SD":{
                        startActivity(new Intent(HomeActivity.this, WriteStorageActivity.class));
                        break;
                    }
                    case "SMS":{
                        startActivity(new Intent(HomeActivity.this, SmsActivity.class));
                        break;
                    }
                    case "Alarm":{
                        startActivity(new Intent(HomeActivity.this, AlarmActivity.class));
                        break;
                    }

                }
            }
            else {

                Snackbar bar = Snackbar.make(findViewById(R.id.rootRL), "RIP Android Lab", Snackbar.LENGTH_LONG);
                bar.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.nav_home){
            currentFragment="";
            fragmentClass = HomeFragment.class;
            SpannableString s = new SpannableString("Android Samples");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        }
        else if(id==R.id.nav_basic_ui_design){
            currentFragment = "Basic_UI";
            fragmentClass = BasicUIFragment.class;
            SpannableString s = new SpannableString("Basic UI Design");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_font_color){
            currentFragment = "Font_color";
            fragmentClass = FontColorFragment.class;
            SpannableString s = new SpannableString("Font and Color Application");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);

        } else if(id==R.id.nav_table_layout){

            currentFragment = "Table_layout";
            fragmentClass = TableLayoutFragment.class;
            SpannableString s = new SpannableString("Basic UI Design");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);

        } else if(id==R.id.nav_event_listener){
            currentFragment = "Event_listener";
            fragmentClass = EventListenerFragment.class;
            SpannableString s = new SpannableString("Event Listener");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);

        } else if(id==R.id.nav_calculator){
            currentFragment = "Calculator";
            fragmentClass = NativeCalculatorFragment.class;
            SpannableString s = new SpannableString("Native Calculator");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_graphics_primitives){
            currentFragment = "Graphics_primitives";
            fragmentClass = GraphicsPrimitiveFragment.class;
            SpannableString s = new SpannableString("Graphics Primitives");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_sql){
            currentFragment = "SQL";
            fragmentClass = SqlFragment.class;
            SpannableString s = new SpannableString("Sql Database");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_rss){
            currentFragment = "RSS";
            fragmentClass = RssFeedFragment.class;
            SpannableString s = new SpannableString("Rss Feed");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_multi_threading){
            currentFragment = "Multi_Threading";
            fragmentClass = MultithreadingFragment.class;
            SpannableString s = new SpannableString("MultiThreading");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_location){
            currentFragment = "Location";
            fragmentClass = GPSLocationFragment.class;
            SpannableString s = new SpannableString("Gps Location");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_sd){
            currentFragment = "SD";
            fragmentClass = WriteStorageFragment.class;
            SpannableString s = new SpannableString("Internal Storage Writing");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_sms){
            currentFragment = "SMS";
            fragmentClass = SmsFragment.class;
            SpannableString s = new SpannableString("Send SMS");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_alarm){
            currentFragment = "Alarm";
            fragmentClass = AlarmFragment.class;
            SpannableString s = new SpannableString("Alarm Clock");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        return true;
    }

    private void applyFontToMenuItem(MenuItem mi) {
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , bold), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    public void init(){
        SpannableString s = new SpannableString("Android Samples");
        s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(s);
        normal = Typeface.createFromAsset(getAssets(), "fonts/product_san_regular.ttf");
        bold = Typeface.createFromAsset(getAssets(),"fonts/product_sans_bold.ttf");
        normalFontChanger = new FontChanger(normal);
        boldFontChanger = new FontChanger(bold);
        normalFontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
        TextView title = navigationView.getHeaderView(0).findViewById(R.id.titleTV);
        title.setTypeface(bold);
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for applying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }

    public void fragmentInit(){
        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = HomeFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        navigationView.getMenu().getItem(0).setChecked(true);
    }
}
