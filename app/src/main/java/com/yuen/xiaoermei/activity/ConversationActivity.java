package com.yuen.xiaoermei.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.utils.MyApplication;
import com.yuen.xiaoermei.utils.SPUtil;
import com.yuen.xiaoermei.utils.SysExitUtil;

import java.util.Locale;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imkit.widget.provider.TextInputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Bob on 15/8/18.
 * 会话页面
 */
public class ConversationActivity extends ActionBarActivity implements RongIM.UserInfoProvider {

    private String mTargetId;


    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private TextView tv_tixian;
    private LinearLayout layout_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        initView();
        SysExitUtil.activityList.add(this);

        TextInputProvider textInputProvider = new TextInputProvider(RongContext.getInstance());
        RongIM.setPrimaryInputProvider(textInputProvider);
        InputProvider.ExtendProvider[] provider = {
                new ImageInputProvider(RongContext.getInstance()),//图片
                new CameraInputProvider(RongContext.getInstance()),//相机
        };

        RongIM.resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, provider);

        Intent intent = getIntent();

        getIntentDate(intent);

        isReconnect(intent);
    }

    /**
     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
     */
    private void getIntentDate(Intent intent) {

        mTargetId = intent.getData().getQueryParameter("targetId");
        //intent.getData().getLastPathSegment();//获得当前会话类型
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));

        enterFragment(mConversationType, mTargetId);
        RongIM.getInstance().getRongIMClient().setConversationNotificationStatus(mConversationType, mTargetId, Conversation.ConversationNotificationStatus.NOTIFY, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {

            @Override
            public void onSuccess(Conversation.ConversationNotificationStatus status) {
                Log.i("mafuhua", "SetConversationNotificationFragment------" + status);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.i("mafuhua", "SetConversation//---NotificationFragment");
            }
        });
    }


    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType
     * @param mTargetId
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);
    }


    /**
     * 判断消息是否是 push 消息
     */
    private void isReconnect(Intent intent) {


        //push或通知过来
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("push") != null
                    && intent.getData().getQueryParameter("push").equals("true")) {

                reconnect("Mt2khvgkeVz3f6y8ePcN2LOfAwdWcN1nOBUdFP7QtVDHUr8Jw47No09crVEMD24uWn+av7waUiI=");
                Log.d("mafuhua", "/push或通知过来重来呢---------");
            } else {
                //程序切到后台，收到消息后点击进入,会执行这里
                if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {

                    reconnect("Mt2khvgkeVz3f6y8ePcN2LOfAwdWcN1nOBUdFP7QtVDHUr8Jw47No09crVEMD24uWn+av7waUiI=");
                    Log.d("mafuhua", "/push重来呢---------");
                } else {
                    enterFragment(mConversationType, mTargetId);
                }
            }
        }
    }


    /**
     * 重连
     *
     * @param token
     */
    private void reconnect(String token) {

        if (getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {

                    enterFragment(mConversationType, mTargetId);
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public UserInfo getUserInfo(String s) {
        return new UserInfo(SPUtil.getString("username"), SPUtil.getString("id"), Uri.parse(SPUtil.getString("show_img")));
    }

    private void initView() {
        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
        tv_title_dec = (TextView) findViewById(R.id.tv_title_dec);
        iv_btn_add = (ImageView) findViewById(R.id.iv_btn_add);
        tv_tixian = (TextView) findViewById(R.id.tv_tixian);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        iv_btn_add.setVisibility(View.GONE);
        tv_title_dec.setText("小而美");
        iv_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ConversationActivity", "关闭");
                finish();
            }
        });
    }
}
