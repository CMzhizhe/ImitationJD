package com.example.imitationjd.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.imitationjd.fragment.BottomFragment;

import java.util.List;

public class FragmentAdapter extends FragmentPagerAdapter {
    List<BottomFragment> fragmentList;
    public FragmentAdapter(@NonNull FragmentManager fm,List<BottomFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public BottomFragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
