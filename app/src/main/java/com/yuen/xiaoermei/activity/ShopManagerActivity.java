package com.yuen.xiaoermei.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;

import java.util.HashMap;

import xlkd.provinceslinkage.ProvinceLinkActivity;

/**
 * 店铺管理
 */
public class ShopManagerActivity extends BaseActivity {
    private String[] shopItemString = new String[]{"营业时间", "店铺公告", "店铺地址", "配送距离", "配送费", "接单手机号"};
    private ListView mLvShopManager;
    private LinearLayout mLayoutTitleUsericon;
    private ImageView mIvUserIcon;
    private TextView mTvUserName;
    private MyAdapter myAdapter;
    private Context context;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;

    private SharedPreferences sharedPreferences;
    private HashMap<Integer, String> settingMap = new HashMap<>();

    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mLayoutTitleUsericon = (LinearLayout) findViewById(R.id.layout_title_usericon);
        mIvUserIcon = (ImageView) findViewById(R.id.iv_user_icon);
        mTvUserName = (TextView) findViewById(R.id.tv_user_name);
        mLvShopManager = (ListView) findViewById(R.id.lv_shop_manager);
        mTvTitleDec.setText("店铺管理");
        mIvBtnAdd.setVisibility(View.GONE);
        mTvUserName.setText(username);
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        myAdapter = new MyAdapter();
        mLvShopManager.setAdapter(myAdapter);
        mLvShopManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private Intent intent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showSettingTimeDialog();
                        break;
                    case 1:
                        intent = new Intent(context, ShopNoticeActivity.class);
                        context.startActivity(intent);

                        break;
                    case 2:
                        intent = new Intent(context, ProvinceLinkActivity.class);
                        context.startActivity(intent);
                        break;
                    case 3:
                        settingShopManger(position, "配送距离", "公里");
                        break;
                    case 4:
                        settingShopManger(position, "配送费", "元");
                        break;
                    case 5:
                        intent = new Intent(context, RecievePhoneListActivity.class);
                        context.startActivity(intent);
                        break;

                }
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_manager);
        toNext();
        assignViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void settingShopManger(final int position, String str, final String hint) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请输入" + str);
        final EditText editText = new EditText(context);
        // editText.setHint(hint);
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String weight = editText.getText().toString().trim();
                settingMap.put(position, weight + hint);
                myAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSettingTimeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请输入营业时间");
        View view = getLayoutInflater().inflate(R.layout.dialog_setting_time, null);
        final EditText et_input_start = (EditText) view.findViewById(R.id.et_input_start);
        final EditText et_input_end = (EditText) view.findViewById(R.id.et_input_end);
        builder.setView(view);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etstart = et_input_start.getText().toString().trim();
                String etend = et_input_end.getText().toString().trim();
                settingMap.put(0, etstart + " - " + etend);
                myAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return shopItemString.length;
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
                convertView = View.inflate(context, R.layout.layout_shop_manager_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvshopmanagerleft.setText(shopItemString[position]);
            viewHolder.tvshopmanagerright.setText(settingMap.get(position));
            viewHolder.ivShopItemImage.setBackgroundResource(R.drawable.iconfontjiantu2x);
            return convertView;
        }

        public class ViewHolder {
            public final TextView tvshopmanagerleft;
            public final TextView tvshopmanagerright;
            public final ImageView ivShopItemImage;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                tvshopmanagerleft = (TextView) root.findViewById(R.id.tv_shop_manager_left);
                ivShopItemImage = (ImageView) root.findViewById(R.id.iv_shop_item_image);
                tvshopmanagerright = (TextView) root.findViewById(R.id.tv_shop_manager_right);
            }
        }
    }
}
