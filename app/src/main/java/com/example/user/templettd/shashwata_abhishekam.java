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

public class shashwata_abhishekam extends Fragment {
    View view;
    ListView lst_shashwataabhishekam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shashwata_abhishekam, null);
        lst_shashwataabhishekam = (ListView) view.findViewById(R.id.lst_shashwataabhishekam);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest req = new JsonArrayRequest("http://"+ConFigurationUtil.IpConfig+"/Temple/ED_select.php?Event_name=Shashwata_abhishekam", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = response.length();
                shashwataabhishekamdisplay.arrdata4.clear();
                for (int i = 0; i < count; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        shashwataabhishekamdisplay obj9 = new shashwataabhishekamdisplay();
                        obj9.setEid(obj.getString("Event_id"));
                        obj9.setStartdate("StartDate: " + obj.getString("Event_startdate"));
                        obj9.setEnddate("EndDate: " + obj.getString("Event_enddate"));
                        obj9.setTime("Time:" + obj.getString("Event_time"));
                        shashwataabhishekamdisplay.arrdata4.add(obj9);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
                shashwataabhishekamadapter obj4 = new shashwataabhishekamadapter(getActivity().getApplicationContext());
                lst_shashwataabhishekam.setAdapter(obj4);
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
