package com.yuen.xiaoermei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.BaseBean;
import com.yuen.xiaoermei.bean.MoneyBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class OutMoneyActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private LinearLayout layout_title_bar;
    private TextView tv_out_name;
    private EditText ed_zfb_num;
    private EditText ed_zfb_name;
    private EditText ed_zfb_money;
    private Button btn_getmoney;
    private MoneyBean moneyBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_money);
        Intent intent = getIntent();
        moneyBean = (MoneyBean) intent.getSerializableExtra("moneyBean");
        initView();
    }

    private void initView() {
        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
        tv_title_dec = (TextView) findViewById(R.id.tv_title_dec);
        iv_btn_add = (ImageView) findViewById(R.id.iv_btn_add);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        tv_out_name = (TextView) findViewById(R.id.tv_out_name);

        ed_zfb_num = (EditText) findViewById(R.id.ed_zfb_num);
        ed_zfb_name = (EditText) findViewById(R.id.ed_zfb_name);
        ed_zfb_money = (EditText) findViewById(R.id.ed_zfb_money);
        btn_getmoney = (Button) findViewById(R.id.btn_getmoney);
        ed_zfb_money.setHint("可提现余额" + moneyBean.getPrice_num());
        tv_title_dec.setText("财务管理");

        tv_out_name.setText(MainActivity.username);
        btn_getmoney.setOnClickListener(this);
        iv_btn_back.setOnClickListener(this);
        iv_btn_add.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;
            case R.id.btn_getmoney:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String num = ed_zfb_num.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            Toast.makeText(this, "支付宝账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = ed_zfb_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "支付宝姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String money = ed_zfb_money.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            Toast.makeText(this, "提现金额不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Double i = Double.parseDouble(money);
        if (i> Double.parseDouble(moneyBean.getYu())) {
            Toast.makeText(this, "余额不足", Toast.LENGTH_SHORT).show();
            return;
        } else {
            getMoney(num, name, money);
        }
        // TODO validate success, do something


    }

    private void getMoney(String num, String name, String money) {
        HashMap<String, String> map = new HashMap<>();
        map.put("shop_id", MainActivity.userid);
        map.put("price", money);
        map.put("name", name);
        map.put("zhifubao", num);
        XUtils.xUtilsPost(ContactURL.OUT_MONEY, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "-----OUT_MONEY-----" + result);
                Gson gson = new Gson();
                BaseBean baseBean = gson.fromJson(result, BaseBean.class);
                Toast.makeText(OutMoneyActivity.this, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
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
}
