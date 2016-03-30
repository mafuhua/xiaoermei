package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.CommodityDecBean;
import com.yuen.xiaoermei.utils.ContactURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品描述
 */
public class CommodityDecActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mVpCommodityDec;
    private TextView mTvCommodityDec;
    private GridView mGvCommoditydec;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private MyAdapter adapter;
    private List<String> commodityDecKey = new ArrayList<>();
    private List<String> commodityDecValue = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;

    private void assignViews() {
        context = this;
        mVpCommodityDec = (ViewPager) findViewById(R.id.vp_commodity_dec);
        mTvCommodityDec = (TextView) findViewById(R.id.tv_commodity_dec);
        mGvCommoditydec = (GridView) findViewById(R.id.gv_commoditydec);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mTvTitleDec.setText("商品详情");
        mIvBtnAdd.setBackgroundResource(R.drawable.iconfontbianji2x);
        myPagerAdapter = new MyPagerAdapter();
        mVpCommodityDec.setAdapter(myPagerAdapter);
        mGvCommoditydec.setAdapter(adapter);
        mIvBtnAdd.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_dec);
        assignViews();
        getCommodityList();
    }

    public void getCommodityList() {
        RequestParams params = new RequestParams(ContactURL.COMMODITY_DEC);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                CommodityDecBean commodityDecBean = gson.fromJson(res, CommodityDecBean.class);
                CommodityDecBean.DataBean commodityDecBeanData = commodityDecBean.getData();
                String pro_color = commodityDecBeanData.getPro_color();
                String pro_h_price = commodityDecBeanData.getPro_h_price();
                String pro_img = commodityDecBeanData.getPro_img();
                String pro_inventory = commodityDecBeanData.getPro_inventory();
                String pro_kg = commodityDecBeanData.getPro_kg();
                String pro_ml = commodityDecBeanData.getPro_ml();
                String pro_name = commodityDecBeanData.getPro_name();
                String pro_origin = commodityDecBeanData.getPro_origin();
                String pro_price = commodityDecBeanData.getPro_price();
                String pro_size = commodityDecBeanData.getPro_size();
                String pro_taste = commodityDecBeanData.getPro_taste();
                mTvCommodityDec.setText(pro_name);
                if (pro_color.length() > 0) {
                    commodityDecKey.add("颜色:");
                    commodityDecValue.add(pro_color);
                }
                if (pro_h_price.length() > 0) {
                    commodityDecKey.add("活动价:");
                    commodityDecValue.add(pro_h_price);
                }
                if (pro_price.length() > 0) {
                    commodityDecKey.add("原价:");
                    commodityDecValue.add(pro_price);
                }
                if (pro_inventory.length() > 0) {
                    commodityDecKey.add("库存:");
                    commodityDecValue.add(pro_inventory);
                }
                if (pro_kg.length() > 0) {
                    commodityDecKey.add("重量:");
                    commodityDecValue.add(pro_kg);
                }
                if (pro_ml.length() > 0) {
                    commodityDecKey.add("体积:");
                    commodityDecValue.add(pro_ml);
                }
                if (pro_origin.length() > 0) {
                    commodityDecKey.add("产地:");
                    commodityDecValue.add(pro_origin);
                }

                if (pro_size.length() > 0) {
                    commodityDecKey.add("尺寸:");
                    commodityDecValue.add(pro_size);
                }
                if (pro_taste.length() > 0) {
                    commodityDecKey.add("味道:");
                    commodityDecValue.add(pro_taste);
                }
                adapter = new MyAdapter();
                mGvCommoditydec.setAdapter(adapter);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_back:
                finish();
                break;
            case R.id.iv_btn_add:
                startActivity(new Intent(context, CommodityEditDecActivity.class));
                break;
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.shangpintu2x);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return commodityDecKey.size();
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
                convertView = View.inflate(context, R.layout.layout_commoditydec_gric_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvcommoditydecitemkey.setText(commodityDecKey.get(position));
            viewHolder.tvcommoditydecitemvalue.setText(commodityDecValue.get(position));

            return convertView;
        }

        public class ViewHolder {
            public final TextView tvcommoditydecitemkey;
            public final TextView tvcommoditydecitemvalue;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                tvcommoditydecitemkey = (TextView) root.findViewById(R.id.tv_commodity_dec_item_key);
                tvcommoditydecitemvalue = (TextView) root.findViewById(R.id.tv_commodity_dec_item_value);
            }
        }
    }

}
