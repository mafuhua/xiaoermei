package com.yuen.xiaoermei.utils;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by Administrator on 2016/3/22.
 */
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        context = this;

        x.Ext.init(this);
        // 设置是否输出debug
        x.Ext.setDebug(true);

    }
}
