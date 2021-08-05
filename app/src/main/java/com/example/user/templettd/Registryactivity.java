package com.example.user.templettd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Registryactivity extends Fragment {
    View view;
    ListView registry_lst;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.registry, container,false);
        registry_lst = (ListView) view.findViewById(R.id.lst_registry);

        final Registryadapter reg1 = new  Registryadapter(getActivity().getApplicationContext());
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonArrayRequest req = new JsonArrayRequest("http://"+ConFigurationUtil.IpConfig+"/Temple/ED_select.php?uid="+signin_fetch.user_id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = response.length();
                Registrydisplay.arrdatareg.clear();
                for (int i = 0; i < count; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Registrydisplay reg = new Registrydisplay();
                        reg.setEventid (obj.getInt ("Event_id"));
                        reg.setTitle("Title:" + obj.getString("title"));
                        reg.setSdate("StartDate: " + obj.getString("sdate"));
                        reg.setEdate("EndDate: " + obj.getString("edate"));
                        Registrydisplay.arrdatareg.add(reg);
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                registry_lst.setAdapter(reg1);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(req);

        registry_lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Registrydisplay r=Registrydisplay.arrdatareg.get (position);
                int e=r.getEventid ();
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url = "http://"+ConFigurationUtil.IpConfig+"/Temple/ED_delete.php?Event_id="+e;
                final JsonArrayRequest req1 = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject js=response.getJSONObject (0);
                            int de=js.getInt ( "rows" );
                            if(de<0)
                                Toast.makeText(getActivity().getApplicationContext(),"Event Deletion Failed"+response, Toast.LENGTH_LONG).show();
                            else {
                                Toast.makeText ( getActivity().getApplicationContext (), "Deleted Event Sucessfully" + r.getTitle (), Toast.LENGTH_LONG ).show ();
                            }
                        } catch (JSONException e1) {

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(),"Failed to delete Event", Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(req1);
                reg1.notifyDataSetChanged ();
                return true;
            }
        });
        return view;
    }
}
