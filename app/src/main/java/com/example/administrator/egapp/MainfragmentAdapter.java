package com.example.administrator.egapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2022/10/9.
 */

public class MainfragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    public MainfragmentAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragmentList=fragments;
        this.fragmentManager=fm;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
