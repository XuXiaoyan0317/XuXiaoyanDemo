package com.lanou3g.liwushuodemo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lanou3g.liwushuodemo.Bean.list.WearBean;
import com.lanou3g.liwushuodemo.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/18.
 */
public class ListFragmemtAdapter extends RecyclerView.Adapter<ListFragmemtAdapter.ListViewHolder> {
    private List<WearBean.DataBean.ItemsBean> itemsBeanList;
    private Context context;

    public void setItemsBeanList(List<WearBean.DataBean.ItemsBean> itemsBeanList) {
        this.itemsBeanList = itemsBeanList;
        Log.d("穿搭",itemsBeanList+"");
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
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Picasso.with(context).load(itemsBeanList.get(position).getCover_image_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemsBeanList==null?0:itemsBeanList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;

        public ListViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.list_fragment_content_iv);
        }
    }
}
