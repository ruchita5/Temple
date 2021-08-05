package com.example.user.templettd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    Button btn_update;
    TextView t_t,t_n,t_m,t_a,t_e,t_un;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User Details");

        btn_update=(Button)findViewById(R.id.btnup_details);
        t_t=(TextView)findViewById(R.id.up_title);
        t_n=(TextView)findViewById(R.id.up_name);
        t_m=(TextView)findViewById(R.id.up_mob);
        t_a=(TextView)findViewById(R.id.up_add);
        t_e=(TextView)findViewById(R.id.up_email);
        t_un=(TextView)findViewById(R.id.up_user);

        t_n.setText(signin_fetch.name);
        t_m.setText(signin_fetch.mobile_no);
        t_a.setText(signin_fetch.address);
        t_e.setText(signin_fetch.email_id);
        t_un.setText(signin_fetch.user_name);

        btn_update.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent i=new Intent ( getApplicationContext (),UpdateActivity.class );
                i.putExtra ( "ID",signin_fetch.user_id );
                i.putExtra ( "Name",signin_fetch.name );
                i.putExtra ( "Mobile",signin_fetch.mobile_no );
                i.putExtra ( "Email",signin_fetch.email_id );
                i.putExtra ( "Address",signin_fetch.address );
                startActivity ( i );
            }
        } );


    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
