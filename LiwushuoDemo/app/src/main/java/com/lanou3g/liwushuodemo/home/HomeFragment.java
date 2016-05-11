package com.lanou3g.liwushuodemo.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/10.
 */
public class HomeFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeAdapter homeAdapter;
    private List<String> titles;

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.home_fragment_tab);
        viewPager = bindView(R.id.home_fragment_viewpager);

    }

    @Override
    protected void initData() {

        titles = new ArrayList<>();
        titles.add("精选");
        titles.add("体育");
        titles.add("巴萨");
        titles.add("购物");
        titles.add("明星");
        titles.add("视频");
        titles.add("健康");
        titles.add("励志");
        titles.add("图文");
        titles.add("本地");
        titles.add("动漫");
        titles.add("搞笑");
        titles.add("精选");
        for(int i=0;i<titles.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OmnibusFragment());
        for(int i=0;i<titles.size()-1;i++){
            fragments.add(new ListFragment());
        }
        homeAdapter = new HomeAdapter(getChildFragmentManager(),fragments,titles);
        viewPager.setAdapter(homeAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }



    }
