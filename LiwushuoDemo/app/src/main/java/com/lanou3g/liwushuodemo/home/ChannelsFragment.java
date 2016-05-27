package com.lanou3g.liwushuodemo.home;

import android.os.Bundle;

import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.ChannelBean;

/**
 * Created by dllo on 16/5/19.
 */
public class ChannelsFragment extends BaseFragment{

    public static ChannelsFragment newInstance(int pos, ChannelBean bean) {

        Bundle args = new Bundle();

        ChannelsFragment fragment = new ChannelsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int initLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


    }
}
