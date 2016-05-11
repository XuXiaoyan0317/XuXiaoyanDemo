package com.lanou3g.liwushuodemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.lanou3g.liwushuodemo.Base.BaseActivity;
import com.lanou3g.liwushuodemo.category.CategoryFragment;
import com.lanou3g.liwushuodemo.home.HomeFragment;
import com.lanou3g.liwushuodemo.profile.ProfileFragment;
import com.lanou3g.liwushuodemo.select.SelectFragment;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton homeBtn, selectBtn, categoryBtn, profileBtn;
    private FragmentManager manager;


    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        homeBtn = bindView(R.id.main_aty_home_btn);
        selectBtn = bindView(R.id.main_aty_select_btn);
        categoryBtn = bindView(R.id.main_aty_category_btn);
        profileBtn = bindView(R.id.main_aty_profile_btn);


    }

    @Override
    protected void initData() {
        homeBtn.setOnClickListener(this);
        selectBtn.setOnClickListener(this);
        categoryBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);
        FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction trans = manager.beginTransaction();
        //设置刚点进去就是HomeFragment页,并且按钮有颜色
        trans.replace(R.id.main_aty_replace, new HomeFragment());
        homeBtn.setChecked(true);
        trans.commit();
    }


    @Override
    public void onClick(View v) {
         manager = getSupportFragmentManager();
         FragmentTransaction trans = manager.beginTransaction();


        switch (v.getId()) {
            case R.id.main_aty_home_btn:
                trans.replace(R.id.main_aty_replace, new HomeFragment());
                break;
            case R.id.main_aty_select_btn:
                trans.replace(R.id.main_aty_replace, new SelectFragment());
                break;
            case R.id.main_aty_category_btn:
                trans.replace(R.id.main_aty_replace, new CategoryFragment());
                break;
            case R.id.main_aty_profile_btn:
                trans.replace(R.id.main_aty_replace, new ProfileFragment());
                break;
            default:
                break;
        }
        trans.commit();
    }
}
