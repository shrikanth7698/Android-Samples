package com.example.sai_h.labex;

import android.graphics.Typeface;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.nav_home){
            fragmentClass = HomeFragment.class;
            SpannableString s = new SpannableString("Android Samples");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        }
        else if(id==R.id.nav_basic_ui_design){
            fragmentClass = BasicUIFragment.class;
            SpannableString s = new SpannableString("Basic UI Design");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_font_color){

            fragmentClass = FontColorFragment.class;
            SpannableString s = new SpannableString("Font and Color Application");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);

        } else if(id==R.id.nav_table_layout){


            fragmentClass = TableLayoutFragment.class;
            SpannableString s = new SpannableString("Basic UI Design");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);

        } else if(id==R.id.nav_event_listener){
            fragmentClass = EventListenerFragment.class;
            SpannableString s = new SpannableString("Event Listener");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);

        } else if(id==R.id.nav_calculator){
            fragmentClass = NativeCalculatorFragment.class;
            SpannableString s = new SpannableString("Native Calculator");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_graphics_primitives){
            fragmentClass = GraphicsPrimitiveFragment.class;
            SpannableString s = new SpannableString("Graphics Primitives");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_sql){
            fragmentClass = SqlFragment.class;
            SpannableString s = new SpannableString("Sql Database");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_rss){
            fragmentClass = RssFeedFragment.class;
            SpannableString s = new SpannableString("Rss Feed");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_multi_threading){
            fragmentClass = MultithreadingFragment.class;
            SpannableString s = new SpannableString("MultiThreading");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_location){
            fragmentClass = GPSLocationFragment.class;
            SpannableString s = new SpannableString("Gps Location");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_sd){
            fragmentClass = WriteStorageFragment.class;
            SpannableString s = new SpannableString("Internal Storage Writing");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_sms){
            fragmentClass = SmsFragment.class;
            SpannableString s = new SpannableString("Send SMS");
            s.setSpan(new TypefaceSpan(this, "product_sans_bold.ttf"), 0, s.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(s);
        } else if(id==R.id.nav_alarm){
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
