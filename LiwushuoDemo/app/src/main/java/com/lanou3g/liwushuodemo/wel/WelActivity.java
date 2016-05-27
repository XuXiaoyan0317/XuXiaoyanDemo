package com.lanou3g.liwushuodemo.wel;

import android.content.Intent;

import com.lanou3g.liwushuodemo.Base.BaseActivity;
import com.lanou3g.liwushuodemo.MainActivity;
import com.lanou3g.liwushuodemo.R;

/**
 * Created by dllo on 16/5/9.
 */
public class WelActivity extends BaseActivity {

    @Override
    public int setLayout() {
        return R.layout.activity_wel;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Intent intent = new Intent(WelActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
