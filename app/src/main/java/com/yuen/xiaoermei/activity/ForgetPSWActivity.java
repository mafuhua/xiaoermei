package com.yuen.xiaoermei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.ShopYanZhengMaBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class ForgetPSWActivity extends AppCompatActivity implements View.OnClickListener {
    public static String zhagnhao;
    public static String dianhua;
    public ShopYanZhengMaBean shopYanZhengMaBean;
    EditText mEtForgetPswZh;
    EditText mEtForgetPswTel;
    private TextView mTvGetYzm;
    private EditText mEtForgetPswYzm;
    private Button mIvBtnForgetPswNext;

    private void assignViews() {
        mEtForgetPswZh = (EditText) findViewById(R.id.et_forget_psw_zh);
        mEtForgetPswTel = (EditText) findViewById(R.id.et_forget_psw_tel);
        mTvGetYzm = (TextView) findViewById(R.id.tv_get_yzm);
        mEtForgetPswYzm = (EditText) findViewById(R.id.et_forget_psw_yzm);
        mIvBtnForgetPswNext = (Button) findViewById(R.id.iv_btn_foregetpswfinish_finish);
        mTvGetYzm.setOnClickListener(this);
        mIvBtnForgetPswNext.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_psw);
        assignViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_yzm:
                zhagnhao = mEtForgetPswZh.getText().toString().trim();
                dianhua = mEtForgetPswTel.getText().toString().trim();
                if (TextUtils.isEmpty(zhagnhao)) {
                    Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (TextUtils.isEmpty(dianhua)) {
                    Toast.makeText(this, "手机号不能为空!", Toast.LENGTH_SHORT).show();
                    break;

                }
                HashMap<String, String> map = new HashMap<>();
                map.put("name", zhagnhao);
                map.put("tel", dianhua);
                XUtils.xUtilsPost(ContactURL.SHOP_GET_YANZHENGMA, map, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d("mafuhua", "SHOP_GET_YANZHENGMA--------" + result);
                        Gson gson = new Gson();
                        shopYanZhengMaBean = gson.fromJson(result, ShopYanZhengMaBean.class);
                        Toast.makeText(ForgetPSWActivity.this, shopYanZhengMaBean.getMsg(), Toast.LENGTH_SHORT).show();
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
                break;
            case R.id.iv_btn_foregetpswfinish_finish:
                Intent intent = new Intent(ForgetPSWActivity.this, ForgetPSWFINISHActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                break;

        }
    }
}
