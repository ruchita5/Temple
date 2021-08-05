package com.example.user.templettd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class sriwari_sevalu extends Fragment {


    View view;
    ListView lst_sriwarisevalu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.sriwari_sevalu, null);
        lst_sriwarisevalu = (ListView) view.findViewById(R.id.lst_sriwarisevalu);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest req = new JsonArrayRequest("http://"+ConFigurationUtil.IpConfig+"/Temple/ED_select.php?Event_name=Sriwari_Sevalu", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = response.length();
                sriwarisevaludisplay.arrdata1.clear();
                for (int i = 0; i < count; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        sriwarisevaludisplay obj9 = new sriwarisevaludisplay();
                        obj9.setEid(obj.getString("Event_id"));
                        obj9.setStartdate("StartDate:"+obj.getString("Event_startdate"));
                        obj9.setEnddate("EndDate:"+obj.getString("Event_enddate"));
                        obj9.setTime("Time:"+obj.getString("Event_time"));
                        sriwarisevaludisplay.arrdata1.add(obj9);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }
                sriwarisevaluadapter obj10 = new sriwarisevaluadapter(getActivity().getApplicationContext());
                lst_sriwarisevalu.setAdapter(obj10);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(req);
        return view;
    }
}
