package com.lanou3g.liwushuodemo.select;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.Bean.GiftDetilBean;
import com.lanou3g.liwushuodemo.Bean.SelectBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/12.
 */
public class SelectAdaper extends RecyclerView.Adapter<SelectAdaper.SelectViewHodler> {
    private Context context;
    private List<SelectBean.DataBean.ItemsBean> itemsBeens;
    private OnItemClickListener itemClickListener;
    private List<GiftDetilBean.DataBean.ItemsBean> giftDetilBean;
    private int flag=0;

    public void setGiftDetilBean(List<GiftDetilBean.DataBean.ItemsBean> giftDetilBean) {
        this.giftDetilBean = giftDetilBean;
        notifyDataSetChanged();
        Log.d("SelectAdaper", "收到了"+giftDetilBean);

    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public void setFlag(int flag) {
        this.flag = flag;
        Log.d("SelectAdaper", "flag:" + flag);
    }

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
    public void onBindViewHolder(final SelectViewHodler holder, final int position) {
        Log.d("SelectAdaper", "flag1:" + flag);
        if (flag==0){
        Picasso.with(context).load(itemsBeens.get(position).getData().getCover_image_url()).into(holder.productImg);
        holder.productName.setText(itemsBeens.get(position).getData().getName());
        holder.productPrice.setText(itemsBeens.get(position).getData().getPrice());
            Log.d("SelectAdaper", "flag2:" + flag);
        }else {
            Log.d("-------",giftDetilBean.get(position).getName());
            Picasso.with(context).load(giftDetilBean.get(position).getCover_image_url()).into(holder.productImg);
            holder.productName.setText(giftDetilBean.get(position).getName());
            holder.productPrice.setText(giftDetilBean.get(position).getPrice());
            Log.d("SelectAdaper", "flag3:" + flag);
        }



        //holder.messageSize.setText(itemsBeens.get(position).getData().getFavorites_count());
        if (itemClickListener!=null){
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
        if (flag==0) {
            return itemsBeens == null ? 0 : itemsBeens.size();
        }else {
            return giftDetilBean==null?0:giftDetilBean.size();
        }
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
