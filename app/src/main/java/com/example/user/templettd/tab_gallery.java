package com.example.user.templettd;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class tab_gallery extends Fragment {
    View view;
    ImageView img1,img2,img3,img4,img5,img6,img7;
    ImageView l11,l21,l31,l41,l51,l61,l71;
    ImageView l12,l22,l32,l42,l52,l62,l72;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_gallery,container,false);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        img1= view.findViewById(R.id.img1);
        img2= view.findViewById(R.id.img2);
        img3= view.findViewById(R.id.img3);
        img4= view.findViewById(R.id.img4);
        img5= view.findViewById(R.id.img5);
        img6= view.findViewById(R.id.img5);
        img7= view.findViewById(R.id.img5);

        l11=(ImageView)view.findViewById ( R.id.l11 );
        l12=(ImageView)view.findViewById ( R.id.l12 );

        l21=(ImageView)view.findViewById ( R.id.l21 );
        l22=(ImageView)view.findViewById ( R.id.l22 );

        l31=(ImageView)view.findViewById ( R.id.l31 );
        l32=(ImageView)view.findViewById ( R.id.l32 );

        l41=(ImageView)view.findViewById ( R.id.l41 );
        l42=(ImageView)view.findViewById ( R.id.l42 );

        l51=(ImageView)view.findViewById ( R.id.l51 );
        l52=(ImageView)view.findViewById ( R.id.l52 );

        l61=(ImageView)view.findViewById ( R.id.l61 );
        l62=(ImageView)view.findViewById ( R.id.l62 );

        l71=(ImageView)view.findViewById ( R.id.l71 );
        l72=(ImageView)view.findViewById ( R.id.l72 );

        l11.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                share(img1,"img1");
            }
        } );
        l12.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    download ( img1 );
                    Toast.makeText ( getActivity ().getApplicationContext (),"Downloaded Successfully",Toast.LENGTH_LONG ).show ();
                    Log.d("Sucess download","ads");
                } catch (IOException e) {
                    Toast.makeText ( getActivity ().getApplicationContext (),"Image Download Failed",Toast.LENGTH_LONG ).show ();
                }
            }
        } );

        l21.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                share(img2,"img2");
            }
        } );

        l22.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    download ( img2 );
                    Toast.makeText ( getActivity ().getApplicationContext (),"Downloaded Successfully",Toast.LENGTH_LONG ).show ();
                    Log.d("Sucess download","ads");
                } catch (IOException e) {
                    Toast.makeText ( getActivity ().getApplicationContext (),"Image Download Failed",Toast.LENGTH_LONG ).show ();
                }
            }
        } );

        l31.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                share(img3,"img3");
            }
        } );
        l32.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    download ( img3 );
                    Toast.makeText ( getActivity ().getApplicationContext (),"Downloaded Successfully",Toast.LENGTH_LONG ).show ();
                    Log.d("Sucess download","ads");
                } catch (IOException e) {
                    Toast.makeText ( getActivity ().getApplicationContext (),"Image Download Failed",Toast.LENGTH_LONG ).show ();
                }
            }
        } );

        l41.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                share(img4,"img4");
            }
        } );
        l42.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    download ( img4 );
                    Toast.makeText ( getActivity ().getApplicationContext (),"Downloaded Successfully",Toast.LENGTH_LONG ).show ();
                    Log.d("Sucess download","ads");
                } catch (IOException e) {
                    Toast.makeText ( getActivity ().getApplicationContext (),"Image Download Failed",Toast.LENGTH_LONG ).show ();
                }
            }
        } );
        l51.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                share(img5,"img5");
            }
        } );
        l52.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    download ( img5 );
                    Toast.makeText ( getActivity ().getApplicationContext (),"Downloaded Successfully",Toast.LENGTH_LONG ).show ();
                    Log.d("Sucess download","ads");
                } catch (IOException e) {
                    Toast.makeText ( getActivity ().getApplicationContext (),"Image Download Failed",Toast.LENGTH_LONG ).show ();
                }
            }
        } );

        l61.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                share(img6,"img6");
            }
        } );
        l62.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    download ( img6 );
                    Toast.makeText ( getActivity ().getApplicationContext (),"Downloaded Successfully",Toast.LENGTH_LONG ).show ();
                    Log.d("Sucess download","ads");
                } catch (IOException e) {
                    Toast.makeText ( getActivity ().getApplicationContext (),"Image Download Failed",Toast.LENGTH_LONG ).show ();
                }
            }
        } );

        l71.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                share(img7,"img7");
            }
        } );
        l72.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                try {
                    download ( img7 );
                    Toast.makeText ( getActivity ().getApplicationContext (),"Downloaded Successfully",Toast.LENGTH_LONG ).show ();
                    Log.d("Sucess download","ads");
                } catch (IOException e) {
                    Toast.makeText ( getActivity ().getApplicationContext (),"Image Download Failed",Toast.LENGTH_LONG ).show ();
                }
            }
        } );



        return view;
    }

    public void share(ImageView img1,String s){
        Bitmap icon = ((BitmapDrawable)img1.getDrawable()).getBitmap(); ;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator +"file1.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/file1.jpg"));
        startActivity(Intent.createChooser(share, "Share Image"));
    }

    public void download(ImageView img1) throws IOException {
        BitmapDrawable draw = (BitmapDrawable) img1.getDrawable();
        Bitmap bitmap = draw.getBitmap();

        FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/Temple");
        dir.mkdirs();
        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, fileName);
        outStream = new FileOutputStream(outFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        Toast.makeText ( getActivity ().getApplicationContext (),"Image Downloaded",Toast.LENGTH_LONG );
        outStream.flush();
        outStream.close();
    }
}
