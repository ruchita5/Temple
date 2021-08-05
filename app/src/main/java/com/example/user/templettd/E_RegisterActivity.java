package com.example.user.templettd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.timessquare.CalendarPickerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class E_RegisterActivity extends AppCompatActivity {
    Calendar calendar;
    CalendarView calendarView;
    int id=-1;
    String dttemp;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_e__register );

        final String startdate;
        final String enddate;
        Button b1;
        b1=findViewById ( R.id.btn_er );
        Bundle bundle=getIntent ().getExtras ();
        id=bundle.getInt ( "eid" );
        startdate=bundle.getString ( "start" );
        enddate=bundle.getString ( "end" );
        Log.d("dates",""+startdate+" "+enddate);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        calendar.set(Calendar.YEAR, 2012);

        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);
        calendarView = findViewById(R.id.calendarView);
        Calendar calendar = Calendar.getInstance();

        String[] s=enddate.split ( "-" );
        String[] d=s[0].toString ().split ( ":" );
        Log.d("Sad",""+d[1].substring ( 1,d[1].length ()) );
        int year=Integer.parseInt ( d[1].substring ( 1,d[1].length ()) );
        int month=Integer.parseInt ( s[1] );
        int day=Integer.parseInt ( s[2] );
        calendar.set ( year,month-1,day );
        long endOfMonth = calendar.getTimeInMillis();

        calendar = Calendar.getInstance();
        String[] s1=startdate.split ( "-" );
        String[] d1=s1[0].toString ().split ( ":" );
        Log.d("Sad",""+d[1].substring ( 1,d1[1].length ()) );
        int year1=Integer.parseInt ( d[1].substring ( 1,d1[1].length ()) );
        int month1=Integer.parseInt ( s1[1] );
        int day1=Integer.parseInt ( s1[2] );
        calendar.set ( year1,month1-1,day1 );
        long startOfMonth = calendar.getTimeInMillis();

        calendarView.setMaxDate(endOfMonth);
        calendarView.setMinDate(startOfMonth);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String msg = "Selected date Day: " + i2 + " Month : " + (i1 + 1) + " Year " + i;
                dttemp=i+"-"+(i1+1)+"-"+i2;
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });


        b1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String url = "http://"+ConFigurationUtil.IpConfig+"/Temple/ER_insert.php?Event_type_id="+id+"&Event_date="+dttemp+"&Reg_id="+signin_fetch.user_id+"&Status=Active";
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest req=new JsonArrayRequest( url,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            try{
                                JSONObject obj=response.getJSONObject(0);
                                String msg=obj.getString("msg");
                                if(msg.equalsIgnoreCase("Success")){
                                    Toast.makeText(getApplicationContext(),"Registered for Event",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(req);
            }
        } );
    }


}