package com.example.user.templettd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.example.user.templettd.HomeActivity.editor;

public class UpdateActivity extends AppCompatActivity {

    TextView nm,add,email,mobile;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_update );
        final Bundle bundle=getIntent ().getExtras ();

        nm=(TextView)findViewById ( R.id.edtname);
        add=(TextView)findViewById ( R.id.edtadd );
        email=(TextView)findViewById ( R.id.edtemail );
        mobile=(TextView)findViewById ( R.id.edtmob );
        b=(Button)findViewById ( R.id.btnreg );

        nm.setText ( bundle.getString ( "Name" ) );
        add.setText ( bundle.getString ( "Address" ) );
        email.setText ( bundle.getString ( "Email" ) );
        mobile.setText ( bundle.getString ( "Mobile" ) );

        b.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String url = "http://"+ConFigurationUtil.IpConfig+"/Temple/UR_update.php?name="+nm.getText().toString()+"" +
                        "&mobile_no="+mobile.getText().toString()+"&address="+add.getText().toString() +
                        "&email_id="+email.getText().toString()+"&id="+bundle.getString ( "ID" );
                Log.d ("url",url);
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest req=new JsonArrayRequest( url,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            try{
                                JSONObject obj=response.getJSONObject(0);
                                int msg=obj.getInt ("msg");
                                if(msg>0){
                                    Toast.makeText(getApplicationContext(),"Updated Details Sucessfully",Toast.LENGTH_SHORT).show();
                                    editor.putString("name",nm.getText ().toString ());
                                    editor.putString("mobile",mobile.getText ().toString ());
                                    editor.putString("address",add.getText ().toString ());
                                    editor.putString("email_id",email.getText ().toString ());
                                    editor.commit();
                                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                    finish();
                                    startActivity(intent);
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
        } );
    }

    private boolean validateData(){
        if(nm.getText().toString().equals("")) {
            return(false);
        }else if(mobile.getText().toString().equals("")) {
            return(false);
        }else if(add.getText().toString().equals("")) {
            return(false);
        }else if(email.getText().toString().equals("")) {
            return (false);
        }
            return(true);
        }


    }


