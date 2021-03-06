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
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.CommodityEditDecBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
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
    private List<String> commodityImageList = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;
    private String commodityid;
    private ImageOptions options;
    private TextView mTvCommodityDecContent;
    private LinearLayout mLlPointGroup;
    private CommodityEditDecBean.DataBean commodityDecBeanData;

    private void assignViews() {
        context = this;
        mVpCommodityDec = (ViewPager) findViewById(R.id.vp_commodity_dec);
        mTvCommodityDec = (TextView) findViewById(R.id.tv_commodity_dec);
        mTvCommodityDecContent = (TextView) findViewById(R.id.tv_commodity_dec_content);
        mGvCommoditydec = (GridView) findViewById(R.id.gv_commoditydec);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mLlPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
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
        options = new ImageOptions.Builder()
                //设置使用缓存
                .setUseMemCache(true)
                // 图片缩放模式
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_dec);
        SysExitUtil.activityList.add(this);
        Intent intent = getIntent();
        commodityid = intent.getStringExtra("commodityid");
        Log.d("mafuhua", "commodityid"+commodityid);
        assignViews();
        getCommodityList(commodityid);
       // regListener();
    }
    public void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len / 2 + 1; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;//+ (listView.getHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
    private void addPoints() {
        for(int i = 0;i<commodityImageList.size();i++){
            // 动态添加指示点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_bg); // 设置背景

            // 默认让第一个点是选中状态
            if(i == 0){
                point.setEnabled(true);
            }else{
                point.setEnabled(false);
            }

            // 布局参数 : 当布局添加子view 时， 布局参数一定要和布局的类型 匹配
            // 向线性布局中，添加子view时，一定要指定线性布局的布局参数
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);

            layoutParams.leftMargin = 10; // 左边距，10象素
            layoutParams.topMargin = 5; // 上边距 ,5 象素

            mLlPointGroup.addView(point,layoutParams); // 添加至页面中之前准备好的布局


        }
    }
    public void getCommodityList(String commodity) {
       // RequestParams params = new RequestParams(ContactURL.COMMODITY_DEC+commodityid);
        org.xutils.http.RequestParams params = new org.xutils.http.RequestParams(ContactURL.SHOP_EDIT_COMMODITY);
        params.addBodyParameter("product_id", commodity);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", "-----SHOP_EDIT_COMMODITY-----"+result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                CommodityEditDecBean commodityDecBean = gson.fromJson(res, CommodityEditDecBean.class);
                commodityDecBeanData = commodityDecBean.getData();
                //  CommodityDecBean.DataBean commodityDecBeanData = commodityDecBean.;
                String pro_color = commodityDecBeanData.getPro_color();
                String pro_h_price = commodityDecBeanData.getPro_h_price();
                List<CommodityEditDecBean.DataBean.ProImgsBean> proImg = commodityDecBeanData.getPro_imgs();
                for (int i = 0; i < proImg.size(); i++) {
                    CommodityEditDecBean.DataBean.ProImgsBean proImgBean = proImg.get(i);
                    commodityImageList.add(proImgBean.getImg());
                }
                mVpCommodityDec.setAdapter(myPagerAdapter);

                String pro_inventory = commodityDecBeanData.getPro_inventory();
                String pro_kg = commodityDecBeanData.getPro_kg();
                String pro_ml = commodityDecBeanData.getPro_ml();
                String pro_name = commodityDecBeanData.getPro_name();
                String pro_origin = commodityDecBeanData.getPro_origin();
                String pro_price = commodityDecBeanData.getPro_price();
                String pro_size = commodityDecBeanData.getPro_size();
                String pro_taste = commodityDecBeanData.getPro_taste();
                String pro_content = commodityDecBeanData.getPro_content();
                mTvCommodityDec.setText(pro_name);

           /*     if (pro_h_price.length() > 0) {
                    commodityDecKey.add("活动价:");
                    commodityDecValue.add(pro_h_price);
                }*/
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
                if (pro_color.length() > 0) {
                    commodityDecKey.add("颜色:");
                    commodityDecValue.add(pro_color);
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
                mTvCommodityDecContent.setText(pro_content);
                adapter = new MyAdapter();
                mGvCommoditydec.setAdapter(adapter);
                setListViewHeightBasedOnChildren(mGvCommoditydec);
                addPoints();
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
                Intent intent = new Intent(context, CommodityEditDecActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("commodityid", commodityid);
                intent.putExtra("data",commodityDecBeanData);
                startActivity(intent);
                finish();
                break;
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
           /* LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);
            imageView .setLayoutParams(mParams);*/
            x.image().bind(imageView, commodityImageList.get(position), options);

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return commodityImageList.size();
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
