package xlkd.provinceslinkage;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.activity.MainActivity;
import com.yuen.xiaoermei.bean.ShopAddressBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;

import xlkd.util.Util;

public class ProvinceLinkActivity extends Util {

    private Spinner provinceSpinner;
    private Spinner citySpinner;
    private Spinner districtSpinner;
    private TextView tv_result;
    private Context context;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private EditText mEtShopManagerAddress;
    private ImageView mIvBtnAdd;
    private TextView mTvShopAddress;
    private Button mBtnShopQuedingAddress;


    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mBtnShopQuedingAddress = (Button) findViewById(R.id.btn_shop_queding_address);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mTvShopAddress = (TextView) findViewById(R.id.tv_shop_address);
        mEtShopManagerAddress = (EditText) findViewById(R.id.et_shop_manager_address);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mTvTitleDec.setText("店铺地址");
        mIvBtnAdd.setVisibility(View.GONE);
        mBtnShopQuedingAddress.setTextColor(Color.WHITE);
        mBtnShopQuedingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddAddress();
            }
        });
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provincelink);
        initView();
        assignViews();
        getShopAddress();

    }

    public void getShopAddress() {
        RequestParams params = new RequestParams(ContactURL.SHOP_GET_ADDRESS + MainActivity.userid);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopAddressBean shopAddressBean = gson.fromJson(res, ShopAddressBean.class);
                String shop_address = shopAddressBean.getData();
                if (shop_address != null) {
                    mTvShopAddress.setText("店铺地址:"+shop_address);
                }
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
    private String city;
    private void setAddAddress() {
        if (city.contains("请选择")) {
            Toast.makeText(context, "省市区必须选择", Toast.LENGTH_SHORT).show();
            return;
        }
        String mEtShopManagerAddress = this.mEtShopManagerAddress.getText().toString().trim();
        if (TextUtils.isEmpty(mEtShopManagerAddress)){
            Toast.makeText(context, "详细地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("city", city);
        map.put("adds", mEtShopManagerAddress);
        XUtils.xUtilsPost(ContactURL.SHOP_ADD_ADDRESS, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopAddressBean shopAddressBean = gson.fromJson(res, ShopAddressBean.class);
                String shop_address = shopAddressBean.getData();
                if (shop_address != null) {

                    mTvShopAddress.setText("店铺地址:"+shop_address);

                }
                finish();

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

    private void initView() {
        provinceSpinner = (Spinner) this.findViewById(R.id.province);
        citySpinner = (Spinner) this.findViewById(R.id.city);
        districtSpinner = (Spinner) this.findViewById(R.id.district);
        tv_result = (TextView) this.findViewById(R.id.txt);
        initProvinceDatas();
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mProvinceDatas);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mCurrentProviceName = mProvinceDatas[position];
                String[] cities = mCitisDatasMap.get(mCurrentProviceName);
                if (cities == null) {
                    cities = new String[]{""};
                }
                ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, cities);
                citySpinner.setAdapter(cityAdapter);
                mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[0];
                String[] areas = mDistrictDatasMap.get(mCurrentCityName);
                mCurrentDistrictName = areas[0];
                if (areas == null) {
                    areas = new String[]{""};
                }
                ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, areas);
                districtSpinner.setAdapter(districtAdapter);
                tv_result.setText(mCurrentProviceName + "," + mCurrentCityName + "," + mCurrentDistrictName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[position];
                String[] areas = mDistrictDatasMap.get(mCurrentCityName);
                mCurrentDistrictName = areas[0];
                if (areas == null) {
                    areas = new String[]{""};
                }
                ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, areas);
                districtSpinner.setAdapter(districtAdapter);
                tv_result.setText(mCurrentProviceName + "," + mCurrentCityName + "," + mCurrentDistrictName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner sp = (Spinner) parent;
                mCurrentDistrictName = (String) sp.getItemAtPosition(position);
                tv_result.setText(mCurrentProviceName + "," + mCurrentCityName + "," + mCurrentDistrictName);
                city = mCurrentProviceName + "," + mCurrentCityName + "," + mCurrentDistrictName;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
