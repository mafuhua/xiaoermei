package com.yuen.xiaoermei.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.bean.ShopDistanceBean;
import com.yuen.xiaoermei.bean.ShopFreightBean;
import com.yuen.xiaoermei.bean.ShopTimeBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        mTvUserName.setText(MainActivity.username);
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
        getShopTime();
        getShopDistance();
        getShopFreight();
    }

    public void getShopDistance() {
        RequestParams params = new RequestParams(ContactURL.SHOP_GET_DISTANCE + MainActivity.userid);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopDistanceBean shopDistanceBean = gson.fromJson(res, ShopDistanceBean.class);
                String shop_distance = shopDistanceBean.getData().getShop_distance();
                settingMap.put(3, shop_distance + "km");
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


    public void getShopFreight() {
        RequestParams params = new RequestParams(ContactURL.SHOP_GET_FREIGHT + MainActivity.userid);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // Log.d("mafuhua", "result"+result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopFreightBean shopFreightBean = gson.fromJson(res, ShopFreightBean.class);
                String store_freight = shopFreightBean.getData().getShop_freight();
                settingMap.put(4, store_freight + "元");
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

    public void getShopTime() {
        RequestParams params = new RequestParams(ContactURL.SHOP_TIME + MainActivity.userid);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //  Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopTimeBean shopTimeBean = gson.fromJson(res, ShopTimeBean.class);
                String starttime = shopTimeBean.getData().getShop_time();
                String endtime = shopTimeBean.getData().getShop_etime();
                settingMap.put(0, starttime + ":00 - " + endtime + ":00");
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
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        // editText.setHint(hint);
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String weight = editText.getText().toString().trim();
                if (position == 3) {
                    setAddDistance(weight);
                } else {
                    setAddFreight(weight);
                }
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
        builder.setMessage("请输入0-24之间的整数");
        View view = getLayoutInflater().inflate(R.layout.dialog_setting_time, null);
        final EditText et_input_start = (EditText) view.findViewById(R.id.et_input_start);
        final EditText et_input_end = (EditText) view.findViewById(R.id.et_input_end);
        builder.setView(view);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String etstart = et_input_start.getText().toString().trim();
                String etend = et_input_end.getText().toString().trim();
                if (!TextUtils.isEmpty(etstart) && !TextUtils.isEmpty(etend)) {
                    if (Integer.parseInt(etend, 10) > 24 || Integer.parseInt(etstart, 10) > 24) {
                        Toast.makeText(context, "时间不能大于24", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (Integer.parseInt(etend, 10) < 0 || Integer.parseInt(etstart, 10) < 0) {
                        Toast.makeText(context, "时间不能小于0", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        settingMap.put(0, etstart + ":00 - " + etend + ":00");
                        myAdapter.notifyDataSetChanged();
                        setAddTime(etstart, etend);
                    }
                } else {
                    Toast.makeText(context, "时间不能为空", Toast.LENGTH_SHORT).show();

                }


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

    private void setAddTime(String etstart, String etend) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("shop_etime", etend);
        map.put("shop_time", etstart);
        XUtils.xUtilsPost(ContactURL.SHOP_ADD_TIME, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());


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

    private void setAddDistance(String distance) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("shop_distance", distance);
        XUtils.xUtilsPost(ContactURL.SHOP_ADD_DISTANCE, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());


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

    private void setAddFreight(String freight) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("shop_freight", freight);
        XUtils.xUtilsPost(ContactURL.SHOP_ADD_FREIGHT, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //   Log.d("mafuhua", result.toString());


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
