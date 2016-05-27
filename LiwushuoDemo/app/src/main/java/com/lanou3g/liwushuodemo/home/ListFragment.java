package com.lanou3g.liwushuodemo.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.ListBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;
import com.lanou3g.liwushuodemo.volley.VolleySingle;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 各频道页
 * Created by dllo on 16/5/10.
 */
public class  ListFragment extends BaseFragment implements OnItemClickListener {
    private RecyclerView recyclerView;
    private ListFragmemtAdapter adapter;
    //private ListBroadcast listBroadcast;
//    private ListBean listBean;
    private List<ListBean.DataBean.ItemsBean> itemBeans;

    @Override
    protected int initLayout() {
        EventBus.getDefault().register(this);
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {


        recyclerView = bindView(R.id.list_fragment_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ListFragmemtAdapter(context);//初始化适配器
        recyclerView.setAdapter(adapter);//绑定适配器

    }



    @Override
    protected void initData() {
        //动态注册广播
//        listBroadcast = new ListBroadcast();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("com.lanou3g.liwushuodemo.homecom.LISTFRAGMENT");
//        context.registerReceiver(listBroadcast,intentFilter);

        adapter.setItemsBeanList(itemBeans);
        Log.d("广播数据",""+itemBeans);

    }

//线程的类型
 @Subscribe(threadMode = ThreadMode.MainThread)
     public void getListBean(ListBean listBean){
         itemBeans = new ArrayList<>();
         itemBeans= listBean.getData().getItems();
         adapter.setItemsBeanList(itemBeans);
     adapter.setItemClickListener(this);

 }
    @Override
    public void onClick(int position) {
        Intent intent = new Intent(context,ListDetilActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
