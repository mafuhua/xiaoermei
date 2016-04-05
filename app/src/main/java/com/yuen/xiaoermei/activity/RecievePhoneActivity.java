package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.bean.ShopPhoneNumSuccBean;
import com.yuen.xiaoermei.bean.ShopYanZhengMaBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

/**
 * 接单手机号
 */
public class RecievePhoneActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtRecievePhoneNum;
    private EditText mEtRecieveTestNum;
    private ImageView mIvRecieveTestNum;
    private ImageView mIvBtnRecieveTijiao;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private String tel;
    private int shopYanZhengMaBeanYan;


    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mEtRecievePhoneNum = (EditText) findViewById(R.id.et_recieve_phone_num);
        mEtRecieveTestNum = (EditText) findViewById(R.id.et_recieve_test_num);
        mIvRecieveTestNum = (ImageView) findViewById(R.id.iv_recieve_test_num);
        mIvBtnRecieveTijiao = (ImageView) findViewById(R.id.iv_btn_recieve_tijiao);
        mTvTitleDec.setText("接单手机号");
        mIvBtnBack.setOnClickListener(this);
        mIvBtnRecieveTijiao.setOnClickListener(this);
        mIvRecieveTestNum.setOnClickListener(this);
        mIvBtnAdd.setVisibility(View.GONE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_phone);
        SysExitUtil.activityList.add(this);
        assignViews();
    }

    private void setGetDuanxin(String tel) {
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("tel", tel);
        XUtils.xUtilsPost(ContactURL.SHOP_GET_DUANXIN, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopYanZhengMaBean shopYanZhengMaBean = gson.fromJson(res, ShopYanZhengMaBean.class);
                shopYanZhengMaBeanYan = shopYanZhengMaBean.getYan();

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

    private void setAddPNum(String tel, String yanzhengma, String etyan) {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("yan", yanzhengma);
        map.put("ke_yan", etyan);
        map.put("tel", tel);
        XUtils.xUtilsPost(ContactURL.SHOP_ADD_TEL, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopPhoneNumSuccBean shopPhoneNumSuccBean = gson.fromJson(res, ShopPhoneNumSuccBean.class);
                String shopPhoneNumSuccBeanMsg = shopPhoneNumSuccBean.getCode();
                if (shopPhoneNumSuccBeanMsg.equals("0")) {
                    Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "提交失败", Toast.LENGTH_SHORT).show();
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {

            goBack();
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
            case R.id.iv_btn_recieve_tijiao:
                String etyan = mEtRecieveTestNum.getText().toString().trim();
                if (TextUtils.isEmpty(etyan)) {
                    Toast.makeText(context, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                setAddPNum(tel, shopYanZhengMaBeanYan + "", etyan);
                break;
            case R.id.iv_recieve_test_num:
                tel = mEtRecievePhoneNum.getText().toString().trim();
                if (TextUtils.isEmpty(tel)) {
                    Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。

                if (tel.matches(telRegex)) {
                    setGetDuanxin(tel);
                } else {
                    Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
        }
    }
}
