package com.yuen.xiaoermei.utils;

import android.app.ActivityManager;
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
import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2016/3/22.
 */
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化融云
         */
        RongIM.init(this);
        /**
         *
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);

        }








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
    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }
}
