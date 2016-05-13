package com.lanou3g.liwushuodemo.category;

import android.graphics.Color;
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
public class CategoryFragment extends BaseFragment {
    private List<Fragment> fragments;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CategoryAdapter adapter;

    @Override
    protected int initLayout() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.category_fragment_tab);
        viewPager = bindView(R.id.category_fragment_viewpager);
        adapter = new CategoryAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.YELLOW,Color.WHITE);

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new GiftFragment());
        fragments.add(new StrategyFragment());
        adapter.setFragments(fragments);

    }
}
