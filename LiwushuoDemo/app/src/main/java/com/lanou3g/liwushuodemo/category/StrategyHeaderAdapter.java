package com.lanou3g.liwushuodemo.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.liwushuodemo.Bean.StrategyHeaderBean;
import com.lanou3g.liwushuodemo.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/24.
 */
public class StrategyHeaderAdapter extends RecyclerView.Adapter<StrategyHeaderAdapter.HeaderViewHodler> {
    private Context context;
    private List<StrategyHeaderBean.DataBean.CollectionsBean> headerBeans;


    public void setHeaderBeans(List<StrategyHeaderBean.DataBean.CollectionsBean> headerBeans) {
        this.headerBeans = headerBeans;
        notifyDataSetChanged();
    }

    public StrategyHeaderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public HeaderViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_strategy_header_imag,null);
        HeaderViewHodler headerHodler = new HeaderViewHodler(view);
        return headerHodler;
    }

    @Override
    public void onBindViewHolder(HeaderViewHodler holder, int position) {
        Picasso.with(context).load(headerBeans.get(position).getBanner_image_url()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return headerBeans==null?0:headerBeans.size();
    }


    class HeaderViewHodler extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public HeaderViewHodler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.category_fragment_strategy_header_iv);
        }
    }
}
