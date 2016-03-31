package com.yuen.xiaoermei.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.ShopPhoneNumBean;
import com.yuen.xiaoermei.utils.ContactURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class RecievePhoneListActivity extends AppCompatActivity {

    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private ListView mLvRecievePhoneList;
    private MyAdapter myAdapter;
    private Context context;
    private List<String> phonelist = new ArrayList<>();
    private List<String> phoneIDlist = new ArrayList<>();

    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mTvTitleDec.setText("接单手机号");
        mLvRecievePhoneList = (ListView) findViewById(R.id.lv_recieve_phone_list);
        myAdapter = new MyAdapter();
        mLvRecievePhoneList.setAdapter(myAdapter);
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, RecievePhoneActivity.class));
            }
        });
        mLvRecievePhoneList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("确认删除此号码吗？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        phonelist.remove(position);
                        String telid = phoneIDlist.get(position);
                        delShopPhoneNum(telid);
                        // myAdapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_phone_list);
        assignViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        phonelist.clear();
        phoneIDlist.clear();
        getShopPhoneNum();
    }

    public void getShopPhoneNum() {
        RequestParams params = new RequestParams(ContactURL.SHOP_GET_PHONENUM + MainActivity.userid);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopPhoneNumBean shopPhoneNumBean = gson.fromJson(res, ShopPhoneNumBean.class);
                List<ShopPhoneNumBean.DataBean> shopPhoneNumBeanData = shopPhoneNumBean.getData();
                for (int i = 0; i < shopPhoneNumBeanData.size(); i++) {

                    ShopPhoneNumBean.DataBean dataBean = shopPhoneNumBeanData.get(i);
                    String tel = dataBean.getTel();
                    String telid = dataBean.getId();
                    phonelist.add(tel);
                    phoneIDlist.add(telid);
                }

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("mafuhua", isOnCallback + "");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public void delShopPhoneNum(String id) {
        RequestParams params = new RequestParams(ContactURL.SHOP_DEL_PHONENUM + id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();

                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("mafuhua", isOnCallback + "");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return phonelist.size();
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
                convertView = View.inflate(context, R.layout.layout_reciever_phonelist_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.ivbtnrecievephonenum.setText(phonelist.get(position));


            return convertView;
        }

        public class ViewHolder {
            public final TextView ivbtnrecievephonenum;
            public final ImageView ivbtnrecievedeletephone;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                ivbtnrecievephonenum = (TextView) root.findViewById(R.id.iv_btn_recieve_phonenum);
                ivbtnrecievedeletephone = (ImageView) root.findViewById(R.id.iv_btn_recieve_deletephone);
            }
        }
    }
}
