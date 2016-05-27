package com.lanou3g.liwushuodemo.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.liwushuodemo.Base.BaseActivity;
import com.lanou3g.liwushuodemo.Bean.CellBean;
import com.lanou3g.liwushuodemo.Bean.ImagDetilBean;
import com.lanou3g.liwushuodemo.Bean.ListBean;
import com.lanou3g.liwushuodemo.Bean.PlayerBean;
import com.lanou3g.liwushuodemo.Bean.SelectContentBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;
import com.lanou3g.liwushuodemo.volley.VolleySingle;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by dllo on 16/5/21.
 */
public class ImageDetilActivity extends BaseActivity implements OnItemClickListener{

    private RecyclerView recyclerView;
    private ListFragmemtAdapter adapter;
    private List<ImagDetilBean.DataBean.PostsBean> detilBean;
    private List<SelectContentBean.DataBean.ItemsBean> strategyBean;




    @Override
    public int setLayout() {
        return R.layout.activity_image_detil;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.detil_recyclerview);
        adapter = new ListFragmemtAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setItemClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int target = intent.getIntExtra("target",0);
        int strategyId = intent.getIntExtra("strategyId",0);
        int groundId = intent.getIntExtra("groundId",0);
        VolleySingle.getInstance().getQueue();

        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/collections/"+target+"/posts?gender=1&generation=2&limit=20&offset=0", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("测试","------");
            }
        }, ImagDetilBean.class, new Response.Listener<ImagDetilBean>() {
            @Override
            public void onResponse(ImagDetilBean response) {
                detilBean = new ArrayList<>();
                detilBean= response.getData().getPosts();
                Log.d("啦啦啦", response.getData().getPosts()+"");
                adapter.setFlag(1);
                adapter.setDetilBean(detilBean);

            }});
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/collections/"+strategyId+"/posts?gender=1&generation=2&limit=20&offset=0", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("测试","------");
            }
        }, ImagDetilBean.class, new Response.Listener<ImagDetilBean>() {
            @Override
            public void onResponse(ImagDetilBean response) {
                detilBean = new ArrayList<>();
                detilBean= response.getData().getPosts();
                Log.d("啦啦啦", response.getData().getPosts()+"");
                adapter.setFlag(1);
                adapter.setDetilBean(detilBean);

            }});
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/channels/" + groundId + "/items?limit=20&offset=0", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, SelectContentBean.class, new Response.Listener<SelectContentBean>() {
            @Override
            public void onResponse(SelectContentBean response) {
                adapter.setFlag(2);
                strategyBean = response.getData().getItems();
                adapter.setStrategyBean(strategyBean);
            }
        });



    }


    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this,ListDetilActivity.class);
        String imagePath= detilBean.get(position).getUrl();
        intent.putExtra("imagePath",imagePath);
        startActivity(intent);
    }
}
