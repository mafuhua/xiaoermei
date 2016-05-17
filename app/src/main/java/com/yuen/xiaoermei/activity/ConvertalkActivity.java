package com.yuen.xiaoermei.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.lisetner.MyReceiveMessageListener;
import com.yuen.xiaoermei.utils.SysExitUtil;

import io.rong.imkit.RongIM;

public class ConvertalkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertalk);
        SysExitUtil.activityList.add(this);

   /*
      启动单聊
      context - 应用上下文。
      targetUserId - 要与之聊天的用户 Id。
      title - 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。*/

        if (RongIM.getInstance() != null) {
            RongIM.getInstance().startPrivateChat(this, MyReceiveMessageListener.targetId, "");
        }
        finish();
    }
}
