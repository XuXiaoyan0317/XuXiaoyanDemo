package com.lanou3g.liwushuodemo.home;


import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.ActionBar.LayoutParams;

/**
 * Created by dllo on 16/5/11.
 */
public class OmnibusFragment extends BaseFragment {
    private ViewPager viewPager;//放轮播图片的
    private ImagePlayAdapter playAdapter;//适配器
    //用于小圆点图片
    private List<ImageView> dotViewList;
    //用于存放轮播效果图片
    private List<ImageView> list;
    //加载其他布局
    private LayoutInflater inflater;
    //存放小圆点的线性布局
    private LinearLayout dotLayout;


    private int currentItem = 0;//当前页面
    boolean isAutoPlay = true;//是否自动轮播

    private ScheduledExecutorService scheduledExecutorService;


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


    @Override
    protected int initLayout() {
        return R.layout.fragment_omnibus;
    }

    @Override
    protected void initView() {
        inflater = LayoutInflater.from(mContext);
        viewPager = bindView(R.id.home_omnibus_fragment_vp);
        //小圆点的线性布局初始化
        dotLayout = bindView(R.id.dotLayout);
        dotLayout.removeAllViews();
        initDot();

        if (isAutoPlay) {
            startPlay();
        }

    }

    public void initDot() {
        //放小圆点的集合
        dotViewList = new ArrayList<>();
        //这是给刚打开界面初始化三个小圆点,以后就不用了,就在viewpager的滑动监听中添加小圆点
        for (int i = 0; i < 3; i++) {
            //创建一个小圆点对象
            ImageView dotView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LayoutParams(
                    LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            params.height = 40;//设置小圆点的大小
            params.width = 40;

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
        //添加图片的布局
        ImageView img1 = (ImageView) inflater.inflate(R.layout.item_scroll_view, null);
        ImageView img2 = (ImageView) inflater.inflate(R.layout.item_scroll_view, null);
        ImageView img3 = (ImageView) inflater.inflate(R.layout.item_scroll_view, null);
        //设置图片内容
        img1.setBackgroundResource(R.mipmap.xxy03);
        img2.setBackgroundResource(R.mipmap.xxy15);
        img3.setBackgroundResource(R.mipmap.xxy10);
        //向图片集合添加图片
        list = new ArrayList<>();
        list.add(img1);
        list.add(img2);
        list.add(img3);
        //初始化适配器
        playAdapter = new ImagePlayAdapter(getContext());
        //绑定适配器
        viewPager.setAdapter(playAdapter);
        //将当前的viewpager设置为0
        viewPager.setCurrentItem(0);
        //向适配器添加图片数据
        playAdapter.setList(list);
        //监听viewpager的状态
        viewPager.addOnPageChangeListener(new MyPageChangeListener());

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
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 3, TimeUnit.SECONDS);
    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem + 1) % list.size();
                //发送空消息
                handler.sendEmptyMessage(100);
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
                    // 当前为最后一张，此时从右向左滑，则切换到第一张

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


}


