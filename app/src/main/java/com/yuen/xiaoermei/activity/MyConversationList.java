package com.yuen.xiaoermei.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.bean.ConversationListBean;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.MyUtils;
import com.yuen.xiaoermei.utils.SysExitUtil;

import org.xutils.x;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

public class MyConversationList extends AppCompatActivity implements  RongIM.UserInfoProvider {

    private ImageView iv_btn_back;
    private TextView tv_title_dec;
    private ImageView iv_btn_add;
    private TextView tv_tixian;
    private LinearLayout layout_title_bar;
    private ListView list;
    private List<Conversation> conversationList;
  //  private List<ConversationListBean.DataBean> conversationListBeanData;
    private MyAdapter myAdapter;
    private List<ConversationListBean.DataBean> conversationListBeanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_conversation_list);
        SysExitUtil.activityList.add(this);
        initView();
        Intent intent = getIntent();
        conversationListBeanData = (List<ConversationListBean.DataBean>) intent.getSerializableExtra("data");
        conversationList =intent.getParcelableArrayListExtra("conver");
        myAdapter = new MyAdapter();
        list.setAdapter(myAdapter);
      /*  RongIMClientWrapper rongIMClient = RongIM.getInstance().getRongIMClient();
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
                myAdapter = new MyAdapter();
                list.setAdapter(myAdapter);
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
        });
*/
    }

    private void initView() {
        iv_btn_back = (ImageView) findViewById(R.id.iv_btn_back);
        tv_title_dec = (TextView) findViewById(R.id.tv_title_dec);
        iv_btn_add = (ImageView) findViewById(R.id.iv_btn_add);
        tv_tixian = (TextView) findViewById(R.id.tv_tixian);
        layout_title_bar = (LinearLayout) findViewById(R.id.layout_title_bar);
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * 启动单聊
                 * context - 应用上下文。
                 * targetUserId - 要与之聊天的用户 Id。
                 * title - 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                 */

                Conversation conversation = conversationList.get(position);

                if (RongIM.getInstance() != null) {
                    RongIM.getInstance().startPrivateChat(MyConversationList.this, conversation.getTargetId(),conversationListBeanData.get(position).getNickname());
                }
            }
        });
      /*  myAdapter = new MyAdapter();
        list.setAdapter(myAdapter);*/
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        for (ConversationListBean.DataBean i : conversationListBeanData) {
            return new UserInfo(i.getUid(), i.getNickname(), Uri.parse(ContactURL.BASEIMG_URL + i.getAvatar()));
        }
        return null;
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return conversationList == null ? 0 : conversationList.size();
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
                convertView = View.inflate(MyConversationList.this, R.layout.item_converdationlsit, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            TextMessage latestMessage = (TextMessage) conversationList.get(position).getLatestMessage();
            if (conversationList.get(position).getUnreadMessageCount() < 1) {
                viewHolder.count.setVisibility(View.GONE);
            } else {
                viewHolder.count.setText(conversationList.get(position).getUnreadMessageCount() + "");
                viewHolder.count.setVisibility(View.VISIBLE);
            }
            viewHolder.content.setText(latestMessage.getContent() + "");
            viewHolder.name.setText(conversationListBeanData.get(position).getNickname());
            viewHolder.time.setText(MyUtils.formatTime(conversationList.get(position).getReceivedTime()));
            x.image().bind(viewHolder.icon, ContactURL.BASEIMG_URL + conversationListBeanData.get(position).getAvatar(), MyUtils.options6);
            return convertView;
        }


    }

    public class ViewHolder {
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

    }

}
