package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.ShopBrandBean;
import com.yuen.xiaoermei.bean.ShopTypeBean;
import com.yuen.xiaoermei.utils.ContactURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import zhy.imageloader.SelectorImageActivity;

/**
 * 商品详情编辑
 */
public class CommodityEditDecActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] ShopBrand;
    private String[] ShopType;
    private EditText mEtProductName;
    private Spinner spinner0;
    private EditText mEtProductPrice;
    private EditText mEtProductActivePrice;
    private EditText mEtProductInventory;
    private EditText mEtProductWeight;
    private EditText mEtProductAddress;
    private EditText mEtProductSize;
    private EditText mEtProductColor;
    private EditText mEtProductVolume;
    private EditText mEtProductDec;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private ImageView mIvBtnProductTiJiao;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private ArrayList<String> mySelectedImage;
    private ImageOptions options;
    private GridView mGvSelectorImage;
    private ImageView mIvBtnAddSelectorImage;
    private List<String> ShopTypeFirstIDList1;
    private List<String> ShopTypeFirstIDList2;
    private List<String> ShopTypeFirstIDList3;
    private List<String> ShopBrandList;
    private int type;
    private CheckBox mCbCommodityUp;
    private CheckBox mCbCommodityDown;

    private void assignViews() {
        context = this;
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mCbCommodityUp = (CheckBox) findViewById(R.id.cb_commodity_up);
        mCbCommodityDown = (CheckBox) findViewById(R.id.cb_commodity_down);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mIvBtnProductTiJiao = (ImageView) findViewById(R.id.iv_btn_product_tijiao);
        mEtProductName = (EditText) findViewById(R.id.et_product_name);
        mEtProductPrice = (EditText) findViewById(R.id.et_product_price);
        mEtProductActivePrice = (EditText) findViewById(R.id.et_product_active_price);
        mEtProductInventory = (EditText) findViewById(R.id.et_product_inventory);
        mEtProductWeight = (EditText) findViewById(R.id.et_product_weight);
        mEtProductAddress = (EditText) findViewById(R.id.et_product_address);
        mEtProductSize = (EditText) findViewById(R.id.et_product_size);
        mEtProductColor = (EditText) findViewById(R.id.et_product_color);
        mEtProductVolume = (EditText) findViewById(R.id.et_product_volume);
        mEtProductDec = (EditText) findViewById(R.id.et_product_dec);
        mGvSelectorImage = (GridView) findViewById(R.id.gv_selector_image);
        mIvBtnAddSelectorImage = (ImageView) findViewById(R.id.iv_btn_add_selector_image);
        mEtProductDec.setText("商品详情");
        mIvBtnAdd.setVisibility(View.GONE);
        mIvBtnBack.setOnClickListener(this);
        mIvBtnAddSelectorImage.setOnClickListener(this);
        mCbCommodityUp.setOnClickListener(this);
        mCbCommodityDown.setOnClickListener(this);
        mIvBtnProductTiJiao.setOnClickListener(this);
        spinner0 = (Spinner) findViewById(R.id.spinner0);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner2.setVisibility(View.GONE);
        spinner3.setVisibility(View.GONE);
        spinner0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) return;
                Log.d("mafuhua",ShopBrandList.get(pos - 1) + "你点击的是:" + pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) return;
                Log.d("mafuhua", ContactURL.SHOP_TYPE + ShopTypeFirstIDList1.get(pos - 1) + "你点击的是:" + pos);
                getSHOP_TYPE(ContactURL.SHOP_TYPE + ShopTypeFirstIDList1.get(pos - 1), 1);
                spinner2.setVisibility(View.VISIBLE);
                spinner3.setVisibility(View.GONE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) return;
                Log.d("mafuhua", ContactURL.SHOP_TYPE + ShopTypeFirstIDList2.get(pos - 1) + "你点击的是:" + pos);
                spinner3.setVisibility(View.VISIBLE);
                getSHOP_TYPE(ContactURL.SHOP_TYPE + ShopTypeFirstIDList2.get(pos - 1), 2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos == 0) return;
                Log.d("mafuhua", ContactURL.SHOP_TYPE + ShopTypeFirstIDList3.get(pos - 1) + "你点击的是:" + pos);
                getSHOP_TYPE(ContactURL.SHOP_TYPE + ShopTypeFirstIDList3.get(pos - 1), 3);
                Toast.makeText(context, ShopTypeFirstIDList3.get(pos - 1) + "id", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        options = new ImageOptions.Builder()
                // 是否忽略GIF格式的图片
                .setIgnoreGif(true)
                        // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                        // 下载中显示的图片
                .setLoadingDrawableId(R.drawable.ic_launcher)
                        // 下载失败显示的图片
                .setFailureDrawableId(R.drawable.ic_launcher)
                        // 得到ImageOptions对象
                .build();

    }

    private void setSpinnerContent(final Spinner spinners, final String[] mCountries) {
        if (mCountries == null) {
            return;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, mCountries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinners.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_edit_dec);
        assignViews();
        getSHOP_BRAND();
        getSHOP_TYPE(ContactURL.SHOP_TYPE + "0", 0);
    }

    private void getSHOP_BRAND() {
        RequestParams params = new RequestParams(ContactURL.SHOP_BRAND);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
               // Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopBrandBean shopBrandBean = gson.fromJson(res, ShopBrandBean.class);
                List<ShopBrandBean.DataBean> data = shopBrandBean.getData();
                ShopBrand = new String[data.size()+1];
                ShopBrand[0] = "请选择";
                ShopBrandList = new ArrayList<String>();
                for (int i = 1; i < data.size()+1; i++) {
                    ShopBrand[i] = data.get(i-1).getBrand();
                    ShopBrandList.add(i-1,data.get(i-1).getBrand());
                }
                setSpinnerContent(spinner0, ShopBrand);
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

    private void getSHOP_TYPE(String url, final int spinnerpos) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
              //  Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                ShopTypeBean shopTypeBean = gson.fromJson(res, ShopTypeBean.class);
                List<ShopTypeBean.DataBean> data = shopTypeBean.getData();

                if (spinnerpos == 0) {
                    ShopTypeFirstIDList1 = new ArrayList<String>();
                } else if (spinnerpos == 1) {
                    ShopTypeFirstIDList2 = new ArrayList<String>();
                } else if (spinnerpos == 2) {
                    ShopTypeFirstIDList3 = new ArrayList<String>();
                }
                ShopType = new String[data.size() + 1];
                ShopType[0] = "请选择";
                for (int i = 1; i <= data.size(); i++) {
                    ShopType[i] = data.get(i - 1).getNav_name();

                    if (spinnerpos == 0) {
                        ShopTypeFirstIDList1.add(i - 1, data.get(i - 1).getId());

                    } else if (spinnerpos == 1) {
                        ShopTypeFirstIDList2.add(i - 1, data.get(i - 1).getId());

                    } else if (spinnerpos == 2) {
                        ShopTypeFirstIDList3.add(i - 1, data.get(i - 1).getId());
                    }

                }
                if (spinnerpos == 0) {

                    setSpinnerContent(spinner1, ShopType);
                } else if (spinnerpos == 1) {
                    setSpinnerContent(spinner2, ShopType);
                } else if (spinnerpos == 2) {
                    setSpinnerContent(spinner3, ShopType);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (resultCode == 100) {
            mySelectedImage = data.getStringArrayListExtra("MySelectedImage");
            Log.d("mafuhua", "7777777777777****" + mySelectedImage.size());
            mGvSelectorImage.setAdapter(new MyAdapter());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;
            case R.id.cb_commodity_up:
                mCbCommodityUp.setChecked(true);
                mCbCommodityDown.setChecked(false);
                Toast.makeText(context, "上架", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cb_commodity_down:
                mCbCommodityUp.setChecked(false);
                mCbCommodityDown.setChecked(true);
                Toast.makeText(context, "下架", Toast.LENGTH_SHORT).show();

                break;
            case R.id.iv_btn_product_tijiao:
                finish();
                break;
            case R.id.iv_btn_add_selector_image:
                Intent intent = new Intent(context, SelectorImageActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mySelectedImage == null ? 0 : mySelectedImage.size();
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
                convertView = View.inflate(context, R.layout.layout_selector_gridview, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            String imag = mySelectedImage.get(position);
            if (imag != null) {

                x.image().bind(viewHolder.iditemimage, new File(imag).toURI().toString(), options);
            }
            return convertView;
        }

        public class ViewHolder {
            public final ImageView iditemimage;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                iditemimage = (ImageView) root.findViewById(R.id.iv_grid_item_image);
            }
        }
    }

}
