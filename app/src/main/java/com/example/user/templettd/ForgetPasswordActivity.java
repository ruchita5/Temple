package com.example.user.templettd;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import static com.example.user.templettd.HomeActivity.editor;

public class ForgetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String verifyid;
    Boolean flag=false;
    Button b1,b2,b3;
    EditText e1,e2,e3,e4;
    String phone=null;
    int id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_forget_password );

        mAuth=FirebaseAuth.getInstance ();

        e1=(EditText)findViewById ( R.id.name );
        e2=(EditText)findViewById ( R.id.pin );
        e3=(EditText)findViewById ( R.id.pass );
        e4=(EditText)findViewById ( R.id.conpass );

        e2.setEnabled ( false );
        e3.setEnabled ( false );
        e4.setEnabled ( false );

        b1=(Button)findViewById ( R.id.send );
        b2=(Button)findViewById ( R.id.verify );
        b3=(Button)findViewById ( R.id.reset );

        b2.setEnabled ( false );
        b3.setEnabled ( false );

        b1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String url = "http://"+ConFigurationUtil.IpConfig+"/Temple/mobile_no_select.php?user_name="+e1.getText().toString();
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                JsonArrayRequest req=new JsonArrayRequest( url,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0){
                            try{
                                JSONObject obj=response.getJSONObject(0);
                                String msg=obj.getString("msg");
                                if(msg.equalsIgnoreCase("Success")){
                                    Toast.makeText(getApplicationContext(),"Matched Username OTP send to Registered no",Toast.LENGTH_SHORT).show();
                                    id=obj.getInt ( "ID" );
                                    phone=obj.getString("mobile_no").toString ();
                                    sentverifycode ( phone );

                                    e1.setEnabled ( false );
                                    b1.setEnabled ( false );
                                    e2.setEnabled ( true );
                                    b2.setEnabled ( true );
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent ( getApplicationContext (),HomeActivity.class );
                                    startActivity ( i );
                                }
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(),"Something went wrong!!",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent ( getApplicationContext (),HomeActivity.class );
                                startActivity ( i );
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

        b2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String c=e2.getText ().toString ();
                if(c.isEmpty () || c.length ()<6){
                    e1.setText ( "Enter code....." );
                    e1.requestFocus ();
                    return;
                }
                verifycode ( c );
                 e3.setEnabled ( true );
                    e4.setEnabled ( true );
                    b3.setEnabled ( true );

            }
        } );

        b3.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Boolean f=e3.getText().toString().equals(e4.getText().toString());
                if(flag && f) {
                    String url = "http://" + ConFigurationUtil.IpConfig + "/Temple/pass_update.php?id=" + id + "&pass=" + e4.getText ();
                    Log.d ( "url", "" + url );
                    RequestQueue queue = Volley.newRequestQueue ( getApplicationContext () );
                    JsonArrayRequest req = new JsonArrayRequest ( url, new Response.Listener <JSONArray> () {
                        @Override
                        public void onResponse(JSONArray response) {
                            if (response.length () > 0) {
                                try {
                                    JSONObject obj = response.getJSONObject ( 0 );
                                    int msg = obj.getInt ( "msg" );
                                    if (msg > 0) {
                                        Toast.makeText ( getApplicationContext (), "Reseted Sucessfully", Toast.LENGTH_LONG ).show ();
                                    } else {
                                        Toast.makeText ( getApplicationContext (), "Something went wrong!!", Toast.LENGTH_SHORT ).show ();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText ( getApplicationContext (), "Something went wrong!!", Toast.LENGTH_SHORT ).show ();
                                }
                            }
                        }
                    }, new Response.ErrorListener () {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText ( getApplicationContext (), error.getMessage (), Toast.LENGTH_SHORT ).show ();

                        }
                    } );
                    queue.add ( req );
                }
                else {
                    Toast.makeText ( getApplicationContext (), "Something went wrong!!", Toast.LENGTH_SHORT ).show ();

                }
            }
        } );


    }

    private void verifycode(String code){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential ( verifyid,code );
        signin(credential);
    }

    private void signin(PhoneAuthCredential credential){
        mAuth.signInWithCredential ( credential ).addOnCompleteListener ( new OnCompleteListener <AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful ()){
                    flag=true;
                    Toast.makeText ( ForgetPasswordActivity.this,"Authorized User",Toast.LENGTH_LONG ).show ();
                }else{
                    Toast.makeText ( ForgetPasswordActivity.this,""+task.getException ().getMessage (),Toast.LENGTH_LONG ).show ();
                }

            }
        } );
    }

    private void sentverifycode(String number)
    {
        PhoneAuthProvider.getInstance ().verifyPhoneNumber (
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallback
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent ( s,forceResendingToken );
            verifyid=s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode ();
            if(code!=null){
                verifycode ( code );
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText ( ForgetPasswordActivity.this,""+e.getMessage (),Toast.LENGTH_LONG ).show ();
        }
    };
}
