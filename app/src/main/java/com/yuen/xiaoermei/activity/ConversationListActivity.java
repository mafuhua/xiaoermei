package com.yuen.xiaoermei.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.lisetner.MyReceiveMessageListener;
import com.yuen.xiaoermei.utils.Friend;
import com.yuen.xiaoermei.utils.SysExitUtil;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class ConversationListActivity extends FragmentActivity implements RongIM.UserInfoProvider {

    private List<Friend> userIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);
        SysExitUtil.activityList.add(this);
        userIdList = new ArrayList<>();
        userIdList.add(new Friend("1", "联通", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
        userIdList.add(new Friend("359", "移动", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
        RongIM.setUserInfoProvider(this, true);
        enterFragment();
    }

    /**
     * 加载 会话列表 ConversationListFragment
     */
    private void enterFragment() {

        ConversationListFragment fragment = (ConversationListFragment) getSupportFragmentManager().findFragmentById(R.id.conversationlist);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        fragment.setUri(uri);
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        for (Friend i : MyReceiveMessageListener.userIdList) {
            if (i.getUserId().equals(userId)) {
                Log.e("mafuhua", i.getPortraitUri());
                return new UserInfo(i.getUserId(), i.getUserName(), Uri.parse(i.getPortraitUri()));
            }
        }
        return null;
    }

}