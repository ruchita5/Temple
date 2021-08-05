package com.example.user.templettd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class about_us extends Fragment implements OnMapReadyCallback {
    View view;
    TextView t2;
    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.about_us, null);
        t2=(TextView)view.findViewById(R.id.t2);

        SupportMapFragment mapFragment = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map));
        //(SupportMapFragment)getFragmentManager ().findFragmentById (R.id.map);
        mapFragment.getMapAsync (this);

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(19.094705, 74.733890);
        mMap.addMarker (new MarkerOptions ().position (sydney).title ("Balaji Mandir"));
        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(sydney,zoomLevel));
    }
}
