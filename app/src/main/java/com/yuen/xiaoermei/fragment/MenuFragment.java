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

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.activity.ClientManagerActivity;
import com.yuen.xiaoermei.activity.DataManagerActivity;
import com.yuen.xiaoermei.activity.MainActivity;
import com.yuen.xiaoermei.activity.MoneyManagerActivity;
import com.yuen.xiaoermei.activity.OrderManagerActivity;
import com.yuen.xiaoermei.activity.SettingActivity;
import com.yuen.xiaoermei.activity.ShopManagerActivity;
import com.yuen.xiaoermei.bean.ConversationListBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import piccutdemo.RoundImageView;

/**
 * Created by Administrator on 2016/3/18.
 */
public class MenuFragment extends BaseFragment {
    private String[] menuItem = new String[]{"店铺管理", "订单管理", "财务管理", "客户管理", "数据统计", "消息", "设置"};
    private Integer[] menuIcon = new Integer[]{R.drawable.iconfontshangpi, R.drawable.iconfontdingdan2x,
            R.drawable.iconfontcaiwu2x, R.drawable.iconfontkehuguanli2x, R.drawable.iconfonttongji2x,
            R.drawable.iconfontxiaoxi2x, R.drawable.iconfontshezhi2x};
    private RoundImageView mIvUserIcon;
    private TextView mTvUserName;
    private ListView mLvLeftMenu;
    private MyAdapter myAdapter;
    private SharedPreferences sharedPreferences;
    private ImageOptions options;
    public static List<ConversationListBean.DataBean> conversationListBeanData;
    private List<Conversation> conversationList;
    private void assignViews(View view) {
        sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        mIvUserIcon = (RoundImageView) view.findViewById(R.id.iv_user_icon);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        mLvLeftMenu = (ListView) view.findViewById(R.id.lv_left_menu);
        String username = sharedPreferences.getString("username", "");
        mTvUserName.setText(username);
        mIvUserIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        options = new ImageOptions.Builder()
                //设置使用缓存
                .setUseMemCache(true)
                .build();
    }

    @Override
    public View initView() {
        View view = View.inflate(getActivity(), R.layout.layout_left_menu, null);
        assignViews(view);
        mIvUserIcon.setType(RoundImageView.TYPE_ROUND);
        mIvUserIcon.setBorderRadius(60);
        myAdapter = new MyAdapter();
        mLvLeftMenu.setAdapter(myAdapter);
        mLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private Intent intent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(getActivity(), ShopManagerActivity.class);
                        /**
                         * 防止activity重复打开
                         */
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getActivity().startActivity(intent);

                        break;
                    case 1:
                        intent = new Intent(getActivity(), OrderManagerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getActivity().startActivity(intent);

                        break;
                    case 2:
                        intent = new Intent(getActivity(), MoneyManagerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getActivity().startActivity(intent);

                        break;
                    case 3:
                        intent = new Intent(getActivity(), ClientManagerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getActivity().startActivity(intent);

                        break;
                    case 4:
                        intent = new Intent(getActivity(), DataManagerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getActivity().startActivity(intent);

                        break;
                    case 5:


                        /**
                         * 启动单聊
                         * context - 应用上下文。
                         * targetUserId - 要与之聊天的用户 Id。
                         * title - 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                         */
                      /*  if (RongIM.getInstance() != null) {
                            RongIM.getInstance().startPrivateChat(getActivity(), "456", "hello");
                        }
*/
                        if (RongIM.getInstance() != null)
                            RongIM.getInstance().startConversationList(getActivity());
                      /*  intent = new Intent(getActivity(), MyConversationList.class);
                       intent.putExtra("data", (Serializable) conversationListBeanData);
                       intent.putParcelableArrayListExtra("conver", (ArrayList<? extends Parcelable>) conversationList);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getActivity().startActivity(intent);*/
                        break;
                    case 6:
                        intent = new Intent(getActivity(), SettingActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }

    @Override
    public void initData() {
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
    }

    @Override
    public void onResume() {
        super.onResume();
        // Log.d("mafuhua", "MainActivity.shop_imgs" + MainActivity.shop_imgs);
        x.image().bind(mIvUserIcon, MainActivity.shop_imgs, options);
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
