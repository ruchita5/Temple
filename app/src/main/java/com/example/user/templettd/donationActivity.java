package com.example.user.templettd;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class donationActivity extends AppCompatActivity {
TextView don;
EditText edt_don;
Button btn_donation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        don=(TextView)findViewById(R.id.txt_don);
        edt_don=(EditText)findViewById(R.id.edt_don);
        btn_donation=(Button)findViewById(R.id.btn_donation);
        getSupportActionBar().setTitle("Donation");

        btn_donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("upi://pay?pa=9922048301@upi&am="+edt_don.getText().toString()+"&cu=INR");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
