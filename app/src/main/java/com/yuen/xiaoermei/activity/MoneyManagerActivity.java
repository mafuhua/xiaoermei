package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.utils.SysExitUtil;

/**
 * 财务管理
 */
public class MoneyManagerActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvUserName;
    private TextView mTvUserPhone;
    private TextView mTvMoneyAllmoney;
    private TextView mTvMoneyOutmoney;
    private RelativeLayout mRlMoneyCard;
    private RelativeLayout mRlMoneyOut;
    private ImageView mIconfonttixian3x1;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private SharedPreferences sharedPreferences;

    private void assignViews() {
        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mTvUserName = (TextView) findViewById(R.id.tv_user_name);
        mTvUserPhone = (TextView) findViewById(R.id.tv_user_phone);
        mTvMoneyAllmoney = (TextView) findViewById(R.id.tv_money_allmoney);
        mTvMoneyOutmoney = (TextView) findViewById(R.id.tv_money_outmoney);
        mRlMoneyCard = (RelativeLayout) findViewById(R.id.rl_money_card);
        mRlMoneyOut = (RelativeLayout) findViewById(R.id.rl_money_out);
        mTvTitleDec.setText("财务管理");
        mTvUserName.setText(sharedPreferences.getString("username", ""));
        mTvUserPhone.setText(sharedPreferences.getString("tel", ""));
        mIvBtnAdd.setVisibility(View.GONE);
        mRlMoneyCard.setOnClickListener(this);
        mRlMoneyOut.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_manager);
        SysExitUtil.activityList.add(this);
        toNext();
        assignViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK&&event.getAction()== MotionEvent.ACTION_UP) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;
            case R.id.rl_money_card:
                break;
            case R.id.rl_money_out:
                break;
        }
    }
}
