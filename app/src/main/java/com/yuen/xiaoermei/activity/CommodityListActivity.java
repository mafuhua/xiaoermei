package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText mEtInputSearch;
    private ImageView mIvBtnSearch;
    private GridView mGvCommoditylist;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;
    private Context context;
    private MyAdapter adapter;
    private List<String> proPriceList = new ArrayList<>();
    private List<String> proImageList = new ArrayList<>();
    private List<String> proIDList = new ArrayList<>();
    private List<String> proSheLvesList = new ArrayList<>();
    private List<String> proNameList = new ArrayList<>();
    private List<String> proInventoryList = new ArrayList<>();
    private ImageOptions options;

    private void assignViews() {
        context = this;
        mEtInputSearch = (EditText) findViewById(R.id.et_input_search);
        mIvBtnSearch = (ImageView) findViewById(R.id.iv_btn_search);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mGvCommoditylist = (GridView) findViewById(R.id.gv_commoditylist);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mIvBtnAdd.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
        mIvBtnSearch.setOnClickListener(this);
        adapter = new MyAdapter();
        mGvCommoditylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, CommodityDecActivity.class);
                intent.putExtra("commodityid", proIDList.get(position));
                context.startActivity(intent);
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                Toast.makeText(context, "正在刷新", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();

            }
        });
        options = new ImageOptions.Builder()
                //设置使用缓存
                .setUseMemCache(true)
                .build();
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    mSwipeRefreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                    //swipeRefreshLayout.setEnabled(false);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_list);
        assignViews();
        getCommodityList();

    }

    public void getCommodityList() {
        RequestParams params = new RequestParams(ContactURL.COMMODITY_LIST+ MainActivity.userid);
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
                    proIDList.add(i, dataBean.getId());
                    proSheLvesList.add(i, dataBean.getPro_shelves());

                }

                mGvCommoditylist.setAdapter(adapter);
               /* String[] arr = proNameList.toArray(new String[proNameList.size()]);
                ArrayAdapter<String> myadapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, arr); //Adapter
                mEtInputSearch.setAdapter(myadapter); //设置adapter
                mEtInputSearch.setThreshold(1);*/
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
            case R.id.iv_btn_search:
                String tvsearch = mEtInputSearch.getText().toString().trim();
                Intent intent = new Intent(context, CommoditySearchListActivity.class);
                intent.putExtra("tvsearch", tvsearch);
                startActivity(intent);
                /*if (proNameList.contains(tvsearch)) {
                    for (int i = 0; i < proNameList.size(); i++) {
                        String searchname = proNameList.get(i);
                        if (searchname.equals(tvsearch)) {

                            break;
                        }
                    }
                } else {
                    Toast.makeText(context, "没有搜索到商品", Toast.LENGTH_SHORT).show();
                }*/
                break;
        }
    }

    class MyAdapter extends BaseAdapter {
        public MyAdapter() {
        }

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
            viewHolder.tvcommodityprice.setText("￥:" + proPriceList.get(position));
            viewHolder.tvcommodityinventory.setText("库存:" + proInventoryList.get(position));
            viewHolder.tvcommoditydec.setText(proNameList.get(position));
            String shelves = proSheLvesList.get(position);
            if (shelves.equals("0")) {
                viewHolder.tvcommodityshelves.setText("上架");
            } else if (shelves.equals("1")) {
                viewHolder.tvcommodityshelves.setText("下架");
            }

            x.image().bind(viewHolder.ivcommodityicon, proImageList.get(position), options);
            return convertView;
        }


        public class ViewHolder {
            public final ImageView ivcommodityicon;
            public final TextView tvcommoditydec;
            public final TextView tvcommodityprice;
            public final TextView tvcommodityinventory;
            public final TextView tvcommodityshelves;
            public final View root;

            public ViewHolder(View root) {
                this.root = root;
                ivcommodityicon = (ImageView) root.findViewById(R.id.iv_commodity_icon);
                tvcommoditydec = (TextView) root.findViewById(R.id.tv_commodity_dec);
                tvcommodityshelves = (TextView) root.findViewById(R.id.tv_commodity_shelves);
                tvcommodityprice = (TextView) root.findViewById(R.id.tv_commodity_price);
                tvcommodityinventory = (TextView) root.findViewById(R.id.tv_commodity_inventory);
            }
        }
    }


}