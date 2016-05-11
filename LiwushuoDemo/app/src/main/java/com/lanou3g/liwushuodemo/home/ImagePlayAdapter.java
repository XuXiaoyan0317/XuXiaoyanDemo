package com.lanou3g.liwushuodemo.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import java.util.List;

/**
 * Created by dllo on 16/5/11.
 */
public class ImagePlayAdapter extends PagerAdapter {
    private List<ImageView> list ;
    private Context context;

    public ImagePlayAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<ImageView> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = list.get(position) ;
        ViewParent vp =  view.getParent();
        if(vp != null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
        }
        //上面这些语句必须加上，如果不加的话，就会产生则当用户滑到第四个的时候就会触发这个异常
        //原因是我们试图把一个有父组件的View添加到另一个组件。
        container.addView(list.get(position));

        System.out.println("------------");
        return list.get(position);

    }
}
