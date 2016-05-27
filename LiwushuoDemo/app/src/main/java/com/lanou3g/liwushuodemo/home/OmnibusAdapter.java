package com.lanou3g.liwushuodemo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.Bean.CellBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * 精选页适配器
 * Created by dllo on 16/5/13.
 */
public class OmnibusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public static final int PLAYER_TYPE = 0;
    public static final int HEADER_TYPE = 1;
    public static final int CONTENT_TYPE = 2;
    public View playerView;//轮播图的布局
    public View headerView;//横向滑动的布局
    private List<CellBean.DataBean.ItemsBean> cellBeans;
    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setCellBeans(List<CellBean.DataBean.ItemsBean> cellBeans) {
        this.cellBeans = cellBeans;
        Log.d("列表的现象"," "+cellBeans);
        notifyDataSetChanged();
    }

    public void setPlayerView(View playerView) {
        this.playerView = playerView;

    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;

    }

    public OmnibusAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_omnibus, parent, false);
        if (viewType == PLAYER_TYPE) {
            PlayerViewHolder playerHolder = new PlayerViewHolder(playerView);

            return playerHolder;
        } else if (viewType == HEADER_TYPE) {
            HeaderViewHolder headerHolder = new HeaderViewHolder(headerView);
            Log.d("头布局", "-----" + headerView);
            return headerHolder;
        } else if (viewType == CONTENT_TYPE) {
            Log.d("测试1", "-----" );
            ContentViewHolder contentHolder = new ContentViewHolder(contentView);
            return contentHolder;
        } else {
            return null;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return PLAYER_TYPE;
        } else if (position == 1) {
            return HEADER_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof PlayerViewHolder) {

        } else if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ContentViewHolder) {
            Log.d("测试2", "-----" );
            Picasso.with(context).load(cellBeans.get(position-2).getCover_image_url()).into(((ContentViewHolder) holder).iv);
            ((ContentViewHolder) holder).tv.setText(cellBeans.get(position-2).getTitle());

            Log.d("测试3", "-----" );
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
        return cellBeans==null?0:cellBeans.size()+2;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {

        public PlayerViewHolder(View itemView) {
            super(itemView);

        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView  tv;

        public ContentViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.omnibus_fragment_content_iv);
            tv = (TextView) itemView.findViewById(R.id.omnibus_fragment_content_tv);

        }
    }

}
