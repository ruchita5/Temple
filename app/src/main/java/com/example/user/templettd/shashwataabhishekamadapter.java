package com.example.user.templettd;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class shashwataabhishekamadapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    public  shashwataabhishekamadapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return shashwataabhishekamdisplay.arrdata4.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.shashwataabishekam_lst,null);
        TextView startdate=(TextView)view.findViewById(R.id.startdate);
        TextView enddate=(TextView)view.findViewById(R.id.enddate);
        TextView time=(TextView)view.findViewById(R.id.time);
        startdate.setText(shashwataabhishekamdisplay.arrdata4.get(i).getStartdate());
        enddate.setText(shashwataabhishekamdisplay.arrdata4.get(i).getEnddate());
        time.setText(shashwataabhishekamdisplay.arrdata4.get(i).getTime());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent E_RegisterActivity=new Intent(view.getContext(), E_RegisterActivity.class);
                E_RegisterActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                E_RegisterActivity.putExtra("eid",shashwataabhishekamdisplay.arrdata4.get(i).getEid());
                E_RegisterActivity.putExtra ( "start",shashwataabhishekamdisplay.arrdata4.get(i).getStartdate () );
                E_RegisterActivity.putExtra ( "end",shashwataabhishekamdisplay.arrdata4.get(i).getEnddate () );

                context.startActivity(E_RegisterActivity);
            }
        });
        return view;
    }
}
