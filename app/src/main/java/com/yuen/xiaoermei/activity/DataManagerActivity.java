package com.yuen.xiaoermei.activity;

import android.graphics.Color;
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
import com.yuen.xiaoermei.bean.OrderNumBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

/**
 * 数据管理
 */
public class DataManagerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private LinearLayout mLlBtnDataWeek;
    private LinearLayout mLlBtnDataMonth;
    private TextView mTvDataOrder;
    private TextView mTvDataOrderNum;
    private TextView mTvDataOrderBusiness;
    private TextView mTvDataOrderBusinessMoney;
    private TextView tv_data_left;
    private View line_data_left;
    private TextView tv_data_center;
    private View line_data_center;
    private TextView tv_data_right;
    private View line_data_right;
    private LinearLayout mLlBtnDataDay;
    private OrderNumBean orderNumBean;

    private void assignViews() {
        tv_data_left = (TextView) findViewById(R.id.tv_data_left);
        line_data_left = (View) findViewById(R.id.line_data_left);
        tv_data_center = (TextView) findViewById(R.id.tv_data_center);
        line_data_center = (View) findViewById(R.id.line_data_center);
        tv_data_right = (TextView) findViewById(R.id.tv_data_right);
        line_data_right = (View) findViewById(R.id.line_data_right);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLlBtnDataDay = (LinearLayout) findViewById(R.id.ll_btn_data_day);
        mLlBtnDataWeek = (LinearLayout) findViewById(R.id.ll_btn_data_week);
        mLlBtnDataMonth = (LinearLayout) findViewById(R.id.ll_btn_data_month);
        mTvDataOrder = (TextView) findViewById(R.id.tv_data_order);
        mTvDataOrderNum = (TextView) findViewById(R.id.tv_data_order_num);
        mTvDataOrderBusiness = (TextView) findViewById(R.id.tv_data_order_business);
        mTvDataOrderBusinessMoney = (TextView) findViewById(R.id.tv_data_order_business_money);
        mIvBtnAdd.setVisibility(View.GONE);
        mTvTitleDec.setText("数据统计");
        mIvBtnBack.setOnClickListener(this);
        mLlBtnDataDay.setOnClickListener(this);
        mLlBtnDataWeek.setOnClickListener(this);
        mLlBtnDataMonth.setOnClickListener(this);
        setLeft();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_manager);
        SysExitUtil.activityList.add(this);
        toNext();
        assignViews();
        getData();
    }

    private void getData() {
        XUtils.xUtilsGet(ContactURL.GET_ORDERNUM + MainActivity.userid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "------GET_ORDERNUM------" + result);
                Log.d("mafuhua", "------GET_ORDERNUM------" + ContactURL.GET_ORDERNUM + MainActivity.userid);
                Gson gson = new Gson();
                orderNumBean = gson.fromJson(result, OrderNumBean.class);
                mTvDataOrderNum.setText(orderNumBean.getJin_num());
                mTvDataOrderBusinessMoney.setText(orderNumBean.getJin_price());
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
            case R.id.ll_btn_data_day:
                setLeft();
                mTvDataOrderNum.setText(orderNumBean.getJin_num());
                mTvDataOrderBusinessMoney.setText(orderNumBean.getJin_price());
                break;
            case R.id.ll_btn_data_week:
                setCenter();
                mTvDataOrderNum.setText(orderNumBean.getBz_num());
                mTvDataOrderBusinessMoney.setText(orderNumBean.getBz_price());
                break;
            case R.id.ll_btn_data_month:
                setRight();
                mTvDataOrderNum.setText(orderNumBean.getBy_num());
                mTvDataOrderBusinessMoney.setText(orderNumBean.getBy_price());
                break;

        }
    }

    private void setLeft() {
        tv_data_left.setTextColor(getResources().getColor(R.color.titlebar_bg));
        line_data_left.setBackgroundColor(getResources().getColor(R.color.titlebar_bg));
        tv_data_center.setTextColor(Color.BLACK);
        tv_data_right.setTextColor(Color.BLACK);
        line_data_right.setVisibility(View.GONE);
        line_data_center.setVisibility(View.GONE);
        line_data_left.setVisibility(View.VISIBLE);
        mTvDataOrder.setText("今日总订单");
        mTvDataOrderBusiness.setText("今日总营业额");
    }

    private void setCenter() {
        tv_data_center.setTextColor(getResources().getColor(R.color.titlebar_bg));
        line_data_center.setBackgroundColor(getResources().getColor(R.color.titlebar_bg));
        tv_data_left.setTextColor(Color.BLACK);
        tv_data_right.setTextColor(Color.BLACK);
        line_data_right.setVisibility(View.GONE);
        line_data_left.setVisibility(View.GONE);
        line_data_center.setVisibility(View.VISIBLE);
        mTvDataOrder.setText("本周总订单");
        mTvDataOrderBusiness.setText("本周总营业额");
    }

    private void setRight() {
        tv_data_right.setTextColor(getResources().getColor(R.color.titlebar_bg));
        line_data_right.setBackgroundColor(getResources().getColor(R.color.titlebar_bg));
        tv_data_center.setTextColor(Color.BLACK);
        tv_data_left.setTextColor(Color.BLACK);
        line_data_left.setVisibility(View.GONE);
        line_data_center.setVisibility(View.GONE);
        line_data_right.setVisibility(View.VISIBLE);
        mTvDataOrder.setText("本月总订单");
        mTvDataOrderBusiness.setText("本月总营业额");
    }


}
