package com.example.user.templettd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {
    EditText edtname,edtmob,edtadd,edtmail,edtuser,edtpass,edtconpass;
    Button btnreg,btnback2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        edtname=(EditText)findViewById(R.id.edtname);
        edtmob=(EditText)findViewById(R.id.edtmob);
        edtadd=(EditText)findViewById(R.id.edtadd);
        edtmail=(EditText)findViewById(R.id.edtemail);
        edtuser=(EditText)findViewById(R.id.edtuser);
        edtpass=(EditText)findViewById(R.id.edtpass);
        edtconpass=(EditText)findViewById(R.id.edtconpass);
        btnback2=(Button)findViewById(R.id.btn_back2);
        btnback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
        btnreg=(Button)findViewById(R.id.btnreg);

       btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://"+ConFigurationUtil.IpConfig+"/Temple/SU_insert.php?name="+edtname.getText().toString()+"" +
                        "&mobile_no="+edtmob.getText().toString()+"&address="+edtadd.getText().toString() +
                        "&email_id="+edtmail.getText().toString()+"&user_name="+edtuser.getText().toString()+"" +
                        "&password="+edtpass.getText().toString()+"";
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest req=new JsonArrayRequest( url,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            try{
                                JSONObject obj=response.getJSONObject(0);
                                String msg=obj.getString("msg");
                                if(msg.equals("Success")){
                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent EventsnaviActivity=new Intent(getApplicationContext(),EventsnaviActivity.class);
                                    startActivity(EventsnaviActivity);

                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){}
                            }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
                if(validateData()){
                    queue.add(req);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Something went wrong!",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private boolean validateData(){
        if(edtname.getText().toString().equals("")) {
            return(false);
        }else if(edtmob.getText().toString().equals("")) {
            return(false);
        }else if(edtadd.getText().toString().equals("")) {
            return(false);
        }else if(edtmail.getText().toString().equals("")) {
            return(false);
        }else if(edtuser.getText().toString().equals("")) {
            return(false);
        }else if(edtpass.getText().toString().equals("")) {
            return(false);
        }else if(edtconpass.getText().toString().equals("")){
            return(false);
        }else if(edtpass.getText().toString().equals(edtconpass.getText().toString())){


            return(true);


        }
        else {
            return(false);
        }


    }

}
