package com.lanou3g.liwushuodemo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.R;

/**
 * Created by dllo on 16/5/13.
 */
public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    public static final int PLAYER_TYPE = 0;
    public static final int HEADER_TYPE = 1;
    public static final int CONTENT_TYPE = 2;
    public View playerView;

    public void setPlayerView(View playerView) {
        this.playerView = playerView;

}

    public GalleryAdapter(Context context) {
        this.context = context;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headerView = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_omnibus, parent, false);
        if (viewType == PLAYER_TYPE) {
            PlayerViewHolder playerHolder = new PlayerViewHolder(playerView);
            return playerHolder;
        } else if (viewType == HEADER_TYPE) {
            HeaderViewHolder headerHolder = new HeaderViewHolder(headerView);
            return headerHolder;
        } else if (viewType == CONTENT_TYPE) {
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
        } else if (position==1){
            return HEADER_TYPE;
        }else {
            Log.d("怎么是你","-----");
            return CONTENT_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if (holder instanceof PlayerViewHolder) {

       }else if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).tv.setText("hahahahaha");
        } else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).iv.setImageResource(R.mipmap.xxy03);
        }

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {

        public PlayerViewHolder(View itemView) {
            super(itemView);

        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.omnibus_fragment_gallery_tv);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public ContentViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.omnibus_fragment_gallery_iv);

        }
    }

}
