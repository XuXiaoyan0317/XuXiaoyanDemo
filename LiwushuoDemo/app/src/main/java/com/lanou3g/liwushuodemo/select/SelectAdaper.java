package com.lanou3g.liwushuodemo.select;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.Bean.SelectBean;
import com.lanou3g.liwushuodemo.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/12.
 */
public class SelectAdaper extends RecyclerView.Adapter<SelectAdaper.SelectViewHodler> {
    private Context context;
    List<SelectBean.DataBean.ItemsBean> itemsBeens;

    public SelectAdaper(Context context) {
        this.context = context;
    }

    public void setItemsBeens(List<SelectBean.DataBean.ItemsBean> itemsBeens) {
        this.itemsBeens = itemsBeens;
        Log.d("TAG", "setItemsBeens: " + itemsBeens);
        notifyDataSetChanged();
    }

    @Override
    public SelectViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select, parent, false);
        SelectViewHodler hodler = new SelectViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(final SelectViewHodler holder, int position) {
        Picasso.with(context).load(itemsBeens.get(position).getData().getCover_image_url()).into(holder.productImg);
        holder.productName.setText(itemsBeens.get(position).getData().getName());
        holder.productPrice.setText(itemsBeens.get(position).getData().getPrice());
        //holder.messageSize.setText(itemsBeens.get(position).getData().getFavorites_count());

    }

    @Override
    public int getItemCount() {
        return itemsBeens == null ? 0 : itemsBeens.size();
    }

    class SelectViewHodler extends RecyclerView.ViewHolder {
        private ImageView productImg;
        private TextView productName;
        private TextView productPrice;
        private TextView messageSize;

        public SelectViewHodler(View itemView) {
            super(itemView);
            productImg = (ImageView) itemView.findViewById(R.id.select_fragment_product_img);
            productName = (TextView) itemView.findViewById(R.id.select_fragment_product_name);
            productPrice = (TextView) itemView.findViewById(R.id.select_fragment_product_price);
            messageSize = (TextView) itemView.findViewById(R.id.select_fragment_product_size);
        }
    }
}
