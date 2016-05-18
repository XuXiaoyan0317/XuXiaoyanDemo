package com.lanou3g.liwushuodemo.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.ChannelBean;
import com.lanou3g.liwushuodemo.Bean.list.HaiTaoBean;
import com.lanou3g.liwushuodemo.Bean.list.WearBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.volley.RequestGson;
import com.lanou3g.liwushuodemo.volley.VolleySingle;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * Created by dllo on 16/5/10.
 */
public class HomeFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeAdapter homeAdapter;
    private List<ChannelBean.DataBean.ChannelsBean> titles;
    private String[] url = {"http://api.liwushuo.com/v2/channels/110/items? limit=20&offset=0&gender=2&generation=1",
    "http://api.liwushuo.com/v2/channels/129/items? limit=20&offset=0&gender=2&generation=1"};



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
        VolleySingle.getInstance().getQueue();
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, ChannelBean.class, new Response.Listener<ChannelBean>() {
            @Override
            public void onResponse(ChannelBean response) {
                titles = response.getData().getChannels();
                for (int i = 0; i < titles.size(); i++) {
                    tabLayout.addTab(tabLayout.newTab().setText(titles.get(i).getName()));
                }
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(new OmnibusFragment());
                for (int i = 0; i < titles.size() - 1; i++) {
                    fragments.add(new ListFragment());
                }
                homeAdapter = new HomeAdapter(getChildFragmentManager(), fragments, titles);
                viewPager.setAdapter(homeAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        },"titleData");

        viewPager.addOnPageChangeListener(new PageChangeLister());

    }
    class PageChangeLister implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void onDestroy() {
        VolleySingle.getInstance().removeRequest("titleData");
        super.onDestroy();
    }
}
