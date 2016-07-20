package com.yuen.xiaoermei.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.fragment.HomeFragment;
import com.yuen.xiaoermei.fragment.MenuFragment;
import com.yuen.xiaoermei.lisetner.MyReceiveMessageListener;
import com.yuen.xiaoermei.lisetner.MyReceivePushMessageListener;
import com.yuen.xiaoermei.utils.MyApplication;
import com.yuen.xiaoermei.utils.SysExitUtil;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * 主页面
 */
public class MainActivity extends SlidingFragmentActivity  {
    public static String username;
    public static String userid;
    public static String shop_imgs;
    public static SlidingMenu slidingMenu;
    /**
     * Notification管理
     */
    public static NotificationManager mNotificationManager;
    /**
     * Notification构造器
     */
    public static NotificationCompat.Builder mBuilder;
    /**
     * Notification的ID
     */
    public static int notifyId = 100;
    public SharedPreferences sharedPreferences;

    /**
     * 初始化通知栏
     */
    public static void initNotify() {

        mNotificationManager = (NotificationManager) MyApplication.context.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(MyApplication.context);
        mBuilder.setContentTitle("小而美")
                .setContentText("您有一条新消息")
                .setAutoCancel(true)
//				.setNumber(number)//显示数量
                .setTicker("小而美:您有一条新消息")//通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
//				.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                        //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(R.drawable.logo2x);
        Intent resultIntent = new Intent(MyApplication.context, ConvertalkActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.context, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }
    public static String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //2.设置内容、菜单
        setContentView(R.layout.main_content);//内容界面
        setBehindContentView(R.layout.menu_content);//菜单界面
        //3.设置SlidingMenu的属性
        slidingMenu = getSlidingMenu();
        slidingMenu.toggle();

//        this.toggle();//真是调用slidingMenu.toggle();
        //分割线
        slidingMenu.setShadowDrawable(R.drawable.shadow);
//        slidingMenu.setShadowWidthRes(200);//错误
        //  slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        //左边的菜单
        slidingMenu.setMode(SlidingMenu.LEFT);
        //打开方式：全屏
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //菜单的偏移量
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        //替换菜单和内容Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment(), "HOME").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu, new MenuFragment(), "MENU").commit();

        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        userid = sharedPreferences.getString("id", "");
        shop_imgs = sharedPreferences.getString("show_img", "");
        token = sharedPreferences.getString("token", "");
        SysExitUtil.activityList.add(this);



        /**
         * 设置接收 push 消息的监听器。
         */
        RongIM.setOnReceivePushMessageListener(new MyReceivePushMessageListener());
        /**
         *  设置接收消息的监听器。
         */
        RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener());
        /**
         * IMKit SDK调用第二步
         *
         * 建立与服务器的连接
         *
         */
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
                Log.e("MainActivity", "——Connect Token— -" + "失效的状态处理，需要重新获取 Token");
            }

            @Override
            public void onSuccess(String userId) {
                Log.e("MainActivity", "——onSuccess— -" + userId);
                 }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MainActivity", "——onError— -" + errorCode);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        shop_imgs = sharedPreferences.getString("show_img", "");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (slidingMenu.isMenuShowing()) {
            slidingMenu.showMenu();
        } else {

        }

    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
