package com.lanou3g.liwushuodemo.category.gift;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.Bean.GiftBean;
import com.lanou3g.liwushuodemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/25.
 */
public class ContentAdapter extends BaseAdapter {
    private List<GiftBean.DataBean.CategoriesBean> beanList;
    private List<GiftBean.DataBean.CategoriesBean.SubcategoriesBean> contentBean;
    //    ContentImageAdapter adapter;
//    RelativeLayout rl;//带那条线的布局
    private ImageAdapter imageAdapter;

    public void setContentBean(List<GiftBean.DataBean.CategoriesBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    private Context context;

    public ContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;//定义一个viewholder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_gift_content, parent, false);
            //进行findviewbyid操作 并把操作玩的组件保存起来
            //初始化hodler,将holder内部的组件都完成findviewbyid操作
            holder = new ViewHolder(convertView);
            //把初始化好的ViewHolder放到converView里
            convertView.setTag(holder);

        } else {
            //证明convertView不是新的
            //它已经注入布局,并且有holder
            //把convertView里的holder取出来
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        //从数据集合里渠道对应的position位置的数据
        holder.tv.setText(beanList.get(position).getName());
        contentBean = new ArrayList<>();
        contentBean = beanList.get(position).getSubcategories();
       imageAdapter.setContentList(contentBean);
        return convertView;


    }

    //内部类用来保存item中的各个组件
    class ViewHolder {
        TextView tv;
        //        RecyclerView recyclerView;
        GridView gridView;


        //在这个ViewHolder的对象创建的时候对他内部保存的各个组件进行findviewbyid的操作
        //之后这个对象内部的各个组件就是初始化完成的
        //省略了不必要的findviewbyid操作
        public ViewHolder(View itemView) {

            gridView = (GridView) itemView.findViewById(R.id.category_fragment_gift_content_gridview);
//            recyclerView = (RecyclerView) itemView.findViewById(R.id.category_fragment_gift_content_select_rv);
            tv = (TextView) itemView.findViewById(R.id.gift_tv);
            imageAdapter = new ImageAdapter(context);
            gridView.setAdapter(imageAdapter);
//            rl = (RelativeLayout) itemView.findViewById(R.id.rv);
//            adapter = new ContentImageAdapter(context);
//            recyclerView.setLayoutManager(new GridLayoutManager(context,3));
//            recyclerView.setAdapter(adapter);


        }

    }
}
