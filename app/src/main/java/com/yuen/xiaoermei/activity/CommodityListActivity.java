package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.CommodityListBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.SysExitUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class CommodityListActivity extends AppCompatActivity implements View.OnClickListener {
    public boolean isLoadingMore = false;//是否加载下一页
    public boolean isRefresh = false;//是否刷新
    private EditText mEtInputSearch;
    private ImageView mIvBtnSearch;
    private GridViewWithHeaderAndFooter mGvCommoditylist;
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
    private int page = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    mSwipeRefreshLayout.setRefreshing(false);
                    //adapter.notifyDataSetChanged();
                    //swipeRefreshLayout.setEnabled(false);
                    break;
                default:
                    break;
            }
        }
    };
    private View footerView;

    private void assignViews() {
        context = this;
        mEtInputSearch = (EditText) findViewById(R.id.et_input_search);
        mIvBtnSearch = (ImageView) findViewById(R.id.iv_btn_search);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mGvCommoditylist = (GridViewWithHeaderAndFooter) findViewById(R.id.gv_commoditylist);
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mIvBtnAdd.setOnClickListener(this);
        mIvBtnBack.setOnClickListener(this);
        mIvBtnSearch.setOnClickListener(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        footerView = layoutInflater.inflate(R.layout.refresh_footer, null);
        mGvCommoditylist.addFooterView(footerView);
        footerView.setVisibility(View.GONE);
        adapter = new MyAdapter();
        mGvCommoditylist.setAdapter(adapter);
        mGvCommoditylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, CommodityDecActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.putExtra("commodityid", proIDList.get(position));
                context.startActivity(intent);
            }
        });

        mGvCommoditylist.setOnScrollListener(new MyOnScrollListener());
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
               // Toast.makeText(context, "正在刷新", Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override

                    public void run() {
                        if (!isRefresh) {
                            isLoadingMore = true;
                            Log.d("mafuhua", "刷新");
                            proNameList.clear();
                            proImageList.clear();
                            proPriceList.clear();
                            proInventoryList.clear();
                            proIDList.clear();
                            proSheLvesList.clear();
                            page = 0;
                            getCommodityList();
                            mHandler.sendEmptyMessage(1);
                        }

                    }
                }).start();

            }
        });
        options = new ImageOptions.Builder()
                //设置使用缓存
                .setUseMemCache(true)
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_list);
        SysExitUtil.activityList.add(this);
        //getCommodityList();
        assignViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        proNameList.clear();
        proImageList.clear();
        proPriceList.clear();
        proInventoryList.clear();
        proIDList.clear();
        proSheLvesList.clear();
        page = 0;
        getCommodityList();
        mGvCommoditylist.setSelection(0);
    }

    public void getCommodityList() {
        RequestParams params = new RequestParams(ContactURL.COMMODITY_LIST + MainActivity.userid + "/page/" + page);
        Log.d("mafuhua", ContactURL.COMMODITY_LIST + MainActivity.userid + "/" + page);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                isLoadingMore = false;
                isRefresh = false;
              //  Log.d("mafuhua", result.toString());
               /**/
                String res = result.toString();
                Gson gson = new Gson();
                CommodityListBean commodityListBean = gson.fromJson(res, CommodityListBean.class);
                List<CommodityListBean.DataBean> commodityListBeanData = commodityListBean.getData();
              //  Log.d("mafuhua", "commodityListBeanData:" + commodityListBeanData);
                if (commodityListBeanData==null) {
                    Toast.makeText(context, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    footerView.setVisibility(View.GONE);
                    return;
                }else if (commodityListBeanData.size()>18){
                    footerView.setVisibility(View.VISIBLE);
                }
                for (int i = 0; i < commodityListBeanData.size(); i++) {
                    CommodityListBean.DataBean dataBean = commodityListBeanData.get(i);
                    proNameList.add(dataBean.getPro_name());
                    proImageList.add(dataBean.getPro_img());
                    proPriceList.add(dataBean.getPro_price());
                    proInventoryList.add(dataBean.getPro_inventory());
                    proIDList.add(dataBean.getId());
                    proSheLvesList.add(dataBean.getPro_shelves());

                }
                //Log.d("mafuhua", "proNameList:" + proNameList);
                adapter.notifyDataSetChanged();

                // mGvCommoditylist.setAdapter(adapter);
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
                Intent intent1 = new Intent(context, CommodityEditADDDecActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent1);
                break;
            case R.id.iv_btn_search:
                String tvsearch = mEtInputSearch.getText().toString().trim();
                Intent intent = new Intent(context, CommoditySearchListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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

    class MyOnScrollListener implements AbsListView.OnScrollListener {
        /**
         * 状态改变回调 public static int SCROLL_STATE_IDLE = 0;//空闲 public static int
         * SCROLL_STATE_TOUCH_SCROLL = 1;//触摸滑动 public static int
         * SCROLL_STATE_FLING = 2;//滑翔状态
         */
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // 滚动状态
            //Log.d("mafuhua", "scrollState:" + scrollState);
            int lastVisiblePosition = mGvCommoditylist.getLastVisiblePosition();
            //Log.d("mafuhua", "lastVisiblePosition:" + lastVisiblePosition);
            //  Log.d("mafuhua", "mGvCommoditylist.getCount():" + mGvCommoditylist.getCount());
            // 如果处于空闲状态
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                //最后一个条目
                if (lastVisiblePosition == mGvCommoditylist.getCount() - 1) {
                    if (!isLoadingMore) {
                        isLoadingMore = true;
                        Log.d("mafuhua", "加载下一页");
                        //请求下一页数据：handler模拟加载下一页数据
//                        handler.sendEmptyMessageDelayed(REFRESHFINISH, 2000);
                        page = page + 1;
                        getCommodityList();
                    }
                } else {

                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

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
