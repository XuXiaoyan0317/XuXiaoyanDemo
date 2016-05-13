package com.lanou3g.liwushuodemo.select;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.SelectBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.volley.RequestGson;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class SelectFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private SelectAdaper recycleAdaper;
    private List<SelectBean.DataBean.ItemsBean> itemsBeens;
    @Override
    protected int initLayout() {
        return R.layout.fragment_select;

    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.select_fragment_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recycleAdaper = new SelectAdaper(getContext());
        recyclerView.setAdapter(recycleAdaper);

    }

    @Override
    protected void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        RequestGson<SelectBean> requestGson = new RequestGson<>(Request.Method.GET,
                "http://api.liwushuo.com/v2/items?limit=20&offset=0&gender=2&generation=1",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                },SelectBean.class,new Response.Listener<SelectBean>(){
            @Override
            public void onResponse(SelectBean response) {
               itemsBeens=new ArrayList<>();
                itemsBeens=response.getData().getItems();
                Log.d("daklj","dkasl"+itemsBeens);
                recycleAdaper.setItemsBeens(itemsBeens);

            }
        });

        requestQueue.add(requestGson);
        Log.d("hhhhh","dkasl"+itemsBeens);
    }
}
