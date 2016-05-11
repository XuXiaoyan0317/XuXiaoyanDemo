package com.lanou3g.liwushuodemo.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.lanou3g.liwushuodemo.R;

/**
 * Created by dllo on 16/5/9.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initData();
    }

    public abstract int setLayout();//在这里加载布局

    protected abstract void initView();//在这里初始化组件

    protected abstract void initData();//在这里初始化数据

    //使组件实例化,不需要强转
    protected <T extends View >T bindView(int id){
        return (T) findViewById(id);
    }

}
