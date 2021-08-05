package com.example.user.templettd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class abhishekamadapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    public abhishekamadapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return abhishekamdisplay.arrdata3.size();
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
        view=inflater.inflate(R.layout.abhishekam_lst,null);
        final TextView startdate=(TextView)view.findViewById(R.id.startdate);
        final TextView enddate=(TextView)view.findViewById(R.id.enddate);
        TextView time=(TextView)view.findViewById(R.id.time);
        startdate.setText(abhishekamdisplay.arrdata3.get(i).getStartdate());
        enddate.setText(abhishekamdisplay.arrdata3.get(i).getEnddate());
        time.setText(abhishekamdisplay.arrdata3.get(i).getTime());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent E_RegisterActivity=new Intent(view.getContext(),E_RegisterActivity.class);
                E_RegisterActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                E_RegisterActivity.putExtra("eid",abhishekamdisplay.arrdata3.get(i).getEid());
                E_RegisterActivity.putExtra ( "start",abhishekamdisplay.arrdata3.get(i).getStartdate () );
                E_RegisterActivity.putExtra ( "end",abhishekamdisplay.arrdata3.get(i).getEnddate () );
                context.startActivity(E_RegisterActivity);
            }
        });
        return view;
    }
}
