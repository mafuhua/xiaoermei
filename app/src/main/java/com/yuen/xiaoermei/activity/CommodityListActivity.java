package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.CommodityListBean;
import com.yuen.xiaoermei.utils.ContactURL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class CommodityListActivity extends AppCompatActivity implements View.OnClickListener {
    private MultiAutoCompleteTextView mEtInputSearch;
    private ImageView mIvBtnSearch;
    private GridView mGvCommoditylist;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private MyAdapter adapter;
    private List<String> proPriceList = new ArrayList<>();
    private List<String> proImageList = new ArrayList<>();
    private List<String> proNameList = new ArrayList<>();
    private List<String> proInventoryList = new ArrayList<>();
    private ImageOptions options;

    private void assignViews() {
        context = this;
        mEtInputSearch = (MultiAutoCompleteTextView) findViewById(R.id.et_input_search);
        mIvBtnSearch = (ImageView) findViewById(R.id.iv_btn_search);
        mGvCommoditylist = (GridView) findViewById(R.id.gv_commoditylist);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mIvBtnAdd.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
        mGvCommoditylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, CommodityDecActivity.class);
                context.startActivity(intent);
            }
        });
        options = new ImageOptions.Builder()
                    //设置加载过程中的图片
                .setLoadingDrawableId(R.drawable.ic_launcher)
                    //设置加载失败后的图片
                .setFailureDrawableId(R.drawable.ic_launcher)
                //设置使用缓存
                .setUseMemCache(true)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_list);
        assignViews();
        getCommodityList();

    }

    public void getCommodityList() {
        RequestParams params = new RequestParams(ContactURL.COMMODITY_LIST);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
               // Log.d("mafuhua", result.toString());
                String res = result.toString();
                Gson gson = new Gson();
                CommodityListBean commodityListBean = gson.fromJson(res, CommodityListBean.class);
                List<CommodityListBean.DataBean> commodityListBeanData = commodityListBean.getData();
                for (int i = 0; i < commodityListBeanData.size(); i++) {
                    CommodityListBean.DataBean dataBean = commodityListBeanData.get(i);
                    proNameList.add(i, dataBean.getPro_name());
                    proImageList.add(i, dataBean.getPro_img());
                    proPriceList.add(i, dataBean.getPro_price());
                    proInventoryList.add(i, dataBean.getPro_inventory());

                }
                adapter = new MyAdapter();
                mGvCommoditylist.setAdapter(adapter);
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
                break;
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return proPriceList.size();
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
                convertView = View.inflate(context, R.layout.layout_commodity_grid_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvcommodityprice.setText("￥:"+proPriceList.get(position));
            viewHolder.tvcommodityinventory.setText("库存:"+proInventoryList.get(position));
            viewHolder.tvcommoditydec.setText(proNameList.get(position));
            x.image().bind(viewHolder.ivcommodityicon, proImageList.get(position), options);
            return convertView;
        }


        public class ViewHolder {
            public final ImageView ivcommodityicon;
            public final TextView tvcommoditydec;
            public final TextView tvcommodityprice;
            public final TextView tvcommodityinventory;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                ivcommodityicon = (ImageView) root.findViewById(R.id.iv_commodity_icon);
                tvcommoditydec = (TextView) root.findViewById(R.id.tv_commodity_dec);
                tvcommodityprice = (TextView) root.findViewById(R.id.tv_commodity_price);
                tvcommodityinventory = (TextView) root.findViewById(R.id.tv_commodity_inventory);
            }
        }
    }


}
