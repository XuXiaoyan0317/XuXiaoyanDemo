package com.lanou3g.liwushuodemo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.liwushuodemo.Bean.ImagDetilBean;
import com.lanou3g.liwushuodemo.Bean.ListBean;
import com.lanou3g.liwushuodemo.Bean.SelectContentBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/18.
 */
public class ListFragmemtAdapter extends RecyclerView.Adapter<ListFragmemtAdapter.ListViewHolder> {
    private List<ListBean.DataBean.ItemsBean> itemsBeanList;
    private Context context;
    private OnItemClickListener itemClickListener;
    private List<ImagDetilBean.DataBean.PostsBean> detilBean;
    int flag= 0;
    private List<SelectContentBean.DataBean.ItemsBean> strategyBean;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        notifyDataSetChanged();
    }

    public void setDetilBean(List<ImagDetilBean.DataBean.PostsBean> detilBean) {
        this.detilBean = detilBean;
        notifyDataSetChanged();
        Log.d("哈哈哈",detilBean+"");
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setItemsBeanList(List<ListBean.DataBean.ItemsBean> itemsBeanList) {
        this.itemsBeanList = itemsBeanList;
        Log.d("穿搭",itemsBeanList+"");
        notifyDataSetChanged();
    }

    public void setStrategyBean(List<SelectContentBean.DataBean.ItemsBean> strategyBean) {
        this.strategyBean = strategyBean;
        notifyDataSetChanged();
    }

    public ListFragmemtAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        ListViewHolder viewHolder = new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {
        if (flag==0) {
            Picasso.with(context).load(itemsBeanList.get(position).getCover_image_url()).into(holder.imageView);
        }else if (flag==1){
            Log.d("测试222","");
            Picasso.with(context).load(detilBean.get(position).getCover_image_url()).into(holder.imageView);
        }else {
            Picasso.with(context).load(strategyBean.get(position).getCover_image_url()).into(holder.imageView);
        }
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
        if (flag==0){
        return itemsBeanList==null?0:itemsBeanList.size();
        }else if (flag==1){
            return detilBean==null?0:detilBean.size();
        }else {
            return strategyBean==null?0:strategyBean.size();
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public ListViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.list_fragment_content_iv);
        }
    }
}
