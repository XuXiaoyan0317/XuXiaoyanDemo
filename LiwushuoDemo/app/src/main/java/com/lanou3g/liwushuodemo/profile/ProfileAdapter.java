package com.lanou3g.liwushuodemo.profile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/5/20.
 */
public class ProfileAdapter extends FragmentPagerAdapter {
    String[] title = {"礼物","攻略"};

    List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public ProfileAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments==null?0:fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
