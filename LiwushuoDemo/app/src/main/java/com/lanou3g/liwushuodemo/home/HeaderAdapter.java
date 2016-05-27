package com.lanou3g.liwushuodemo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.liwushuodemo.Bean.BannerBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * 头布局的适配器
 * Created by dllo on 16/5/14.
 */
public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder> {
    private List<BannerBean.DataBean.SecondaryBannersBean> bannersBeens;
    private Context context;
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setBannersBeens(List<BannerBean.DataBean.SecondaryBannersBean> bannersBeens) {
        this.bannersBeens = bannersBeens;
        notifyDataSetChanged();
    }

    public HeaderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headView = LayoutInflater.from(context).inflate(R.layout.item_omnibus_header_image,null);
        HeaderViewHolder holder = new HeaderViewHolder(headView);
        return holder;
    }

    @Override
    public void onBindViewHolder(HeaderViewHolder holder, final int position) {
        Picasso.with(context).load(bannersBeens.get(position).getImage_url()).into(holder.imageView);
        if(itemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bannersBeens==null?0:bannersBeens.size();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_omnibus_fragment_header_iv);
        }
    }
}
