package com.lanou3g.liwushuodemo.select;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Base.MyApplication;
import com.lanou3g.liwushuodemo.Bean.SelectBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;
import com.lanou3g.liwushuodemo.volley.VolleySingle;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class SelectFragment extends BaseFragment implements OnItemClickListener{
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
        recycleAdaper.setItemClickListener(this);

    }

    @Override
    protected void initData() {
        VolleySingle.getInstance().getQueue();
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/items?limit=20&offset=0&gender=2&generation=1",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                },SelectBean.class,new Response.Listener<SelectBean>(){
                    @Override
                    public void onResponse(SelectBean response) {
                        itemsBeens=new ArrayList<>();
                        itemsBeens=response.getData().getItems();
                        recycleAdaper.setItemsBeens(itemsBeens);

                    }
                },"selectData");

    }



    @Override
    public void onClick(int position) {
        Intent intent = new Intent(context,SelectDetilActivity.class);
       String selectPath =  itemsBeens.get(position).getData().getPurchase_url();
        intent.putExtra("selectPath",selectPath);
        context.startActivity(intent);

    }
    @Override
    public void onDestroy() {
        VolleySingle.getInstance().removeRequest("selectData");
        super.onDestroy();
    }



}
