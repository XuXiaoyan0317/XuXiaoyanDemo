package com.lanou3g.liwushuodemo.category.gift;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lanou3g.liwushuodemo.Bean.GiftBean;
import com.lanou3g.liwushuodemo.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/27.
 */
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private List<GiftBean.DataBean.CategoriesBean.SubcategoriesBean> contentList;

    public void setContentList(List<GiftBean.DataBean.CategoriesBean.SubcategoriesBean> contentList) {
        this.contentList = contentList;
    }

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler=null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_gift_img,null);
            hodler = new ViewHodler(convertView);
            convertView.setTag(hodler);
        }else {
            hodler = (ViewHodler) convertView.getTag();
        }
        Picasso.with(context).load(contentList.get(position).getIcon_url()).into(hodler.iv);
        return convertView;
    }

     class ViewHodler {
         ImageView iv;
        public ViewHodler(View itemView) {
            iv = (ImageView) itemView.findViewById(R.id.category_fragment_gift_content_select_iv);
        }
    }

}
