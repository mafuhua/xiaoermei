package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.bean.MoneyBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

/**
 * 财务管理
 */
public class MoneyManagerActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvUserName;
    private TextView mTvUserPhone;
    private TextView mTvMoneyAllmoney;
    private TextView mTvMoneyOutmoney;
    private RelativeLayout mRlMoneyOut;
    private ImageView mIconfonttixian3x1;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private SharedPreferences sharedPreferences;
    private TextView tv_money_yumoney;
    private ImageView iv_user_icon;
    private MoneyBean moneyBean;
    private TextView tv_tixian;

    private void assignViews() {
        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        iv_user_icon = (ImageView) findViewById(R.id.iv_user_icon);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mTvUserName = (TextView) findViewById(R.id.tv_user_name);
        mTvUserPhone = (TextView) findViewById(R.id.tv_user_phone);
        mTvMoneyAllmoney = (TextView) findViewById(R.id.tv_money_allmoney);
        tv_money_yumoney = (TextView) findViewById(R.id.tv_money_yumoney);
        mTvMoneyOutmoney = (TextView) findViewById(R.id.tv_money_outmoney);
        tv_tixian = (TextView) findViewById(R.id.tv_tixian);
        mRlMoneyOut = (RelativeLayout) findViewById(R.id.rl_money_out);
      /*  mTvUserName.setText(sharedPreferences.getString("username", ""));
        mTvUserPhone.setText(sharedPreferences.getString("tel", ""));
        x.image().bind(iv_user_icon,sharedPreferences.getString("show_img",""), MyUtils.options2);*/
        mTvTitleDec.setText("财务管理");
        mIvBtnAdd.setVisibility(View.GONE);
        tv_tixian.setVisibility(View.VISIBLE);
        mRlMoneyOut.setOnClickListener(this);
        tv_tixian.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_manager);
        SysExitUtil.activityList.add(this);
        toNext();
        assignViews();
        getMoney();
    }

    private void getMoney() {
        XUtils.xUtilsGet(ContactURL.GET_MONEY + MainActivity.userid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "----GET_MONEY-----" + result);
                Gson gson = new Gson();
                moneyBean = gson.fromJson(result, MoneyBean.class);
                mTvMoneyAllmoney.setText(moneyBean.getZong());
                tv_money_yumoney.setText(moneyBean.getYu() + "");
                mTvMoneyOutmoney.setText(moneyBean.getPrice_num() + "");

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;

            case R.id.tv_tixian:
                Log.d("mafuhua", "OutMoneyListActivity");
                intent = new Intent(MoneyManagerActivity.this, OutMoneyListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;
            case R.id.rl_money_out:
                intent = new Intent(MoneyManagerActivity.this, OutMoneyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("moneyBean", moneyBean);
                startActivity(intent);
                break;
        }
    }
}
