package com.yuen.xiaoermei.activity;

import android.content.Context;
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

/**
 * 接单手机号
 */
public class RecievePhoneActivity extends BaseActivity implements View.OnClickListener{
    private EditText mEtRecievePhoneNum;
    private EditText mEtRecieveTestNum;
    private ImageView mIvRecieveTestNum;
    private ImageView mIvBtnRecieveTijiao;
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
        mEtRecievePhoneNum = (EditText) findViewById(R.id.et_recieve_phone_num);
        mEtRecieveTestNum = (EditText) findViewById(R.id.et_recieve_test_num);
        mIvRecieveTestNum = (ImageView) findViewById(R.id.iv_recieve_test_num);
        mIvBtnRecieveTijiao = (ImageView) findViewById(R.id.iv_btn_recieve_tijiao);
        mTvTitleDec.setText("接单手机号");
        mIvBtnBack.setOnClickListener(this);
        mIvBtnAdd.setVisibility(View.GONE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_phone);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_btn_back:
                finish();
                break;
        }
    }
}
