package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.bean.HelpBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class SettingOurActivity extends BaseActivity {
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
        mIvBtnAdd.setBackgroundResource(R.drawable.iconfontbianji2x);
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvBtnAdd.setVisibility(View.GONE);
    }
    public void getContent(String url){
        RequestParams params = new RequestParams(ContactURL.SHOP_GET_HELP);
        params.addBodyParameter("page_name",url);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HelpBean helpBean = gson.fromJson(result, HelpBean.class);
                mTvShopNoticeTitle.setText(helpBean.getData().getPage_name());
                mTvShopNoticeContent.setText(helpBean.getData().getContent());
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_notice);
        SysExitUtil.activityList.add(this);
        toNext();
        Intent intent = getIntent();
        String flag = intent.getStringExtra("flag");
        assignViews();
        mTvTitleDec.setText(flag);
        getContent(flag);
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
