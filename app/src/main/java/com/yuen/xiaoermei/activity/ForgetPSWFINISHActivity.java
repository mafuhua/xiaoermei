package com.yuen.xiaoermei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.ShopYanZhengMaBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class ForgetPSWFINISHActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEtForegetpswfinishPsw;
    private EditText mEtForegetpswfinishPswd;
    private Button mIvBtnForegetpswfinishFinish;
    public ShopYanZhengMaBean shopYanZhengMaBean;
    private void assignViews() {
        mEtForegetpswfinishPsw = (EditText) findViewById(R.id.et_foregetpswfinish_psw);
        mEtForegetpswfinishPswd = (EditText) findViewById(R.id.et_foregetpswfinish_pswd);
        mIvBtnForegetpswfinishFinish = (Button) findViewById(R.id.iv_btn_foregetpswfinish_finish);
        mIvBtnForegetpswfinishFinish.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pswfinish);
        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_btn_foregetpswfinish_finish:
                String mima = mEtForegetpswfinishPsw.getText().toString().trim();
                String mimawd = mEtForegetpswfinishPswd.getText().toString().trim();
                if (TextUtils.isEmpty(mima)) {
                    Toast.makeText(this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!mima.equals(mimawd)) {
                    Toast.makeText(this, "密码不一致!", Toast.LENGTH_SHORT).show();
                    break;

                }
                HashMap<String, String> map = new HashMap<>();
                map.put("password", mima);
                map.put("tel", ForgetPSWActivity.dianhua);
                map.put("name", ForgetPSWActivity.zhagnhao);
                XUtils.xUtilsPost(ContactURL.SHOP_SAVE_MIMA, map, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("mafuhua", "SHOP_SAVE_MIMA--------" + result);
                        Gson gson = new Gson();
                        shopYanZhengMaBean = gson.fromJson(result, ShopYanZhengMaBean.class);
                        Toast.makeText(ForgetPSWFINISHActivity.this, shopYanZhengMaBean.getMsg(), Toast.LENGTH_SHORT).show();

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
                Intent intent = new Intent(ForgetPSWFINISHActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
                break;
        }
    }
}
