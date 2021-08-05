package com.example.user.templettd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Registryadapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    public  Registryadapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return Registrydisplay.arrdatareg.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(R.layout.registry_lst,null);
        TextView sdate=(TextView)view.findViewById(R.id.sdate);
        TextView edate=(TextView)view.findViewById(R.id.edate);
        TextView title=(TextView)view.findViewById(R.id.titlereg);
        sdate.setText(Registrydisplay.arrdatareg.get(i).getSdate());
        edate.setText(Registrydisplay.arrdatareg.get(i).getEdate());
        title.setText(Registrydisplay.arrdatareg.get(i).getTitle());
        return view;

    }
}
