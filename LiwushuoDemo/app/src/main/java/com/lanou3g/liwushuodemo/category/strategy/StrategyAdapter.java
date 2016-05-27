package com.lanou3g.liwushuodemo.category.strategy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanou3g.liwushuodemo.Bean.StrategyBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;
import com.lanou3g.liwushuodemo.home.ImageDetilActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/24.
 */
public class StrategyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemClickListener {
    private Context context;
    public static final int HEADER_TYPE = 0;
    public static final int CONTENT_TYPE = 1;
    private View headerView;
    private StrategyContentAdapter adapter;
    private List<StrategyBean.DataBean.ChannelGroupsBean> groupsBeens;
    private List<StrategyBean.DataBean.ChannelGroupsBean.ChannelsBean> imgBean;


    public void setGroupsBeens(List<StrategyBean.DataBean.ChannelGroupsBean> groupsBeens) {
        this.groupsBeens = groupsBeens;
        notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
    }

    public StrategyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            HeaderViewHolder headerHolder = new HeaderViewHolder(headerView);
            return headerHolder;
        } else if (viewType == CONTENT_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_category_strategy, null);
            ContentViewHodler contentHolder = new ContentViewHodler(view);
            adapter = new StrategyContentAdapter(context);
            adapter.setItemClickListener(this);
            return contentHolder;
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ContentViewHodler) {
            ((ContentViewHodler) holder).textView.setText(groupsBeens.get(position - 1).getName());
            ((ContentViewHodler) holder).recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
            ((ContentViewHodler) holder).recyclerView.setAdapter(adapter);
            imgBean = new ArrayList<>();
            imgBean = groupsBeens.get(position - 1).getChannels();
            adapter.setImgBeans(imgBean);
        }


    }

    @Override
    public int getItemCount() {
        return groupsBeens == null ? 0 : groupsBeens.size() + 1;
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(context, ImageDetilActivity.class);
        intent.putExtra("groundId",adapter.getId());
        context.startActivity(intent);

    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ContentViewHodler extends RecyclerView.ViewHolder {
        private TextView textView;
        private RecyclerView recyclerView;

        public ContentViewHodler(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.category_fragment_content_img_rv);
            textView = (TextView) itemView.findViewById(R.id.category_fragment_content_title);
        }
    }
}
