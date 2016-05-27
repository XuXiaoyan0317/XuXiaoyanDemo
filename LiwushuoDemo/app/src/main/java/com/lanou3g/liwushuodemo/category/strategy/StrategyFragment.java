package com.lanou3g.liwushuodemo.category.strategy;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.StrategyBean;
import com.lanou3g.liwushuodemo.Bean.StrategyHeaderBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;
import com.lanou3g.liwushuodemo.home.ImageDetilActivity;
import com.lanou3g.liwushuodemo.volley.VolleySingle;
import java.util.ArrayList;
import java.util.List;

/**
 * 攻略界面
 * Created by dllo on 16/5/12.
 */
public class StrategyFragment extends BaseFragment implements OnItemClickListener{
    private RecyclerView recyclerView;
    private StrategyAdapter strategyAdapter;
    //这是头布局的
    private RecyclerView headerRecyclerView;
    private StrategyHeaderAdapter headerAdapter;


    //这是带圆圈的的数据
    private List<StrategyBean.DataBean.ChannelGroupsBean> groupsBeen;
    //这是头布局
    private List<StrategyHeaderBean.DataBean.CollectionsBean> headerBean;

    @Override
    protected int initLayout() {
        return R.layout.fragment_category_strategy;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.strategy_fragment_rv);
        strategyAdapter = new StrategyAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(strategyAdapter);
        //把头布局发送过去
        View header  = LayoutInflater.from(context).inflate(R.layout.item_category_strategy_header,null);
        strategyAdapter.setHeaderView(header);
        //头布局的recyclerView
        headerRecyclerView = (RecyclerView) header.findViewById(R.id.category_fragment_strategy_header_rv);
        headerAdapter= new StrategyHeaderAdapter(context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        headerRecyclerView.setLayoutManager(manager);
        headerRecyclerView.setAdapter(headerAdapter);
        headerAdapter.setItemClickListener(this);


    }

    @Override
    protected void initData() {
        VolleySingle.getInstance().getQueue();
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/channel_groups/all", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, StrategyBean.class, new Response.Listener<StrategyBean>() {
            @Override
            public void onResponse(StrategyBean response) {
                groupsBeen = new ArrayList<>();
                groupsBeen = response.getData().getChannel_groups();
                strategyAdapter.setGroupsBeens(groupsBeen);

            }
        },"strategyData");
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/collections?limit=10&offset=0", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, StrategyHeaderBean.class, new Response.Listener<StrategyHeaderBean>() {
            @Override
            public void onResponse(StrategyHeaderBean response) {
                headerBean = new ArrayList<>();
                headerBean=response.getData().getCollections();
                headerAdapter.setHeaderBeans(headerBean);
                Log.d("我的数据",""+headerBean);

            }
        });
    }


    @Override
    public void onClick(int position) {
        Intent intent = new Intent(context, ImageDetilActivity.class);
       int strategyId =  headerBean.get(position).getId();
        intent.putExtra("strategyId",strategyId);
        startActivity(intent);

    }

}
