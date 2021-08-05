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

public class vishesha_sevalu extends Fragment {
    View view;
    ListView lst_visheshasevalu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.vishesha_sevalu, null);
        lst_visheshasevalu = (ListView) view.findViewById(R.id.lst_visheshasevalu);


        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest req = new JsonArrayRequest("http://"+ConFigurationUtil.IpConfig+"/Temple/ED_select.php?Event_name=Vishesha_Sevalu", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = response.length();
                visheshasevaludisplay.arrdata7.clear();
                for (int i = 0; i < count; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        visheshasevaludisplay obj9 = new visheshasevaludisplay();
                        obj9.setEid(obj.getString("Event_id"));
                        obj9.setStartdate("StartDate: " + obj.getString("Event_startdate"));
                        obj9.setEnddate("EndDate: " + obj.getString("Event_enddate"));
                        obj9.setTime("Time:" + obj.getString("Event_time"));
                        visheshasevaludisplay.arrdata7.add(obj9);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
                visheshasevaluadapter obj4 = new visheshasevaluadapter(getActivity().getApplicationContext());
                lst_visheshasevalu.setAdapter(obj4);
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




























