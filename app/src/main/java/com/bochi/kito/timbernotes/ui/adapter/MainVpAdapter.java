package com.bochi.kito.timbernotes.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainVpAdapter extends FragmentPagerAdapter {
    private final List<Fragment> pagerList;
    private final FragmentManager fragmentManager;

    public MainVpAdapter(List<Fragment> pagerList, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.pagerList = pagerList;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pagerList.get(position);
    }

    @Override
    public int getCount() {
        return pagerList.size();
    }
}
