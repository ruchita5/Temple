package com.example.user.templettd;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class temple_list extends FragmentStatePagerAdapter {
    int count;
    String[] title={"Temple","Events","Gallery"};

    public temple_list(FragmentManager fm,int count) {
        super(fm);
        this.count=count;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                tab_temple tt=new tab_temple();
                return tt;
            case 1:
                tab_events te=new tab_events();
                return te;
            case 2:
                tab_gallery tg=new tab_gallery();
                return tg;

        }
        return null;
    }

    @Override
    public int getCount() {
        return count;
    }


}
