package com.example.user.templettd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class abhishekam extends Fragment {
    View view;
    ListView lst_abhishekam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.abhishekam, container,false);
        lst_abhishekam = (ListView) view.findViewById(R.id.lst_abhishekam);

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest req = new JsonArrayRequest("http://"+ConFigurationUtil.IpConfig+"/Temple/ED_select.php?Event_name=Abhishekam", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = response.length();
                abhishekamdisplay.arrdata3.clear();
                for (int i = 0; i < count; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        abhishekamdisplay obj9 = new abhishekamdisplay();
                        obj9.setEid(obj.getString("Event_id"));
                        obj9.setStartdate("StartDate: " + obj.getString("Event_startdate"));
                        obj9.setEnddate("EndDate: " + obj.getString("Event_enddate"));
                        obj9.setTime("Time:" + obj.getString("Event_time"));
                        abhishekamdisplay.arrdata3.add(obj9);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
                abhishekamadapter obj4 = new abhishekamadapter(getActivity().getApplicationContext());
                lst_abhishekam.setAdapter(obj4);
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




















