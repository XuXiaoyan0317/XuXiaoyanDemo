package com.lanou3g.liwushuodemo.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.list.WearBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.volley.VolleySingle;

import java.util.List;

/**
 * 各频道页
 * Created by dllo on 16/5/10.
 */
public class ListFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private ListFragmemtAdapter adapter;
    private List<WearBean.DataBean.ItemsBean> itemsBeen;
    @Override
    protected int initLayout() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {
       recyclerView = bindView(R.id.list_fragment_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ListFragmemtAdapter(context);


    }

    @Override
    protected void initData() {
        VolleySingle.getInstance().getQueue();
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/channels/110/items?limit=20&offset=0&gender=2&generation=1", new
                Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, WearBean.class, new Response.Listener<WearBean>() {
            @Override
            public void onResponse(WearBean response) {
                itemsBeen=response.getData().getItems();
                recyclerView.setAdapter(adapter);
                adapter.setItemsBeanList(itemsBeen);

            }
        },"wearData");

    }
}
