package com.lanou3g.liwushuodemo.profile;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class ProfileFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProfileAdapter adapter;
    List<Fragment> fragments;
    @Override
    protected int initLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.profile_fragment_tab);
        viewPager = bindView(R.id.profile_fragment_vp);
        adapter = new ProfileAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new ProfileGiftFragment());
        fragments.add(new ProfileStrategyFragment());
        adapter.setFragments(fragments);

    }
}
