package com.yuen.xiaoermei.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.activity.ClientManagerActivity;
import com.yuen.xiaoermei.activity.DataManagerActivity;
import com.yuen.xiaoermei.activity.MessagerMangerActivity;
import com.yuen.xiaoermei.activity.MoneyManagerActivity;
import com.yuen.xiaoermei.activity.OrderManagerActivity;
import com.yuen.xiaoermei.activity.SettingActivity;
import com.yuen.xiaoermei.activity.ShopManagerActivity;

/**
 * Created by Administrator on 2016/3/18.
 */
public class MenuFragment extends BaseFragment {
    private String[] menuItem = new String[]{"店铺管理", "订单管理", "财务管理", "客户管理", "数据统计", "消息","设置"};
    private Integer[] menuIcon = new Integer[]{R.drawable.iconfontshangpi, R.drawable.iconfontdingdan2x,
            R.drawable.iconfontcaiwu2x, R.drawable.iconfontkehuguanli2x, R.drawable.iconfonttongji2x,
            R.drawable.iconfontxiaoxi2x,R.drawable.iconfontshezhi2x};
    private ImageView mIvUserIcon;
    private TextView mTvUserName;
    private ListView mLvLeftMenu;
    private MyAdapter myAdapter;
    private SharedPreferences sharedPreferences;
    private void assignViews(View view) {
        sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        mIvUserIcon = (ImageView) view.findViewById(R.id.iv_user_icon);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        mLvLeftMenu = (ListView) view.findViewById(R.id.lv_left_menu);
        String username = sharedPreferences.getString("username", "");
        mTvUserName.setText(username);
        mIvUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"点我干嘛", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.layout_left_menu, null);
        assignViews(view);
        myAdapter = new MyAdapter();
        mLvLeftMenu.setAdapter(myAdapter);
        mLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private Intent intent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getActivity(), menuItem[position], Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        intent = new Intent(getActivity(), ShopManagerActivity.class);
                        getActivity().startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(getActivity(), OrderManagerActivity.class);
                        getActivity().startActivity(intent);

                        break;
                    case 2:
                        intent = new Intent(getActivity(), MoneyManagerActivity.class);
                        getActivity().startActivity(intent);

                        break;
                    case 3:
                        intent = new Intent(getActivity(), ClientManagerActivity.class);
                        getActivity().startActivity(intent);

                        break;
                    case 4:
                        intent = new Intent(getActivity(), DataManagerActivity.class);
                        getActivity().startActivity(intent);

                        break;
                    case 5:
                        intent = new Intent(getActivity(), MessagerMangerActivity.class);
                        getActivity().startActivity(intent);

                        break;
                    case 6:
                        intent = new Intent(getActivity(), SettingActivity.class);
                        getActivity().startActivity(intent);

                        break;


                }
            }
        });
        return view;
    }

    @Override
    public void initData() {

    }

    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return menuItem.length;
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

            View view;
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.layout_menu_list_item, null);
            }
            ImageView ivmenuitemicon = (ImageView) convertView.findViewById(R.id.iv_menuitem_icon);
            TextView tvmenuitemdec = (TextView) convertView.findViewById(R.id.tv_menuitem_dec);
            ivmenuitemicon.setBackgroundResource(menuIcon[position]);
            tvmenuitemdec.setText(menuItem[position]);
            return convertView;
        }

    }
}