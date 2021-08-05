package com.example.user.templettd;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    Button btn_signin,btn_signup;
    TextView txt_name;
    SharedPreferences pref;
    public static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(check()) {
            Intent EventsnaviActivity = new Intent(getApplicationContext(), EventsnaviActivity.class);
            finish();
            startActivity(EventsnaviActivity);
        }

        getSupportActionBar().hide();

        btn_signin=(Button)findViewById(R.id.btn_signin);
        btn_signup=(Button)findViewById(R.id.btn_signup);
        txt_name=(TextView)findViewById(R.id.txt_name);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SigninActivity=new Intent(getApplicationContext(),SigninActivity.class);
                finish();
                startActivity(SigninActivity);

            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignupActivity=new Intent(getApplicationContext(),SignupActivity.class);
                finish();
                startActivity(SignupActivity);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed(){
        showAlertDialog();
    }
    private void showAlertDialog(){
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_exit_to_app_black_24dp);
        builder.setMessage("Are you sure you want to leave?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
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
    private boolean check() {
        pref = getApplicationContext().getSharedPreferences("User",0);
        editor = pref.edit();
        if(pref.getString("user_id",null)!=null){
            signin_fetch.user_id=pref.getString("user_id",null);
            signin_fetch.name=pref.getString("name",null);
            signin_fetch.mobile_no=pref.getString("mobile_no",null);
            signin_fetch.address=pref.getString("address",null);
            signin_fetch.email_id=pref.getString("email_id",null);
            signin_fetch.user_name=pref.getString("user_name",null);
            signin_fetch.password=pref.getString("password",null);
            return  true;
        }
        return  false;
    }
}
