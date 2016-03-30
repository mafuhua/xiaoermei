package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.ShopNoticeBean;
import com.yuen.xiaoermei.utils.ContactURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 开店须知
 */
public class OpenShopNoticeActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLlOpenShopnoticeLeft;
    private View mLineOpenShopnoticeLeft;
    private LinearLayout mLlOpenShopnoticeRight;
    private View mLineOpenShopnoticeRight;
    private ListView mLvOpenShopnoticeContent;
    private MyAdapter myAdapter;
    private Context context;
    private TextView mTvOpenShopNoticeRight;
    private TextView mTvOpenShopNoticeLeft;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private List<String> LeftQuesList;
    private List<String> LeftAnList;
    private List<String> RightQuesList;
    private List<String> RightAnList;
    private void getLeftNotice(){
        RequestParams params = new RequestParams(ContactURL.SHOP_NOTICE);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopNoticeBean shopNoticeBean = gson.fromJson(res, ShopNoticeBean.class);
                List<ShopNoticeBean.DataBean> data = shopNoticeBean.getData();
                LeftQuesList = new ArrayList<String>();
                LeftAnList = new ArrayList<String>();
                for (int i = 0; i < data.size(); i++) {
                    LeftQuesList.add(i, data.get(i).getQ());
                    LeftAnList.add(i, data.get(i).getA());
                }
                myAdapter = new MyAdapter(LeftQuesList,LeftAnList);
                mLvOpenShopnoticeContent.setAdapter(myAdapter);
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
    private void getRightNotice()
    {
        RequestParams params = new RequestParams(ContactURL.SHOP_DISTRIBUTION);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopNoticeBean shopNoticeBean = gson.fromJson(res, ShopNoticeBean.class);
                List<ShopNoticeBean.DataBean> data = shopNoticeBean.getData();
                RightAnList = new ArrayList<String>();
                RightQuesList = new ArrayList<String>();
                for (int i = 0; i < data.size(); i++) {
                    RightAnList.add(i, data.get(i).getQ());
                    RightQuesList.add(i, data.get(i).getA());
                }
                myAdapter = new MyAdapter(RightQuesList,RightAnList);
                mLvOpenShopnoticeContent.setAdapter(myAdapter);
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

    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLlOpenShopnoticeLeft = (LinearLayout) findViewById(R.id.ll_open_shopnotice_left);
        mLineOpenShopnoticeLeft = findViewById(R.id.line_open_shopnotice_left);
        mLlOpenShopnoticeRight = (LinearLayout) findViewById(R.id.ll_open_shopnotice_right);
        mLineOpenShopnoticeRight = findViewById(R.id.line_open_shopnotice_right);
        mLvOpenShopnoticeContent = (ListView) findViewById(R.id.lv_open_shopnotice_content);
        mTvOpenShopNoticeRight = (TextView) findViewById(R.id.tv_open_shopnotice_right);
        mTvOpenShopNoticeLeft = (TextView) findViewById(R.id.tv_open_shopnotice_left);
        mLlOpenShopnoticeLeft.setOnClickListener(this);
        mLlOpenShopnoticeRight.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
        mLineOpenShopnoticeRight.setVisibility(View.GONE);
        mIvBtnAdd.setVisibility(View.GONE);
        mTvTitleDec.setText("开店须知");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_shop_notice);
        assignViews();
        getLeftNotice();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;

            case R.id.ll_open_shopnotice_left:
                setLeft();
                getLeftNotice();
                break;
            case R.id.ll_open_shopnotice_right:
                setRight();
                getRightNotice();
                break;
        }
    }

    private void setRight() {
        mTvOpenShopNoticeLeft.setTextColor(Color.BLACK);
        mLineOpenShopnoticeRight.setVisibility(View.VISIBLE);
        mTvOpenShopNoticeRight.setTextColor(getResources().getColor(R.color.titlebar_bg));
        mLineOpenShopnoticeLeft.setVisibility(View.GONE);
    }

    private void setLeft() {
        mTvOpenShopNoticeRight.setTextColor(Color.BLACK);
        mTvOpenShopNoticeLeft.setTextColor(getResources().getColor(R.color.titlebar_bg));
        mLineOpenShopnoticeLeft.setVisibility(View.VISIBLE);
        mLineOpenShopnoticeRight.setVisibility(View.GONE);
    }

    class MyAdapter extends BaseAdapter {


        private List<String> qlist;
        private List<String> alist;

        public MyAdapter(List<String> qlist,List<String> alist) {


            this.qlist = qlist;
            this.alist = alist;
        }

        @Override
        public int getCount() {
            return qlist.size();
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
                convertView = View.inflate(context, R.layout.layout_opennotice_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvopennoticequestion.setText(qlist.get(position));
            viewHolder.tvopennoticerequest.setText(alist.get(position));
            return convertView;
        }

        public class ViewHolder {
            public final TextView tvopennoticequestion;
            public final TextView tvopennoticerequest;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                tvopennoticequestion = (TextView) root.findViewById(R.id.tv_opennotice_question);
                tvopennoticerequest = (TextView) root.findViewById(R.id.tv_opennotice_request);
            }
        }
    }

}
