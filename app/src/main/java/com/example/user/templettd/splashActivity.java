package com.example.user.templettd;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;

public class splashActivity extends AppCompatActivity {
    /*Handler handler;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        handler=new Handler();
        mp=MediaPlayer.create(this,R.raw.start_chant);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(splashActivity.this,HomeActivity.class);
                startActivity(intent);

                finish();
            }
        },3000);}*/

    VideoView v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        getSupportActionBar ().hide ();

        v=(VideoView)findViewById ( R.id.videoView );
        Uri vid=  Uri.parse ( "android.resource://"+getPackageName()+"/"+ R.raw.video);
        v.setVideoURI(vid);

        v.setOnCompletionListener ( new MediaPlayer.OnCompletionListener () {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startActivity (new Intent (  splashActivity.this,HomeActivity.class ));
                finish ();
            }
        } );
        v.start ();
    }

}
