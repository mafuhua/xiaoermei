package com.yuen.xiaoermei.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.ConversationListBean;
import com.yuen.xiaoermei.lisetner.MyReceiveMessageListener;
import com.yuen.xiaoermei.utils.Friend;
import com.yuen.xiaoermei.utils.SysExitUtil;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class ConversationListActivity extends FragmentActivity  implements RongIM.UserInfoProvider{

    private List<Friend> userIdList;
    private List<ConversationListBean.DataBean> conversationListBeanData;
    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private TextView tv_tixian;
    private LinearLayout layout_title_bar;
    private Context context;
    private List<Conversation> conversationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);
        SysExitUtil.activityList.add(this);
        context = this;
        initView();
        userIdList = new ArrayList<>();
        userIdList.add(new Friend("1", "联通", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
        userIdList.add(new Friend("359", "移动", "http://img02.tooopen.com/Download/2010/5/22/20100522103223994012.jpg"));
        RongIM.setUserInfoProvider(this, true);

     /*   RongIMClientWrapper rongIMClient = RongIM.getInstance().getRongIMClient();
        conversationList = rongIMClient.getConversationList();
        String ids = "";
        for (int i = 0; i < conversationList.size(); i++) {
            String targetId = conversationList.get(i).getTargetId();
            ids = ids + targetId + ",";
        }
        String substring = ids.substring(0, ids.length() - 1);
        Log.d("MyConversationList", "---XIAO---" + substring);
        HashMap<String, String> map = new HashMap<>();
        map.put("user_id", substring);
        XUtils.xUtilsPost(ContactURL.XIAO_UID, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("MyConversationList", "---XIAO_UID00---" + result);
                Gson gson = new Gson();
                ConversationListBean conversationListBean = gson.fromJson(result, ConversationListBean.class);
                conversationListBeanData = conversationListBean.getData();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });*/
        enterFragment();

    }

    /**
     * 加载 会话列表 ConversationListFragment
     */
    private void enterFragment() {

        ConversationListFragment fragment = (ConversationListFragment) getSupportFragmentManager().findFragmentById(R.id.conversationlist);
   //     fragment.setAdapter(new NewAdapter(context));
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        fragment.setUri(uri);
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

    @Override
    public UserInfo getUserInfo(String userId) {
      //  return new UserInfo(SPUtil.getString("username"), SPUtil.getString("id"), Uri.parse(SPUtil.getString("show_img")));
        for (Friend i : MyReceiveMessageListener.userIdList) {
            if (i.getUserId().equals(userId)) {
                Log.e("mafuhua", i.getPortraitUri());
                return new UserInfo(i.getUserId(), i.getUserName(), Uri.parse(i.getPortraitUri()));
            }
        }
       /* for (ConversationListBean.DataBean i : MenuFragment.conversationListBeanData) {
            return new UserInfo(i.getUid(), i.getNickname(), Uri.parse(ContactURL.BASEIMG_URL+i.getAvatar()));
        }*/
        return null;
    }

  /*  class NewAdapter extends ConversationListAdapter {

        private Context contextt;

        public NewAdapter(Context contextt) {
            super(context);
            this.contextt = contextt;
        }

        @Override
        protected View newView(Context context, int position, ViewGroup group) {
            View inflate = View.inflate(contextt, R.layout.item_converdationlsit, (ViewGroup)null);
            ViewHolder viewHolder = new ViewHolder(inflate);
            inflate.setTag(viewHolder);
            return inflate;
        }

        @Override
        protected void bindView(View v, int position, UIConversation data) {

            ViewHolder viewHolder = (ViewHolder) v.getTag();
            TextMessage latestMessage = (TextMessage) conversationList.get(position).getLatestMessage();
            if (conversationList.get(position).getUnreadMessageCount() < 1) {
                viewHolder.count.setVisibility(View.GONE);
            } else {
                viewHolder.count.setText(conversationList.get(position).getUnreadMessageCount() + "");
                viewHolder.count.setVisibility(View.VISIBLE);
            }
            viewHolder.content.setText(latestMessage.getContent() + "");
     //       viewHolder.name.setText(conversationListBeanData.get(position).getNickname()+"");
            viewHolder.time.setText(MyUtils.formatTime(conversationList.get(position).getReceivedTime())+"");
       //     x.image().bind(viewHolder.icon, ContactURL.BASEIMG_URL + conversationListBeanData.get(position).getAvatar(), MyUtils.options6);
        }
    }*/

  /*  public class ViewHolder {
        public View rootView;
        public ImageView icon;
        public TextView name;
        public TextView time;
        public TextView content;
        public TextView count;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.icon = (ImageView) rootView.findViewById(R.id.icon);
            this.name = (TextView) rootView.findViewById(R.id.name);
            this.time = (TextView) rootView.findViewById(R.id.time);
            this.content = (TextView) rootView.findViewById(R.id.content);
            this.count = (TextView) rootView.findViewById(R.id.count);
        }

    }*/


}