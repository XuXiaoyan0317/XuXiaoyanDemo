package com.lanou3g.liwushuodemo.category.gift;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    private List<GiftBean.DataBean.CategoriesBean> beanList;



    public void setContentList(List<GiftBean.DataBean.CategoriesBean.SubcategoriesBean> contentList) {
        this.contentList = contentList;

        notifyDataSetChanged();
    }

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return contentList==null?0:contentList.size();
    }

    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHodler hodler=null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_category_gift_img,parent,false);
            hodler = new ViewHodler(convertView);
            convertView.setTag(hodler);
        }else {
            hodler = (ViewHodler) convertView.getTag();
        }

        Log.d("图片不符",contentList.get(position).getIcon_url());
        Picasso.with(context).load(contentList.get(position).getIcon_url()).into(hodler.iv);
        hodler.tv.setText(contentList.get(position).getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,GiftDetilActivity.class);
                intent.putExtra("giftId",contentList.get(position).getId());
                Log.d("ImageAdapter", "contentList.get(position).getId():" + contentList.get(position).getId());
                context.startActivity(intent);
            }
        });
          Log.d("ImageAdapter", "position:" + position);
        return convertView;
    }

     class ViewHodler {
         ImageView iv;
         TextView tv;
        public ViewHodler(View itemView) {
            iv = (ImageView) itemView.findViewById(R.id.category_fragment_gift_content_select_iv);
            tv = (TextView) itemView.findViewById(R.id.category_fragment_gift_content_select_tv);
        }
    }

}
