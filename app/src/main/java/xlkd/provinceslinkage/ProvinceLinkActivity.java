package xlkd.provinceslinkage;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.yuen.xiaoermei.R;

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



	private void assignViews() {
		context = this;
		mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
		mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
		mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
		mEtShopManagerAddress = (EditText) findViewById(R.id.et_shop_manager_address);
		mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
		mTvTitleDec.setText("店铺地址");
		mIvBtnAdd.setVisibility(View.GONE);
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
					cities = new String[] { "" };
				}
				ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, cities);
				citySpinner.setAdapter(cityAdapter);
				mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[0];
				String[] areas = mDistrictDatasMap.get(mCurrentCityName);
				mCurrentDistrictName = areas[0];
				if (areas == null) {
					areas = new String[] { "" };
				}
				ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, areas);
				districtSpinner.setAdapter(districtAdapter);
				tv_result.setText(mCurrentProviceName+","+mCurrentCityName+","+mCurrentDistrictName);
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
					areas = new String[] { "" };
				}
				ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(ProvinceLinkActivity.this, android.R.layout.simple_list_item_1, areas);
				districtSpinner.setAdapter(districtAdapter);
				tv_result.setText(mCurrentProviceName+","+mCurrentCityName+","+mCurrentDistrictName);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
    	districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner sp = (Spinner)parent;
				mCurrentDistrictName = (String) sp.getItemAtPosition(position);
				tv_result.setText(mCurrentProviceName+","+mCurrentCityName+","+mCurrentDistrictName);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});
	}
}
