package com.lanou3g.liwushuodemo.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.liwushuodemo.Bean.PlayerBean;
import com.lanou3g.liwushuodemo.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * 轮播图的适配器
 * Created by dllo on 16/5/11.
 */
public class ImagePlayAdapter extends PagerAdapter {
    private Context context;
    private List<PlayerBean.DataBean.BannersBean> imageBean;


    public void setImageBean(List<PlayerBean.DataBean.BannersBean> imageBean) {
        this.imageBean = imageBean;

        notifyDataSetChanged();
    }

    public ImagePlayAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return imageBean == null ? 0 : imageBean.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_ommibus_player_img, null);
        ImageView iv = (ImageView) v.findViewById(R.id.item_omnibus_fragment_player_iv);
        Picasso.with(context).load(imageBean.get(position).getImage_url()).into(iv);
        System.out.println("------------");
        container.addView(v);
        return v;


//        ImageView view = imageBean.get(position).getImage_url();
//        ViewParent vp =  view.getParent();
//        if(vp != null){
//            ViewGroup parent = (ViewGroup)vp;
//            parent.removeView(view);
        // }
        //上面这些语句必须加上，如果不加的话，就会产生则当用户滑到第四个的时候就会触发这个异常
        //原因是我们试图把一个有父组件的View添加到另一个组件。
        //container.addView(list.get(position));


    }
}
