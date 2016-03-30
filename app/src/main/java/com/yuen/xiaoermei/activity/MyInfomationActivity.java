package com.yuen.xiaoermei.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuen.xiaoermei.R;

public class MyInfomationActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private LinearLayout layout_title_bar;
    private ImageView iv_user_icon;
    private TextView tv_user_name;
    private RelativeLayout rl_user_icon;
    private TextView et_user_nickname;
    private RelativeLayout rl_user_nickname;
    private TextView et_user_shop_name;
    private RelativeLayout rl_user_shop_name;
    private TextView et_user_num;
    private RelativeLayout rl_user_num;
    private Button btn_login_out;


    private void assignViews() {
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        iv_user_icon = (ImageView) findViewById(R.id.iv_user_icon);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        rl_user_icon = (RelativeLayout) findViewById(R.id.rl_user_icon);
        et_user_nickname = (TextView) findViewById(R.id.et_user_nickname);
        rl_user_nickname = (RelativeLayout) findViewById(R.id.rl_user_nickname);
        et_user_shop_name = (TextView) findViewById(R.id.et_user_shop_name);
        rl_user_shop_name = (RelativeLayout) findViewById(R.id.rl_user_shop_name);
        et_user_num = (TextView) findViewById(R.id.et_user_num);
        rl_user_num = (RelativeLayout) findViewById(R.id.rl_user_num);
        btn_login_out = (Button) findViewById(R.id.btn_login_out);
        mIvBtnAdd.setVisibility(View.GONE);
        mTvTitleDec.setText("我的资料");
        mIvBtnBack.setOnClickListener(this);
        btn_login_out.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infomation);
        assignViews();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_out:

                break;
            case R.id.iv_btn_back:
                finish();
                break;
        }
    }
}
