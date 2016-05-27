package com.lanou3g.liwushuodemo.category.gift;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.liwushuodemo.Base.BaseFragment;
import com.lanou3g.liwushuodemo.Bean.GiftBean;
import com.lanou3g.liwushuodemo.R;
import com.lanou3g.liwushuodemo.volley.VolleySingle;

import java.util.ArrayList;
import java.util.List;


/**
 * 礼物界面
 * Created by dllo on 16/5/12.
 */
public class GiftFragment extends BaseFragment {
    private ListView titleListView;
    private ListView contentListView;
    private TitleAdapter titleAdapter;
    private ContentAdapter contentAdapter;
    public int pos = 0;
    private List<GiftBean.DataBean.CategoriesBean> titleBean;


    @Override
    protected int initLayout() {

        return R.layout.fragment_gift;
    }

    @Override
    protected void initView() {
        titleListView = bindView(R.id.category_fragment_gift_title_lv);
        contentListView = bindView(R.id.category_fragment_gift_content_lv);
        titleAdapter = new TitleAdapter(context);
        titleListView.setAdapter(titleAdapter);
        contentAdapter = new ContentAdapter(context);
        contentListView.setAdapter(contentAdapter);

        titleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                TextView view1 = (TextView) parent.getChildAt(position).findViewById(R.id.tv);

                Log.d("测试", "" + position);
                titleListView.setSelection(position - 1);
//                view1.setTextColor(Color.RED);
                contentListView.setSelection(position);
            }
        });

        contentListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case 0:
                        //停止滚动
                        titleListView.setSelection(pos-1);
                        Log.d("停止滚动", "----");

//                        view.getPositionForView(contentListView);
                        break;
                    case 1:
                        //手指触摸
                        titleListView.setSelection(pos-1);
                        Log.d("手指触摸", "----");
                        break;
                    case 2:
                        //惯性滑动
                        titleListView.setSelection(pos - 1);
                        Log.d("惯性滑动", "----");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                pos = firstVisibleItem;
                Log.d("显示的item", "----" + firstVisibleItem);
                titleListView.setItemChecked(pos, true);
//                    titleListView.setSelection(pos);
//               TextView view2= (TextView) view.getChildAt(pos).findViewById(R.id.tv);
//                view2.setTextColor(Color.RED);
                Log.d("被点击", "-------------" + pos);

            }
        });


    }

    @Override
    protected void initData() {
        VolleySingle.getInstance().getQueue();
        VolleySingle.getInstance()._addRequest("http://api.liwushuo.com/v2/item_categories/tree", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, GiftBean.class, new Response.Listener<GiftBean>() {
            @Override
            public void onResponse(GiftBean response) {
                titleBean = new ArrayList<>();
                titleBean = response.getData().getCategories();
                titleAdapter.setTitleBean(titleBean);
                contentAdapter.setContentBean(titleBean);
            }
        },"giftData");
    }

}
