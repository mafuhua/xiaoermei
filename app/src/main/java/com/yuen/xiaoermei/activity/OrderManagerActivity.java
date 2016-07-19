package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.baseclass.BaseHolder;
import com.yuen.xiaoermei.baseclass.DefaultAdapter;
import com.yuen.xiaoermei.bean.BaseBean;
import com.yuen.xiaoermei.bean.OrderListBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class OrderManagerActivity extends BaseActivity implements View.OnClickListener {
    private static int typepos = 0;
    private static List<Integer> typeposList = new ArrayList<>();
    private static int botpos = 0;
    private static List<Integer> botposList = new ArrayList<>();
    public boolean btnsend = false;
    int bot;
    private LinearLayout mLlBtnOrderUnsend;
    private LinearLayout mLlBtnOrderSend;
    private ListView mLvOrderList;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private MyAdapter myAdapter;
    private TextView tv_order_left;
    private View line_order_left;
    private TextView tv_order_right;
    private View line_order_right;
    private Context context;
    private List<OrderListBean.DataBean> orderListBeanData;
    private List<OrderListBean.DataBean.ProBean> orderList = new ArrayList<>();

    private void assignViews() {
        context = this;
        tv_order_left = (TextView) findViewById(R.id.tv_order_left);
        line_order_left = (View) findViewById(R.id.line_order_left);
        tv_order_right = (TextView) findViewById(R.id.tv_order_right);
        line_order_right = (View) findViewById(R.id.line_order_right);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLlBtnOrderUnsend = (LinearLayout) findViewById(R.id.ll_btn_order_unsend);
        mLlBtnOrderSend = (LinearLayout) findViewById(R.id.ll_btn_order_send);
        mLvOrderList = (ListView) findViewById(R.id.lv_order_list);
        mIvBtnAdd.setVisibility(View.GONE);
        line_order_right.setVisibility(View.GONE);
        mTvTitleDec.setText("订单管理");
        tv_order_left.setTextColor(getResources().getColor(R.color.titlebar_bg));
        line_order_left.setBackgroundColor(getResources().getColor(R.color.titlebar_bg));
        mLlBtnOrderUnsend.setOnClickListener(this);
        mLlBtnOrderSend.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manager);
        SysExitUtil.activityList.add(this);
        assignViews();
        toNext();
        btnsend = true;
        getOrderList("/type/2");

    }

    private void getOrderList(String type) {
        XUtils.xUtilsGet(ContactURL.GET_ORDER_LIST + MainActivity.userid + type, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "----GET_ORDER_LIST-----" + result);
                Log.d("mafuhua", "----GET_ORDER_LIST-----" + ContactURL.GET_ORDER_LIST + MainActivity.userid + "/type/2");

                if (orderListBeanData != null) {
                    orderListBeanData.clear();
                }
                botposList.clear();
                typeposList.clear();
                orderList.clear();
                Gson gson = new Gson();
                OrderListBean orderListBean = gson.fromJson(result, OrderListBean.class);
                if (orderListBean.getCode().equals("1")) {
                    Toast.makeText(OrderManagerActivity.this, "没有订单了", Toast.LENGTH_SHORT).show();
                    myAdapter.notifyDataSetChanged();
                } else {
                    orderListBeanData = orderListBean.getData();
                    typepos = 0;
                    typeposList.add(typepos);
                    for (int i = 0; i < orderListBeanData.size(); i++) {
                        OrderListBean.DataBean dataBean = orderListBeanData.get(i);
                        List<OrderListBean.DataBean.ProBean> proBeanList = dataBean.getPro();
                        typepos = typepos + proBeanList.size();
                        botposList.add(typepos - 1);
                        typeposList.add(typepos);
                        for (int j = 0; j < proBeanList.size(); j++) {
                            OrderListBean.DataBean.ProBean proBean = proBeanList.get(j);
                            orderList.add(proBean);
                            myAdapter = new MyAdapter(orderList);
                            mLvOrderList.setAdapter(myAdapter);
                        }
                    }
                }

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
            case R.id.ll_btn_order_unsend:
                btnsend = true;
                setUnSend();
                getOrderList("/type/2");
                break;
            case R.id.ll_btn_order_send:
                btnsend = false;
                setSend();
                getOrderList("/type/3");
                break;
        }
    }

    private void setUnSend() {
        tv_order_left.setTextColor(getResources().getColor(R.color.titlebar_bg));
        line_order_left.setBackgroundColor(getResources().getColor(R.color.titlebar_bg));
        tv_order_right.setTextColor(Color.BLACK);
        line_order_right.setVisibility(View.GONE);
        line_order_left.setVisibility(View.VISIBLE);
    }

    private void setSend() {
        tv_order_right.setTextColor(getResources().getColor(R.color.titlebar_bg));
        line_order_right.setBackgroundColor(getResources().getColor(R.color.titlebar_bg));
        tv_order_left.setTextColor(Color.BLACK);
        line_order_left.setVisibility(View.GONE);
        line_order_right.setVisibility(View.VISIBLE);
    }

    private void sendOrder(String order_id) {
        Log.d("mafuhua", "----SEND_ORDER-----" + ContactURL.SEND_ORDER + order_id);
        XUtils.xUtilsGet(ContactURL.SEND_ORDER + order_id, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "----SEND_ORDER-----" + result);

                Gson gson = new Gson();
                BaseBean baseBean = gson.fromJson(result, BaseBean.class);
                Toast.makeText(OrderManagerActivity.this, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
                getOrderList("/type/2");

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

    class MyAdapter extends DefaultAdapter {
        public MyAdapter(List datas) {
            super(datas);
        }

        @Override
        public BaseHolder getHolder() {
            return new ViewHolder();
        }
    }

    public class ViewHolder extends BaseHolder<OrderListBean.DataBean.ProBean> {
        public TextView tvorderlistunsend;
        public TextView tvorderlisttime;
        public ImageView ivordershopimage;
        public TextView tvorderlistshopname;
        public TextView tvorderlistclass;
        public TextView tvorderlistclassname;
        public TextView tvorderlistpresentprice;
        public TextView tvorderlistcurrentprice;
        public TextView tvorderlistcount;
        public TextView tvorderlistpeople;
        public TextView tvorderlistpeoplename;
        public TextView tvorderlistphone;
        public TextView tvorderlistaddress;
        public TextView tvorderlistpeopleaddress;
        public LinearLayout lv_bottom;
        public RelativeLayout lv_title;
        public RelativeLayout add;
        public Button btn_send;
        public LinearLayout hhr;

        OrderListBean.DataBean dataBean;

        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_order_list_item, null);
            tvorderlistunsend = (TextView) root.findViewById(R.id.tv_order_list_unsend);
            lv_bottom = (LinearLayout) root.findViewById(R.id.lv_bottom);
            lv_title = (RelativeLayout) root.findViewById(R.id.lv_title);
            btn_send = (Button) root.findViewById(R.id.btn_send);
            hhr = (LinearLayout) root.findViewById(R.id.hhr);
            tvorderlisttime = (TextView) root.findViewById(R.id.tv_order_list_time);
            ivordershopimage = (ImageView) root.findViewById(R.id.iv_order_shop_image);
            tvorderlistshopname = (TextView) root.findViewById(R.id.tv_order_list_shopname);
            tvorderlistclass = (TextView) root.findViewById(R.id.tv_order_list_class);
            tvorderlistclassname = (TextView) root.findViewById(R.id.tv_order_list_classname);
            tvorderlistpresentprice = (TextView) root.findViewById(R.id.tv_order_list_presentprice);
            tvorderlistcurrentprice = (TextView) root.findViewById(R.id.tv_order_list_currentprice);
            tvorderlistcount = (TextView) root.findViewById(R.id.tv_order_list_count);
            tvorderlistpeople = (TextView) root.findViewById(R.id.tv_order_list_people);
            tvorderlistpeoplename = (TextView) root.findViewById(R.id.tv_order_list_peoplename);
            tvorderlistphone = (TextView) root.findViewById(R.id.tv_order_list_phone);
            tvorderlistaddress = (TextView) root.findViewById(R.id.tv_order_list_address);
            tvorderlistpeopleaddress = (TextView) root.findViewById(R.id.tv_order_list_peopleaddress);

            return root;
        }

        @Override
        public void refreshView(OrderListBean.DataBean.ProBean data, int position) {
            tvorderlistshopname.setText(data.getName());
            tvorderlistcount.setText(data.getNum());
            tvorderlistpresentprice.setText(data.getPrice());
            x.image().bind(ivordershopimage, data.getImage());
            Log.d("mafuhua", "typeposList:" + typeposList);
            Log.d("mafuhua", "botposList:" + botposList);
            if (typeposList.contains(position)) {
                int i = typeposList.indexOf(position);
                dataBean = orderListBeanData.get(i);
                tvorderlisttime.setText(dataBean.getTime());
                tvorderlistunsend.setText("订单号:" + dataBean.getOrder_id());
                lv_bottom.setVisibility(View.VISIBLE);
            } else {
                lv_bottom.setVisibility(View.GONE);
            }

            if (botposList.contains(position)) {
                int i = botposList.indexOf(position);
                Log.d("mafuhua", "position---------:" + (position));
                dataBean = orderListBeanData.get(i);
                tvorderlistpeoplename.setText(dataBean.getName());
                tvorderlistphone.setText(dataBean.getTel());
                tvorderlistpeopleaddress.setText(dataBean.getAdd() + dataBean.getAdds());
                hhr.setVisibility(View.VISIBLE);
            } else {
                hhr.setVisibility(View.GONE);
            }
            if (btnsend){
                btn_send.setVisibility(View.VISIBLE);
            }else {
                btn_send.setVisibility(View.GONE);
            }
            btn_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendOrder(dataBean.getOrder_id());
                }

            });
        }
    }
}
