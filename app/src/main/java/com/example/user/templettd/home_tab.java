package com.example.user.templettd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class home_tab extends Fragment {
    View view;
    TabLayout tab1;
    ViewPager vp1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home_tab,null);
        tab1=(TabLayout)view.findViewById(R.id.tab_temple);
        vp1=(ViewPager)view.findViewById(R.id.view_temple);
        temple_list adapter=new temple_list(getActivity().getSupportFragmentManager(),3);
        vp1.setAdapter(adapter);
        tab1.setupWithViewPager(vp1);
        tab1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp1.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

}
