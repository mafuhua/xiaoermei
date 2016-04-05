package com.yuen.xiaoermei.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.IconResultBean;
import com.yuen.xiaoermei.bean.ShopNameBean;
import com.yuen.xiaoermei.bean.ShopTitleBean;
import com.yuen.xiaoermei.bean.UserIconBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import piccutdemo.RoundImageView;

public class MyInfomationActivity extends AppCompatActivity implements View.OnClickListener {
    private final String ACTION_NAME = "geticon";
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private LinearLayout layout_title_bar;
    private RoundImageView iv_user_icon;
    private TextView tv_user_name;
    private RelativeLayout rl_user_icon;
    private TextView et_user_nickname;
    private RelativeLayout rl_user_nickname;
    private TextView et_user_shop_name;
    private RelativeLayout rl_user_shop_name;
    private TextView et_user_num;
    private RelativeLayout rl_user_num;
    private Button btn_login_out;
    private Context context;
    private File destDir;
    private SharedPreferences sharedPreferences;

    private void assignViews() {
        context = this;
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        iv_user_icon = (RoundImageView) findViewById(R.id.iv_user_icon);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        rl_user_icon = (RelativeLayout) findViewById(R.id.rl_user_icon);
        et_user_nickname = (TextView) findViewById(R.id.et_user_nickname);
        rl_user_nickname = (RelativeLayout) findViewById(R.id.rl_user_nickname);
        et_user_shop_name = (TextView) findViewById(R.id.et_user_shop_name);
        rl_user_shop_name = (RelativeLayout) findViewById(R.id.rl_user_shop_name);
        et_user_num = (TextView) findViewById(R.id.et_user_num);
        rl_user_num = (RelativeLayout) findViewById(R.id.rl_user_num);
        btn_login_out = (Button) findViewById(R.id.btn_login_out);
        btn_login_out.setVisibility(View.GONE);
        mIvBtnAdd.setVisibility(View.GONE);
        mTvTitleDec.setText("我的资料");
        et_user_num.setText(MainActivity.username);
        mIvBtnBack.setOnClickListener(this);
        rl_user_nickname.setOnClickListener(this);
        rl_user_num.setOnClickListener(this);
        rl_user_shop_name.setOnClickListener(this);
        iv_user_icon.setOnClickListener(this);
        btn_login_out.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infomation);
        assignViews();
        SysExitUtil.activityList.add(this);
        iv_user_icon.setType(RoundImageView.TYPE_ROUND);
        iv_user_icon.setBorderRadius(60);
        getUserIcon(ContactURL.SHOP_STORE_TOU + MainActivity.userid);
        x.image().bind(iv_user_icon, MainActivity.shop_imgs);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getShopInfo(ContactURL.SHOP_GET_NICK, 1);
        getShopInfo(ContactURL.SHOP_GET_SHOPTITILE, 2);
        //
    }

    /**
     * 选择提示对话框
     */
    private void ShowPickDialog() {
        new AlertDialog.Builder(this)
                .setTitle("设置头像...")
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        /**
                         * 刚开始，我自己也不知道ACTION_PICK是干嘛的，后来直接看Intent源码，
                         * 可以发现里面很多东西，Intent是个很强大的东西，大家一定仔细阅读下
                         */
                        Intent intent = new Intent(Intent.ACTION_PICK, null);

                        /**
                         * 下面这句话，与其它方式写是一样的效果，如果：
                         * intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                         * intent.setType(""image/*");设置数据类型
                         * 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                         * 这个地方小马有个疑问，希望高手解答下：就是这个数据URI与类型为什么要分两种形式来写呀？有什么区别？
                         */
                        intent.setDataAndType(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                        startActivityForResult(intent, 1);

                    }
                })
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        /**
                         * 下面这句还是老样子，调用快速拍照功能，至于为什么叫快速拍照，大家可以参考如下官方
                         * 文档，you_sdk_path/docs/guide/topics/media/camera.html
                         * 我刚看的时候因为太长就认真看，其实是错的，这个里面有用的太多了，所以大家不要认为
                         * 官方文档太长了就不看了，其实是错的，这个地方小马也错了，必须改正
                         */
                        Intent intent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        //下面这句指定调用相机拍照后的照片存储的路径
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(new File(Environment
                                        .getExternalStorageDirectory(),
                                        "xiaoma.jpg")));
                        startActivityForResult(intent, 2);
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // 如果是直接从相册获取
            case 1:
                startPhotoZoom(data.getData());
                break;
            // 如果是调用相机拍照时
            case 2:
                File temp = new File(Environment.getExternalStorageDirectory()
                        + "/xiaoma.jpg");
                startPhotoZoom(Uri.fromFile(temp));
                break;
            // 取得裁剪后的图片
            case 3:
                /**
                 * 非空判断大家一定要验证，如果不验证的话，
                 * 在剪裁之后如果发现不满意，要重新裁剪，丢弃
                 * 当前功能时，会报NullException，小马只
                 * 在这个地方加下，大家可以根据不同情况在合适的
                 * 地方做判断处理类似情况
                 *
                 */
                if (data != null) {
                    setPicToView(data);
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        /*
         * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * 直接在里面Ctrl+F搜：CROP ，之前小马没仔细看过，其实安卓系统早已经有自带图片裁剪功能,
		 * 是直接调本地库的，小马不懂C C++  这个不做详细了解去了，有轮子就用轮子，不再研究轮子是怎么
		 * 制做的了...吼吼
		 */
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            iv_user_icon.setImageBitmap(photo);
            saveBitmapFile(photo);

           /* Bitmap scaledBitmap = Bitmap.createScaledBitmap(photo, 65, 65, false);
            Bitmap circleImage = CustomImageView.createCircleImage(scaledBitmap, 70);
           *//* circleImage.setHeight(70);
            circleImage.setWidth(70);*//*
            Drawable drawable = new BitmapDrawable(circleImage);*/

            /**
             * 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上
             * 传到服务器，QQ头像上传采用的方法跟这个类似
             */

			/*ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 60, stream);
			byte[] b = stream.toByteArray();
			// 将图片流以字符串形式存储下来

			tp = new String(Base64Coder.encodeLines(b));
			这个地方大家可以写下给服务器上传图片的实现，直接把tp直接上传就可以了，
			服务器处理的方法是服务器那边的事了，吼吼

			如果下载到的服务器的数据还是以Base64Coder的形式的话，可以用以下方式转换
			为我们可以用的图片类型就OK啦...吼吼
			Bitmap dBitmap = BitmapFactory.decodeFile(tp);
			Drawable drawable = new BitmapDrawable(dBitmap);
			*/
            //  iv_user_icon.setBackgroundDrawable(drawable);
            //  iv_user_icon.setImageBitmap(circleImage);
            // iv_user_icon.setBackground(CustomImageView.createCircleImage(photo,70));
            //iv.setBackgroundDrawable(drawable);
        }
    }

    public void saveBitmapFile(Bitmap bitmap) {
        /**
         * 创建文件夹存放压缩文件
         */
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        destDir = new File(externalStorageDirectory + "/imagcacahe/");
        // Log.d("mafuhua", "externalStorageDirectory:" + destDir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        File file = new File(destDir + "01.jpg");//将要保存图片的路径

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendimg(file.getPath());
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_login_out:
                break;
            case R.id.iv_user_icon:
                ShowPickDialog();

                break;
            case R.id.rl_user_nickname:
                intent = new Intent(context, EditMyInfomationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("name", "修改昵称");
                intent.putExtra("flag", "1");
                startActivity(intent);


                break;
            case R.id.rl_user_shop_name:
                intent = new Intent(context, EditMyInfomationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("name", "修改店铺名");
                intent.putExtra("flag", "2");
                startActivity(intent);

                break;
            case R.id.iv_btn_back:
                finish();
                break;
        }
    }

    private void sendimg(String path) {

        AsyncHttpClient client = new AsyncHttpClient();

        String url = ContactURL.SHOP_EDIT_TOU;

        com.loopj.android.http.RequestParams rp = new com.loopj.android.http.RequestParams();

        File file = new File(path);
        //  Log.d("mafuhua", path + "**************");
        try {
            rp.put("img", file);
            rp.add("shou_img", MainActivity.shop_imgs);
            rp.add("user_id", MainActivity.userid);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        client.post(url, rp, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                // Log.d("mafuhua", "responseBody" + response);
                Gson gson = new Gson();
                IconResultBean iconResultBean = gson.fromJson(response, IconResultBean.class);
                if (iconResultBean.getStatus().equals("0")) {
                    Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
                    getUserIcon(ContactURL.SHOP_STORE_TOU + MainActivity.userid);
                } else {
                    Toast.makeText(context, "上传失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }


        });
    }

    public void getUserIcon(String url) {
        RequestParams params = new RequestParams(url);
        //  Log.d("mafuhua", url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //  Log.d("mafuhua", "result" + result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                UserIconBean userIconBean = gson.fromJson(res, UserIconBean.class);
                String shop_img = userIconBean.getData().getShop_img();
                if (shop_img.length() > 0) {

                    String shop_imgs = userIconBean.getData().getShop_imgs();
                    sharedPreferences.edit()
                            .putString("show_img", shop_imgs)
                            .apply();
                    Intent mIntent = new Intent(ACTION_NAME);
                    //发送广播
                    sendBroadcast(mIntent);
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("mafuhua", isOnCallback + "");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public void getShopInfo(String url, final int type) {
        RequestParams params = new RequestParams(url + MainActivity.userid);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // Log.d("mafuhua", "result" + result.toString());
                String res = result.toString();
              /*  Gson gson = new Gson();
                ShopFreightBean shopFreightBean = gson.fromJson(res, ShopFreightBean.class);
                String store_freight = shopFreightBean.getData().getShop_freight();
                settingMap.put(4, store_freight + "元");
                myAdapter.notifyDataSetChanged();*/
                if (type == 1) {
                    parseJson(res);
                } else if (type == 2) {
                    parseJson2(res);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("mafuhua", isOnCallback + "");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void parseJson(String res) {
        Gson gson = new Gson();
        ShopNameBean shopNameBean = gson.fromJson(res, ShopNameBean.class);
        String shop_name = shopNameBean.getData().getShop_name();
        et_user_nickname.setText(shop_name);
    }

    private void parseJson2(String res) {
        Gson gson = new Gson();
        ShopTitleBean shopTitleBean = gson.fromJson(res, ShopTitleBean.class);
        String shop_title = shopTitleBean.getData().getShop_title();
        et_user_shop_name.setText(shop_title);
    }


}
