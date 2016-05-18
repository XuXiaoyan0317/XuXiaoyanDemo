package com.lanou3g.liwushuodemo.home;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lanou3g.liwushuodemo.Base.BannerBean;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.CellBean;
import com.lanou3g.liwushuodemo.Bean.PlayerBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.volley.VolleySingle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.ActionBar.LayoutParams;

/**
 * 精选页
 * Created by dllo on 16/5/11.
 */
public class OmnibusFragment extends BaseFragment {
    //可横向滑动的组件的创建
    private RecyclerView headerRecyclerView;
    private HeaderAdapter headerAdapter;

    //轮播图
    private ViewPager viewPager;//放轮播图片的
    private ImagePlayAdapter playAdapter;//适配器
    private List<ImageView> dotViewList;//用于小圆点图片
    private List<PlayerBean.DataBean.BannersBean> imageBean;
    private List<BannerBean.DataBean.SecondaryBannersBean> bannersBeen;
    private List<CellBean.DataBean.ItemsBean> cellBean;
    private LinearLayout dotLayout;//存放小圆点的线性布局
    private int currentItem = 0;//当前页面
    boolean isAutoPlay = true;//是否自动轮播
    private ScheduledExecutorService scheduledExecutorService;

    //添加了两个头布局的recycleView
    private View playFlater;
    private View headerFlater;
    private RecyclerView recyclerView;
    private OmnibusAdapter omnibusAdapter;

    //接收改变轮播图页面的消息
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 100) {
                viewPager.setCurrentItem(currentItem);
            }
        }

    };

    //加载本页的布局 布局中只有一个recycleView
    // recycleView里面有三个布局:一个轮播图viewpager,一个头布局recycleView
    @Override
    protected int initLayout() {
        return R.layout.fragment_omnibus;
    }

    //初始化数据
    @Override
    protected void initView() {
        //最大的布局里面
        recyclerView = bindView(R.id.omnibus_fragment_rv);

        //轮播图的布局layout
        playFlater = LayoutInflater.from(context).inflate(R.layout.item_omnibus_palyer, null);

        //头布局的
        headerFlater = LayoutInflater.from(context).inflate(R.layout.item_omnibus_header, null);
        //头布局的recycleView初始化
        headerRecyclerView = (RecyclerView) headerFlater.findViewById(R.id.omnibus_fragment_header_rv);
        //头布局的适配器
        headerAdapter = new HeaderAdapter(context);
        //横向滑动
        LinearLayoutManager manager = new LinearLayoutManager(context);
        headerRecyclerView.setLayoutManager(manager);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //绑定适配器
        //最大的适配器
        omnibusAdapter = new OmnibusAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);



        viewPager = (ViewPager) playFlater.findViewById(R.id.omnibus_fragment_player_vp);
        //小圆点的线性布局初始化
        dotLayout = (LinearLayout) playFlater.findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        initDot();


    }

    public void initDot() {
        bannersBeen = new ArrayList<>();
        imageBean = new ArrayList<>();
        cellBean = new ArrayList<>();
        VolleySingle.getInstance().getQueue();
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/banners", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, PlayerBean.class, new Response.Listener<PlayerBean>() {
            @Override
            public void onResponse(PlayerBean response) {
                imageBean = response.getData().getBanners();
                initPlayer();
            }
        },"playerData");

        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, BannerBean.class, new Response.Listener<BannerBean>() {
            @Override
            public void onResponse(BannerBean response) {
                bannersBeen= response.getData().getSecondary_banners();
                headerRecyclerView.setAdapter(headerAdapter);
                headerAdapter.setBannersBeens(bannersBeen);


            }
        }, "bannerData");
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/channels/103/items?limit=20&ad=2&gender=2&offset=0&generation=1", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, CellBean.class, new Response.Listener<CellBean>() {
            @Override
            public void onResponse(CellBean response) {
               cellBean =  response.getData().getItems();
                omnibusAdapter.setCellBeans(cellBean);
                recyclerView.setAdapter(omnibusAdapter);
                omnibusAdapter.setPlayerView(playFlater);
                omnibusAdapter.setHeaderView(headerFlater);
            }
        },"cellData");

    }






    public void initPlayer(){

        //初始化适配器
        playAdapter = new ImagePlayAdapter(context);
        //绑定适配器
        viewPager.setAdapter(playAdapter);
        //将当前的viewpager设置为0
        viewPager.setCurrentItem(0);

        //向适配器添加图片数据
        playAdapter.setImageBean(imageBean);
        //监听viewpager的状态
        viewPager.addOnPageChangeListener(new MyPageChangeListener());
        if (isAutoPlay) {
            startPlay();
        }
        Log.d("快出来2", "%%%%%%%%");
        dotViewList = new ArrayList<>();
        //这是给刚打开界面初始化三个小圆点,以后就不用了,就在viewpager的滑动监听中添加小圆点
        for (int i = 0; i < imageBean.size(); i++) {
            //创建一个小圆点对象
            ImageView dotView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            if (i == 0) {
                //每次打开程序都显示的是第一张轮播图
                //所以当i=0时,显示的第一个小圆点是白色的
                dotView.setBackgroundResource(R.mipmap.btn_check_normal);
            } else {

                dotView.setBackgroundResource(R.mipmap.btn_check_normal_nightmode);
            }
            //向线性布局添加小圆点和他的位置信息
            dotLayout.addView(dotView, params);
            //向集合中添加小圆点
            dotViewList.add(dotView);
            //上面是动态添加了三个小圆点
        }
    }


    @Override
    protected void initData() {


    }

    // 开始轮播图切换
    private void startPlay() {
        //开启线程
        //因为需要延时显示图片,所以要开线程
        //每隔一段时间线程就发送一条空消息让适配器刷新页面
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //根据他的参数说明，第一个参数是执行的任务，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期；

        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, imageBean.size(), TimeUnit.SECONDS);
    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % imageBean.size();
                //发送空消息
                if (isAutoPlay = true) {
                    handler.sendEmptyMessage(100);
                }
            }
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */

    private class MyPageChangeListener implements OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    //当前为最后一张，此时从右向左滑，则切换到第一张

                    if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        viewPager.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            //给现在滑动到的页面的圆点设置为白色,其他为黑色
            currentItem = pos;
            for (int i = 0; i < dotViewList.size(); i++) {
                if (i == pos) {
                    (dotViewList.get(pos)).setBackgroundResource(R.mipmap.btn_check_normal);

                } else {
                    (dotViewList.get(i)).setBackgroundResource(R.mipmap.btn_check_normal_nightmode);
                }
            }
        }

    }

    @Override
    public void onDestroy() {
        VolleySingle.getInstance().removeRequest("playerData");
        VolleySingle.getInstance().removeRequest("bannerData");
        VolleySingle.getInstance().removeRequest("cellData");

        super.onDestroy();
    }
}


