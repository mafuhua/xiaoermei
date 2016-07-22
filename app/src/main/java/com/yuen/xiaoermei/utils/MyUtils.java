package com.yuen.xiaoermei.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.xutils.image.ImageOptions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.rong.imkit.RongContext;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MyUtils {
    public static ImageOptions options = new ImageOptions.Builder()
            //设置使用缓存
            .setUseMemCache(true)
            // 图片缩放模式
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .build();


    public static ImageOptions options2 = new ImageOptions.Builder()
            .setRadius(20)
            // 是否忽略GIF格式的图片
            .setIgnoreGif(false)
            // 图片缩放模式
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)

            .build();
    public static ImageOptions options6 = new ImageOptions.Builder()
           // .setRadius(80)
            .setCircular(true)
            // 是否忽略GIF格式的图片
            .setIgnoreGif(false)
            // 图片缩放模式
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)

            .build();
    private static Toast toast = null;

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * @param context  内容器实体
     * @param msg      提示文字所在资源id
     * @param duration 提示时间
     */
    public static void toastShow(Context context, String msg, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static String getInputString(Context context, EditText editText, String msg) {
        if (editText == null) {
            return null;
        } else {
            String content = editText.getText().toString();
            if (TextUtils.isEmpty(content)) {
                toastShow(context, msg, Toast.LENGTH_SHORT);
                return "";
            }
            return content;
        }
    }

    /**
     * @param file
     * @Description 删除文件或文件夹
     */
    public static void deletefile(File file) {
        if (!file.exists()) {
            return; // 不存在直接返回
        }

        if (file.isFile()) {
            file.delete(); // 若是文件则删除后返回
            return;
        }

        // 若是目录递归删除后,并最后删除目录后返回
        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete(); // 如果是空目录，直接删除
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                deletefile(childFiles[i]); // 递归删除子文件或子文件夹
            }
            file.delete(); // 删除最后的空目录
        }
        return;
    }
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 新版时间展示 聊天页面
     * @param mTimeStamp
     * @return
     * 【备注】注意时间单位是毫秒
     */
    public static String getSessionTime(long mTimeStamp) {
        if (mTimeStamp <= 0) {
            return null;
        }
        String[] weekDays = {
                "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
        };
        String strDesc = null;
        SimpleDateFormat formatYear = new SimpleDateFormat("yy/MM/dd");
        SimpleDateFormat formatToday = new SimpleDateFormat("HH:mm");
        /**消息时间戳*/
        long changeTime = (long) mTimeStamp;
        long messageTimeStamp = changeTime * 1000;
        /**当前的时间戳*/
        long currentTimeStamp =System.currentTimeMillis();
        /**获取今天的 0 点时间戳*/
        long todayTimeStamp = getTimesmorning();
        /**获取 上一周 0点时间戳*/
        long rangeWeekStamp = todayTimeStamp - 86400000*6;

        /**今天的显示 hh:mm   (今天星期三)
         * 昨天
         * 星期一
         * 星期日 、 星期六、 星期五、星期四
         * yy-hh-mm
         * */
        do{
            long diff = currentTimeStamp -  messageTimeStamp;
            long diffToday = currentTimeStamp - todayTimeStamp;
            /**今天之内的*/
            if(diff < diffToday){
                strDesc = formatToday.format(messageTimeStamp);
                break;
            }

            long diffWeek = currentTimeStamp - rangeWeekStamp;
            /**最近一周的判断*/
            if(diff < diffWeek){
                /**昨天零点的时间*/
                long yesterday = todayTimeStamp - 86400000;
                long diffYesterday = currentTimeStamp - yesterday;
                if(diff < diffYesterday){
                    strDesc = "昨天";
                }else{
                    Calendar weekCal = Calendar.getInstance();
                    weekCal.setTimeInMillis(messageTimeStamp);
                    int w =  weekCal.get(Calendar.DAY_OF_WEEK) -1;
                    w = w<0?0:w;
                    strDesc = weekDays[w];
                }
                break;
            }
            /**年月日显示*/
            strDesc = formatYear.format(messageTimeStamp);
        }while(false);
        return strDesc;
    }
    /**
     * 获取当天 零点的时间戳【linux】
     * @return
     */
    public  static long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
    /**
     * 获取时间
     * @param time
     * @return
     */
    public static String getDate(Long time)
    {
        if(null == time)
        {
            return "";
        }

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }
    public static String formatTime(long timeMillis) {
        if(timeMillis == 0L) {
            return "";
        } else {
            String result = null;
            int targetDay = (int)(timeMillis / 86400000L);
            int nowDay = (int)(System.currentTimeMillis() / 86400000L);
            if(targetDay == nowDay) {
                result = fromatDate(timeMillis, "HH:mm");
            } else if(targetDay + 1 == nowDay) {
                Context context = RongContext.getInstance().getBaseContext();
                String formatString = "昨天";
                result = String.format(formatString, new Object[]{fromatDate(timeMillis, "HH:mm")});
            } else {
                result = fromatDate(timeMillis, "yyyy-MM-dd HH:mm");
            }

            return result;
        }
    }
    private static String fromatDate(long timeMillis, String fromat) {
        SimpleDateFormat sdf = new SimpleDateFormat(fromat);
        return sdf.format(new Date(timeMillis));
    }
}
