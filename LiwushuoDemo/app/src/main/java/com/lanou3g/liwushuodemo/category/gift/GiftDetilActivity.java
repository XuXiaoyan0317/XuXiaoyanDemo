package com.lanou3g.liwushuodemo.category.gift;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.liwushuodemo.Base.BaseActivity;
import com.lanou3g.liwushuodemo.Bean.GiftDetilBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.clickinterface.OnItemClickListener;
import com.lanou3g.liwushuodemo.select.SelectAdaper;
import com.lanou3g.liwushuodemo.select.SelectDetilActivity;
import com.lanou3g.liwushuodemo.volley.VolleySingle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/28.
 */
public class GiftDetilActivity extends BaseActivity implements OnItemClickListener {
    public List<GiftDetilBean.DataBean.ItemsBean> giftDetilBean;
    public RecyclerView recyclerView;
    public SelectAdaper selectAdaper;

    @Override
    public int setLayout() {
        return R.layout.activity_gift_detil;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.category_fragment_gift_detil_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        selectAdaper = new SelectAdaper(this);
        selectAdaper.setFlag(1);
        recyclerView.setAdapter(selectAdaper);
        selectAdaper.setItemClickListener(this);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("giftId", 0);
        VolleySingle.getInstance().getQueue();
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/item_subcategories/" + id + "/items?limit=20&offset=20", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, GiftDetilBean.class, new Response.Listener<GiftDetilBean>() {
            @Override
            public void onResponse(GiftDetilBean response) {
                giftDetilBean = new ArrayList<>();
                giftDetilBean = response.getData().getItems();
                selectAdaper.setGiftDetilBean(giftDetilBean);

                Log.d("hhhhhhhhhh", "giftDetilBean:" + giftDetilBean);
            }
        });
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, SelectDetilActivity.class);
        intent.putExtra("getPurchasePath", giftDetilBean.get(position).getPurchase_url());
        startActivity(intent);
    }
}
