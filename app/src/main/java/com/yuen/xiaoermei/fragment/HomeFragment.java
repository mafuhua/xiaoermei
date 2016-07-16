package com.yuen.xiaoermei.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.activity.CommodityListActivity;
import com.yuen.xiaoermei.activity.MainActivity;
import com.yuen.xiaoermei.activity.MarketingSchoolActivity;
import com.yuen.xiaoermei.activity.OpenShopNoticeActivity;

/**
 * Created by Administrator on 2016/3/18.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{
    private MainActivity context;
    private ImageView mIvBtnMenu;
    private LinearLayout mIvBtnCommodity;
    private LinearLayout mIvBtnShopnotice;
    private LinearLayout mIvBtnMarketing;

    private void assignViews(View view) {
        mIvBtnMenu = (ImageView) view.findViewById(R.id.iv_btn_menu);
        mIvBtnCommodity = (LinearLayout) view.findViewById(R.id.iv_btn_commodity);
        mIvBtnShopnotice = (LinearLayout) view.findViewById(R.id.iv_btn_shopnotice);
        mIvBtnMarketing = (LinearLayout) view.findViewById(R.id.iv_btn_marketing);
        mIvBtnMenu.setOnClickListener(this);
        mIvBtnCommodity.setOnClickListener(this);
        mIvBtnShopnotice.setOnClickListener(this);
        mIvBtnMarketing.setOnClickListener(this);
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.layout_main_content, null);
        context = (MainActivity) getActivity();
        assignViews(view);

        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.iv_btn_menu:
                context.showMenu();
                break;
            case R.id.iv_btn_commodity:
                intent = new Intent(context,CommodityListActivity.class);
                context.startActivity(intent);

                break;
            case R.id.iv_btn_shopnotice:
                intent = new Intent(context,OpenShopNoticeActivity.class);
                context.startActivity(intent);
                break;
            case R.id.iv_btn_marketing:
                intent = new Intent(context,MarketingSchoolActivity.class);
                context.startActivity(intent);

                break;
        }
    }
}
