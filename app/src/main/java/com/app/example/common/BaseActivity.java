package com.app.example.common;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.example.utils.StatusBarUtils;

public class BaseActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        StatusBarUtils.setStatuBarAndBottomBarTranslucent(this);
        super.onCreate(savedInstanceState);
    }

    public Context getContext() {
        return mContext;
    } //获取上下文

}
