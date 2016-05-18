package com.lanou3g.liwushuodemo.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lanou3g.liwushuodemo.Bean.ChannelBean;

import java.util.List;

/**
 * 首页适配器
 * Created by dllo on 16/5/10.
 */
public class HomeAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private List<ChannelBean.DataBean.ChannelsBean> mTitles;

    public HomeAdapter(FragmentManager fm, List<Fragment> fragments, List<ChannelBean.DataBean.ChannelsBean> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments==null?0:mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getName();
    }
}
