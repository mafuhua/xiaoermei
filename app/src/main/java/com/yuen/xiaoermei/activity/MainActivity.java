package com.yuen.xiaoermei.activity;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.fragment.HomeFragment;
import com.yuen.xiaoermei.fragment.MenuFragment;

/**
 * 主页面
 */
public class MainActivity extends SlidingFragmentActivity {

    private SlidingMenu slidingMenu;

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
      //  slidingMenu.setShadowDrawable(R.drawable.shadow);
//        slidingMenu.setShadowWidthRes(200);//错误
      //  slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        //左边的菜单
        slidingMenu.setMode(SlidingMenu.LEFT);
        //打开方式：全屏
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //菜单的偏移量
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //替换菜单和内容Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment(),"HOME").commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu, new MenuFragment(),"MENU").commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (slidingMenu.isMenuShowing()){
            slidingMenu.showMenu();
        }else {

        }

    }
}
