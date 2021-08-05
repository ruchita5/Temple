package com.example.user.templettd;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventsnaviActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        LinearLayout profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventsnavi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View view = navigationView.getHeaderView(0);
        TextView header = (TextView)view.findViewById(R.id.txt_user_name);
        header.setText(signin_fetch.name);

        TextView header1 = (TextView)view.findViewById(R.id.textView);
        header1.setText(signin_fetch.email_id);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.cntr,new home_tab());
        ft.commit();

        profile=findViewById(R.id.linearProfile);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UserProfile.class);
                startActivity(intent);
            }
        });


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.eventsnavi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Intent EventsnaviActivity=new Intent(getApplicationContext(),EventsnaviActivity.class);
            startActivity(EventsnaviActivity);
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if(id==R.id.nav_home){
            ft.replace(R.id.cntr,new home_tab());
            ft.commit();
            getSupportActionBar().setTitle("Home");
        }
        else if(id==R.id.nav_registry){
            ft.replace(R.id.cntr,new Registryactivity());
            ft.commit();
            getSupportActionBar().setTitle("My Registry");
        }


        else if (id == R.id.nav_sriwarisevalu) {
            ft.replace(R.id.cntr,new sriwari_sevalu() );
            ft.commit();
            getSupportActionBar().setTitle("Sriwari Sevalu");
            // Handle the camera action
        } else if (id == R.id.nav_archana) {
            ft.replace(R.id.cntr,new archana() );
            ft.commit();
            getSupportActionBar().setTitle("Archana");


        } else if (id == R.id.nav_abhishekam) {
            ft.replace(R.id.cntr,new abhishekam() );
            ft.commit();
            getSupportActionBar().setTitle("Abhishekam");


        } else if (id == R.id.nav_visheshasevalu) {
            ft.replace(R.id.cntr,new vishesha_sevalu() );
            ft.commit();
            getSupportActionBar().setTitle("Vishesha Sevalu");


        } else if (id == R.id.nav_shashwataabhishekam) {
            ft.replace(R.id.cntr,new shashwata_abhishekam() );
            ft.commit();
            getSupportActionBar().setTitle("Shashwata Abhishekam");


        } else if (id == R.id.nav_specialsevalu) {
            ft.replace(R.id.cntr,new special_sevalu() );
            ft.commit();
            getSupportActionBar().setTitle("Special Sevalu");


        }
        else if (id == R.id.nav_vaikuntaekadashi) {
            ft.replace(R.id.cntr,new vaikuntaekadashi_sevalu() );
            ft.commit();
            getSupportActionBar().setTitle("Vaikunta Ekadashi Sevalu");


        }
        else if (id == R.id.nav_donation) {
            Intent intent=new Intent(getApplicationContext(),donationActivity.class);
            startActivity(intent);




        } else if (id == R.id.nav_aboutus) {
            Intent intent=new Intent(getApplicationContext(),About_us_activity.class);
            startActivity(intent);
        }else if (id == R.id.nav_logout) {
            showAlertDialog();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showAlertDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SigninActivity.logout();
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                finish();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
