package com.yuen.xiaoermei.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.utils.SysExitUtil;

/**
 * 意见反馈编辑
 */
public class SettingOpinionEditActivity extends BaseActivity {

    private EditText mEtShopNoticeTitle;
    private EditText mEtShopNoticeContent;
    private ImageView mIvBtnShopnoticeTijiao;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;

    private void assignViews() {
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mEtShopNoticeTitle = (EditText) findViewById(R.id.et_shop_notice_title);
        mEtShopNoticeContent = (EditText) findViewById(R.id.et_shop_notice_content);
        mTvTitleDec.setText("意见反馈");
        mIvBtnAdd.setVisibility(View.GONE);
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_opinion_edit);
        SysExitUtil.activityList.add(this);
        toNext();
        assignViews();
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
