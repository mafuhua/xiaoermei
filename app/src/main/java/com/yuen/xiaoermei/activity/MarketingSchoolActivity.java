package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;

/**
 * 开店须知
 */
public class MarketingSchoolActivity extends BaseActivity {
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
        mTvTitleDec.setText("营销商学院");
        mIvBtnAdd.setBackgroundResource(R.drawable.iconfontbianji2x);
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvBtnAdd.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_notice);
        toNext();
        assignViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&&event.getAction()== MotionEvent.ACTION_UP) {

            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
