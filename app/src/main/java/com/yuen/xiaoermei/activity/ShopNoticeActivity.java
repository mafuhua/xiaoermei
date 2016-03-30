package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.bean.ShopGGBean;
import com.yuen.xiaoermei.utils.ContactURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 开店须知
 */
public class ShopNoticeActivity extends BaseActivity {
    private TextView mTvShopNoticeTitle;
    private TextView mTvShopNoticeContent;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;


    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mTvShopNoticeTitle = (TextView) findViewById(R.id.tv_shop_notice_title);
        mTvShopNoticeContent = (TextView) findViewById(R.id.tv_shop_notice_content);
        mTvTitleDec.setText("店铺公告");
        mIvBtnAdd.setBackgroundResource(R.drawable.iconfontbianji2x);
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvBtnAdd.setOnClickListener(new View.OnClickListener() {
            private Intent intent;

            @Override
            public void onClick(View v) {
                intent = new Intent(context, ShopNoticeEditActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_notice);
        toNext();
        assignViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getShopGG();
    }

    public void getShopGG() {
        RequestParams params = new RequestParams(ContactURL.SHOP_GET_GG + MainActivity.userid);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopGGBean shopGGBean = gson.fromJson(res, ShopGGBean.class);
                String shop_gg_title = shopGGBean.getData().getShop_gg_title();
                String shop_con = shopGGBean.getData().getShop_con();
                if (TextUtils.isEmpty(shop_gg_title) || TextUtils.isEmpty(shop_con)) {
                    Intent intent = new Intent(context, ShopNoticeEditActivity.class);
                    context.startActivity(intent);

                }
                mTvShopNoticeTitle.setText(shop_gg_title);
                mTvShopNoticeContent.setText(shop_con);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {

            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
