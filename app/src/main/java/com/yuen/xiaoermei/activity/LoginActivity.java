package com.yuen.xiaoermei.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.bean.LoginBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtLoginUsername;
    private EditText mEtLoginPassword;
    private CheckBox mRbLoginRememberPassword;
    private TextView mTvLoginForgetPassword;
    private ImageView mIvBtnLogin;
    private SharedPreferences sharedPreferences;

    private void assignViews() {
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        mEtLoginUsername = (EditText) findViewById(R.id.et_login_username);
        mEtLoginPassword = (EditText) findViewById(R.id.et_login_password);
        mRbLoginRememberPassword = (CheckBox) findViewById(R.id.cb_login_remember_password);
        mTvLoginForgetPassword = (TextView) findViewById(R.id.tv_login_forget_password);
        mIvBtnLogin = (ImageView) findViewById(R.id.iv_btn_login);
        mIvBtnLogin.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assignViews();
        if (!TextUtils.isEmpty(sharedPreferences.getString("username","")) && !TextUtils.isEmpty(sharedPreferences.getString("password", "")) ) {
          //  Log.d("mafuhua", "******"+sharedPreferences.getString("username", ""));
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_login:
              /*  String userName = mEtLoginUsername.getText().toString().trim();
                String password = mEtLoginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;

                }*/


                HashMap<String, String> map = new HashMap<String, String>();
                map.put("name", "admin");
                map.put("password", "123456");
                sharedPreferences.edit().putString("username", "admin")
                        .putString("password", "123456").apply();
                XUtils.xUtilsPost(ContactURL.LOGIN_URL, map, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //Log.d("mafuhua", result.toString());
                        String res = result.toString();
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(res, LoginBean.class);
                        LoginBean.DataBean dataBean = loginBean.getData();
                        /**
                         * getcode
                         * 店铺是0,品牌是1
                         */
                        if (loginBean.getCode().equals("0") && loginBean.getMsg().equals("成功")) {
                            sharedPreferences.edit()
                                    .putString("tel", dataBean.getTel())
                                    .putString("id", dataBean.getId())
                                    .apply();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(intent);
                            finish();
                        }
                        if (loginBean.getCode().equals("1") && loginBean.getMsg().equals("成功")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(intent);
                            finish();
                        } else {

                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.d("mafuhua", isOnCallback + "");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        cex.printStackTrace();
                    }

                    @Override
                    public void onFinished() {

                    }
                });


                break;
        }
    }
}
