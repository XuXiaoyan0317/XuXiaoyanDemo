package com.lanou3g.liwushuodemo.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.ChannelBean;
import com.lanou3g.liwushuodemo.Bean.ListBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.volley.VolleySingle;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 首页
 * Created by dllo on 16/5/10.
 */
public class HomeFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeAdapter homeAdapter;
    private List<ChannelBean.DataBean.ChannelsBean> titles;
    private List<ListBean.DataBean.ItemsBean> itemsBeanList;





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
                Log.d("数据0","-------");
                titles = response.getData().getChannels();
                Log.d("数据1","-------");
                for (int i = 0; i < titles.size(); i++) {

                    tabLayout.addTab(tabLayout.newTab().setText(titles.get(i).getName()));
                }
                Log.d("数据2","-------");
//                FragmentTransaction fragmentTransaction ;
//                fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.addToBackStack(fragment.getTag());
//                fragmentTransaction.replace(R.id.fragment_layout, fragment);
//                fragmentTransaction.commitAllowingStateLoss();
                List<Fragment> fragments = new ArrayList<>();
                fragments.add(new OmnibusFragment());
                for (int i = 0; i < titles.size() - 1; i++) {
                    fragments.add(new ListFragment());
                }
                viewPager.addOnPageChangeListener(new PageChangeLister());
                Log.d("数据3","-------");
                homeAdapter = new HomeAdapter(getChildFragmentManager(), fragments, titles);
                viewPager.setAdapter(homeAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        },"titleData");



    }

    class PageChangeLister implements ViewPager.OnPageChangeListener{


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            VolleySingle.getInstance().getQueue();
            VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/channels/" + titles.get(position).getId() + "/items?limit=20&offset=0&gender=2&generation=1", new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }, ListBean.class, new Response.Listener<ListBean>() {
                @Override
                public void onResponse(ListBean response) {
                    Log.d("数据",response+"");
                    itemsBeanList = response.getData().getItems();
                    Log.d("没法送的数据",itemsBeanList+"");
                    //Intent intent = new Intent("com.lanou3g.liwushuodemo.homecom.LISTFRAGMENT");
                    EventBus.getDefault().post(response);
//                    intent.putExtra("data",response);
//                    context.sendBroadcast(intent);
                   Log.d("发送完成","123456879");



                }
            },"listData");

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    @Override
    public void onDestroy() {
        VolleySingle.getInstance().removeRequest("titleData");
        VolleySingle.getInstance().removeRequest("listData");
        super.onDestroy();
    }
}
