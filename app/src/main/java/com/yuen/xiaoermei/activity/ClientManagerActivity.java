package com.yuen.xiaoermei.activity;

import android.os.Bundle;
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
import com.yuen.xiaoermei.bean.ClientBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

/**
 * 客户管理
 */
public class ClientManagerActivity extends BaseActivity {


    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private LinearLayout layout_title_bar;
    private TextView tv_client_now_count;
    private TextView tv_client_new_add;

    private void initView() {
        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
        tv_title_dec = (TextView) findViewById(R.id.tv_title_dec);
        iv_btn_add = (ImageView) findViewById(R.id.iv_btn_add);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        tv_client_now_count = (TextView) findViewById(R.id.tv_client_now_count);
        tv_client_new_add = (TextView) findViewById(R.id.tv_client_new_add);
        tv_title_dec.setText("客户管理");
        iv_btn_add.setVisibility(View.GONE);
        iv_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_manager);
        SysExitUtil.activityList.add(this);
        initView();
        toNext();
        getClient();
    }

    private void getClient() {
        XUtils.xUtilsGet(ContactURL.GET_CLENT + MainActivity.userid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "------GET_CLENT------"+result);
                Gson gson = new Gson();
                ClientBean clientBean = gson.fromJson(result, ClientBean.class);
                tv_client_now_count.setText(clientBean.getZong()+"人");
                tv_client_new_add.setText(clientBean.getZuo()+"人");
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
        if (keyCode == KeyEvent.KEYCODE_BACK&&event.getAction()== MotionEvent.ACTION_UP) {

            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
