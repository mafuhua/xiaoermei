package com.yuen.xiaoermei.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.utils.SysExitUtil;

/**
 * 意见反馈编辑
 */
public class SettingOpinionEditActivity extends BaseActivity {


    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private TextView tv_tixian;
    private LinearLayout layout_title_bar;
    private EditText et_setting_opnion_content;
    private ImageView iv_btn_setting_opnion_tijiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_opinion_edit);
        initView();
        SysExitUtil.activityList.add(this);
        toNext();
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initView() {
        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
        tv_title_dec = (TextView) findViewById(R.id.tv_title_dec);
        iv_btn_add = (ImageView) findViewById(R.id.iv_btn_add);
        tv_tixian = (TextView) findViewById(R.id.tv_tixian);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        et_setting_opnion_content = (EditText) findViewById(R.id.et_setting_opnion_content);
        iv_btn_setting_opnion_tijiao = (ImageView) findViewById(R.id.iv_btn_setting_opnion_tijiao);
        tv_title_dec.setText("意见反馈");
        iv_btn_add.setVisibility(View.GONE);
        iv_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_btn_setting_opnion_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit();
            }
        });
    }

    private void submit() {

        String content = et_setting_opnion_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        SystemClock.sleep(1000);
        Toast.makeText(SettingOpinionEditActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
        finish();
    }
}
