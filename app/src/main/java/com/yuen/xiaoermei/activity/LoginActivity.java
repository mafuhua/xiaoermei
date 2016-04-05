package com.yuen.xiaoermei.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.yuen.xiaoermei.utils.SysExitUtil;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtLoginUsername;
    private EditText mEtLoginPassword;
    private CheckBox mCbLoginRememberPassword;
    private TextView mTvLoginForgetPassword;
    private ImageView mIvBtnLogin;
    private SharedPreferences sharedPreferences;
    private String username;
    private String password;

    private void assignViews() {
        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        mEtLoginUsername = (EditText) findViewById(R.id.et_login_username);
        mEtLoginPassword = (EditText) findViewById(R.id.et_login_password);
        mCbLoginRememberPassword = (CheckBox) findViewById(R.id.cb_login_remember_password);
        mTvLoginForgetPassword = (TextView) findViewById(R.id.tv_login_forget_password);
        mIvBtnLogin = (ImageView) findViewById(R.id.iv_btn_login);
        mIvBtnLogin.setOnClickListener(this);
        mCbLoginRememberPassword.setOnClickListener(this);
        mTvLoginForgetPassword.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assignViews();
        boolean rempsw = sharedPreferences.getBoolean("rempsw", false);
        mCbLoginRememberPassword.setChecked(rempsw);
        username = sharedPreferences.getString("username", "");
        password = sharedPreferences.getString("password", "");
        if (rempsw) {
            mEtLoginUsername.setText(username);
            mEtLoginPassword.setText(password);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((sharedPreferences.getString("lgusername", "").length() > 1) && (sharedPreferences.getString("lgusername", "").length() > 1)) {
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
                login();
                break;
            case R.id.cb_login_remember_password:
                if (!mCbLoginRememberPassword.isChecked()) {
                    sharedPreferences.edit().putBoolean("rempsw", false).putString("lgusername", "")
                            .putString("lgpassword", "").apply();
                } else {
                    sharedPreferences.edit().putBoolean("rempsw", true).apply();

                }
                break;
            case R.id.tv_login_forget_password:

                break;
        }
    }


    private void login() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "admin");
        map.put("password", "123456");

        XUtils.xUtilsPost(ContactURL.LOGIN_URL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //  Log.d("mafuhua", result.toString());
                String res = result.toString();
                sharedPreferences.edit().putString("username", "admin")
                        .putString("password", "123456").apply();
                sharedPreferences.edit().putString("lgusername", "admin")
                        .putString("lgpassword", "123456").apply();
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
                            .putString("show_img", dataBean.getShop_img())
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SysExitUtil.exit();
    }

}
