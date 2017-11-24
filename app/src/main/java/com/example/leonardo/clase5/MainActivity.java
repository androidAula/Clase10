package com.example.leonardo.clase5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.leonardo.clase5.Entidades.Person;
import com.example.leonardo.clase5.Utils.MusicService;
import com.example.leonardo.clase5.Utils.MyPreferences;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FragmentProfile.OnFragmentPerfilListener,
        FragmentCustomView.OnFragmentInteractionListener {

    private TextView tvNameMenu;
    private static final int myNotification=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tvNameMenu=(TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_menuName);


        MyPreferences pref = new MyPreferences(MainActivity.this);

        FragmentProfile fragmentProfile = FragmentProfile.newInstance(pref.getUserName(),"par2");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragmentProfile)
                .commit();

        NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        Notification.Builder builder2 = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentText("Android Notifications")
                .setPriority(Notification.PRIORITY_MIN);;
        builder2.setContentTitle("Minimum priority notification");

        notificationManager.notify(1,builder2.build());

        Notification.Builder builder3 = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentText("Android Notifications")
                .setPriority(Notification.PRIORITY_LOW);;
        builder3.setContentTitle("Low priority notification");

        notificationManager.notify(2,builder3.build());


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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            FragmentProfile fragmentProfile = FragmentProfile.newInstance("par1","par2");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentProfile)
                    .commit();
        } else if (id == R.id.calls) {
           FragmentCalls fragmentCalls =new FragmentCalls();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentCalls)
                    .commit();
        }else if (id == R.id.sqlite) {
            FragmentSql fragmentSql =new FragmentSql();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentSql)
                    .commit();
        }
        else if (id == R.id.provider) {
            FragmentGetProvider fragmentGetProvider =new FragmentGetProvider();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentGetProvider)
                    .commit();
            Intent intent=new Intent(this, MusicService.class);
            stopService(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onGetPerfilSuccsses(String result) {
        Gson gson=new Gson();
        Person person=gson.fromJson(result,Person.class);
        tvNameMenu.setText(person.getFirstname());
    }


}
