package com.lanou3g.liwushuodemo.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.Bean.StrategyBean;
import com.lanou3g.liwushuodemo.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/24.
 */
public class StrategyContentAdapter extends RecyclerView.Adapter<StrategyContentAdapter.ImageViewHodler> {
    private Context context;
    private List<StrategyBean.DataBean.ChannelGroupsBean.ChannelsBean> imgBeans;

    public void setImgBeans(List<StrategyBean.DataBean.ChannelGroupsBean.ChannelsBean> imgBeans) {
        this.imgBeans = imgBeans;
        notifyDataSetChanged();
    }

    public StrategyContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ImageViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_strategy_img,null);
        ImageViewHodler imageHodler = new ImageViewHodler(view);
        return imageHodler;
    }

    @Override
    public void onBindViewHolder(ImageViewHodler holder, int position) {
        Picasso.with(context).load(imgBeans.get(position).getIcon_url()).into(holder.imageView);
        holder.titleText.setText(imgBeans.get(position).getName());
//       holder.imageView.setImageResource(R.mipmap.xxy03);

    }

    @Override
    public int getItemCount() {
        return imgBeans==null?0:imgBeans.size();
    }

    class ImageViewHodler extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView titleText;

        public ImageViewHodler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.category_fragment_strategy_content_iv);
            titleText = (TextView) itemView.findViewById(R.id.category_fragment_strategy_content_tv);
        }
    }
}
