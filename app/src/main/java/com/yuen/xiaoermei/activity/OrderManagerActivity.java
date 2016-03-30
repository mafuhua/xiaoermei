package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;

public class OrderManagerActivity extends BaseActivity implements View.OnClickListener {
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
        tv_order_left.setTextColor(getResources().getColor(R.color.titlebar_bg));
        line_order_left.setBackgroundColor(getResources().getColor(R.color.titlebar_bg));
        mLlBtnOrderUnsend.setOnClickListener(this);
        mLlBtnOrderSend.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
        myAdapter = new MyAdapter();
        mLvOrderList.setAdapter(myAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manager);
        assignViews();
        toNext();
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
                setUnSend();
                break;
            case R.id.ll_btn_order_send:
                setSend();
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

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.layout_order_list_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        public class ViewHolder {
            public final TextView tvorderlistunsend;
            public final TextView tvorderlisttime;
            public final ImageView ivordershopimage;
            public final TextView tvorderlistshopname;
            public final TextView tvorderlistclass;
            public final TextView tvorderlistclassname;
            public final TextView tvorderlistpresentprice;
            public final TextView tvorderlistcurrentprice;
            public final TextView tvorderlistcount;
            public final TextView tvorderlistpeople;
            public final TextView tvorderlistpeoplename;
            public final TextView tvorderlistphone;
            public final TextView tvorderlistaddress;
            public final TextView tvorderlistpeopleaddress;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                tvorderlistunsend = (TextView) root.findViewById(R.id.tv_order_list_unsend);
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
            }
        }
    }
}
