package com.yuen.xiaoermei.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.utils.SysExitUtil;

public class ShopManagerEditAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_manager_edit_address);
        SysExitUtil.activityList.add(this);
    }


}
