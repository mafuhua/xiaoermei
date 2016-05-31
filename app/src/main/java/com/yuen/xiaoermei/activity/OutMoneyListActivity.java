package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseHolder;
import com.yuen.xiaoermei.baseclass.DefaultAdapter;
import com.yuen.xiaoermei.bean.OutMoneyListBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

import java.util.List;

public class OutMoneyListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private TextView tv_tixian;
    private LinearLayout layout_title_bar;
    private ListView lv_outmoney_list;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_money_list);
        context = this;
        getOutMoneyList();
        initView();
    }

    private void getOutMoneyList() {
        XUtils.xUtilsGet(ContactURL.OUT_MONEYLIST+MainActivity.userid, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "----OUT_MONEYLIST----" + result);
                Gson gson = new Gson();
                OutMoneyListBean outMoneyListBean = gson.fromJson(result, OutMoneyListBean.class);
                List<OutMoneyListBean.DataBean> outMoneyListBeanData = outMoneyListBean.getData();
                lv_outmoney_list.setAdapter(new MyAdapter(outMoneyListBeanData));
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

    private void initView() {

        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
        tv_title_dec = (TextView) findViewById(R.id.tv_title_dec);
        iv_btn_add = (ImageView) findViewById(R.id.iv_btn_add);
        tv_tixian = (TextView) findViewById(R.id.tv_tixian);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        lv_outmoney_list = (ListView) findViewById(R.id.lv_outmoney_list);
        tv_title_dec.setText("提现记录");
        iv_btn_add.setVisibility(View.GONE);
        tv_tixian.setVisibility(View.VISIBLE);
        iv_btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;
        }
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

    public class ViewHolder extends BaseHolder <OutMoneyListBean.DataBean>{
        public TextView tvcontent;
        public TextView tvtime;
        public TextView tv_type;

        @Override
        public View initView() {
            View root = View.inflate(context, R.layout.layout_outmoney_list_item, null);
            tvcontent = (TextView) root.findViewById(R.id.tv_content);
            tv_type = (TextView) root.findViewById(R.id.tv_type);
            tvtime = (TextView) root.findViewById(R.id.tv_time);
            return root;
        }

        @Override
        public void refreshView(OutMoneyListBean.DataBean data, int position) {
            tvcontent.setText(data.getName()+"提现了"+data.getPrice());
            tvtime.setText(data.getTime());
            if (data.getType().equals("1")) {
                tv_type.setText("提款中");
            } else if (data.getType().equals("2")) {
                tv_type.setText("提款成功");
            }else if (data.getType().equals("3")){
                tv_type.setText("提款失败");
            }
        }
    }
}
