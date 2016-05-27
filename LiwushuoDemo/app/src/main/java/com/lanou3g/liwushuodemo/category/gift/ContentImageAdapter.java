package com.lanou3g.liwushuodemo.category.gift;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.liwushuodemo.Bean.GiftBean;
import com.lanou3g.liwushuodemo.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/26.
 */
public class ContentImageAdapter extends RecyclerView.Adapter<ContentImageAdapter.ImageViewHolder>{
    private Context context;
    private List<GiftBean.DataBean.CategoriesBean.SubcategoriesBean> contentList;

    public void setContentList(List<GiftBean.DataBean.CategoriesBean.SubcategoriesBean> contentList) {
        this.contentList = contentList;
        notifyDataSetChanged();
    }

    public ContentImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_gift_img,null);
        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Picasso.with(context).load(contentList.get(position).getIcon_url()).into(holder.iv);

    }

    @Override
    public int getItemCount() {
        return contentList==null?0:contentList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public ImageViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.category_fragment_gift_content_select_iv);
        }
    }
}
