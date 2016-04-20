package com.yuen.xiaoermei.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;

import com.yuen.xiaoermei.R;

import org.xutils.x;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import galleryfinal.GlideImageLoader;
import galleryfinal.GlidePauseOnScrollListener;

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
        //建议在application中配置
        //设置主题
       // ThemeConfig theme = ThemeConfig.ORANGE;
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.titlebar_bg))
                .setTitleBarTextColor(Color.WHITE)
                .setTitleBarIconColor(Color.WHITE)
                .setFabNornalColor(getResources().getColor(R.color.titlebar_bg))
                .setFabPressedColor(getResources().getColor(R.color.titlebar_bg))
                .setCheckNornalColor(Color.WHITE)
                .setIconCamera(R.drawable.ic_action_camera)
                .setCheckSelectedColor(getResources().getColor(R.color.titlebar_bg))
                .build();



      /*  ThemeConfig theme = new ThemeConfig.Builder()
                .build();*/
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(false)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, new GlideImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setPauseOnScrollListener(new GlidePauseOnScrollListener(false, true))
                .build();
        GalleryFinal.init(coreConfig);
    }
}
