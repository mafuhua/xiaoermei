package com.yuen.xiaoermei.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Toast是Android中用来显示信息的一种机制，和Dialog不一样的是，
 * Toast是没有焦点，而且Toast显示的时间有限，过一定的时间就会自动消失。
 * <p/>
 * Toast一般用来提示用户的误操作。但是如果同时显示多个Toast信息提示框，系统会将这些Toast信息提示框放到队列中，
 * 等前一个Toast信息提示框关闭后才会显示下一个Toast信息提示框。当用户在某些情况下，误操作多次时，
 * 使用 Toast提示会出现很多个Toast依次显示，在页面上停留很长时间，用户体验很不好！
 * 为了解决这一问题，每次创建Toast时先做一下判断，如果前面有Toast在显示，
 * 只需调用Toast中的setText（）方法将要显示的信息替换即可。
 * 代码如下：
 * 自定义CustomToast 类：
 */
public class CustomToast {

    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, duration);

        mToast.show();
    }

    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }

}