package com.example.user.templettd;

import android.content.Intent;
import android.content.SharedPreferences;
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

import static com.example.user.templettd.HomeActivity.editor;

public class SigninActivity extends AppCompatActivity {
    EditText edt_user,edt_pass;
    Button btn_signin , btnback1,forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getSupportActionBar().hide();



        edt_user=(EditText)findViewById(R.id.edt_user);
        edt_pass=(EditText)findViewById(R.id.edt_pass);
        btnback1=(Button)findViewById(R.id.btn_back1);
        forget=(Button)findViewById ( R.id.btn_fp );

        forget.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent i=new Intent ( getApplicationContext (),ForgetPasswordActivity.class );
                startActivity ( i );
            }
        } );

        btnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
        btn_signin=(Button)findViewById(R.id.btn_singin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://"+ConFigurationUtil.IpConfig+"/Temple/SU_select.php?user_name="+edt_user.getText().toString()+"" +
                        "&password="+edt_pass.getText().toString()+"";
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest req=new JsonArrayRequest( url,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            try{
                                JSONObject obj=response.getJSONObject(0);
                                String msg=obj.getString("msg");
                                if(msg.equalsIgnoreCase("Success")){
                                   // Classname.id = obj.getString("id")
                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                    signin_fetch.user_id=obj.getString("user_id");
                                    signin_fetch.name=obj.getString("name");
                                    signin_fetch.mobile_no=obj.getString("mobile_no");
                                    signin_fetch.address=obj.getString("address");
                                    signin_fetch.email_id=obj.getString("email_id");
                                    signin_fetch.user_name=obj.getString("user_name");
                                    signin_fetch.password=obj.getString("password");
                                    setPref();
                                    finish();
                                    Intent EventsnaviActivity=new Intent(getApplicationContext(),EventsnaviActivity.class);
                                    startActivity(EventsnaviActivity);

                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){

                            }
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

    private void setPref() {
        editor.putString("user_id",signin_fetch.user_id);
        editor.putString("name",signin_fetch.name);
        editor.putString("mobile",signin_fetch.mobile_no);
        editor.putString("address",signin_fetch.address);
        editor.putString("email_id",signin_fetch.email_id);
        editor.putString("user_name",signin_fetch.user_name);
        editor.putString("password",signin_fetch.password);
        editor.commit();
    }


    public static void logout(){
        editor.clear();
        editor.commit();
    }

    private boolean validateData() {
        if (edt_user.getText().toString().equals("")) {
            return (false);
        } else if (edt_pass.getText().toString().equals("")) {
            return (false);
        }
            return (true);
    }

}
