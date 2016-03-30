package com.yuen.xiaoermei.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuen.xiaoermei.R;
import com.yuen.xiaoermei.baseclass.BaseActivity;
import com.yuen.xiaoermei.utils.ContactURL;
import com.yuen.xiaoermei.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

/**
 * 开店须知编辑
 */
public class ShopNoticeEditActivity extends BaseActivity {
    private EditText mEtShopNoticeTitle;
    private EditText mEtShopNoticeContent;
    private ImageView mIvBtnShopnoticeTijiao;
    private LinearLayout mLayoutTitleBar;
    private ImageView mIvBtnBack;
    private TextView mTvTitleDec;
    private ImageView mIvBtnAdd;



    private void assignViews() {
        mLayoutTitleBar = (LinearLayout) findViewById(R.id.layout_title_bar);
        mIvBtnBack = (ImageView) findViewById(R.id.iv_btn_back);
        mTvTitleDec = (TextView) findViewById(R.id.tv_title_dec);
        mIvBtnAdd = (ImageView) findViewById(R.id.iv_btn_add);
        mEtShopNoticeTitle = (EditText) findViewById(R.id.et_shop_notice_title);
        mEtShopNoticeContent = (EditText) findViewById(R.id.et_shop_notice_content);
        mIvBtnShopnoticeTijiao = (ImageView) findViewById(R.id.iv_btn_shopnotice_tijiao);
        mTvTitleDec.setText("店铺公告");
        mIvBtnAdd.setVisibility(View.GONE);
        mIvBtnShopnoticeTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mEtShopNoticeTitle.getText().toString();
                String content = mEtShopNoticeContent.getText().toString();
                if (TextUtils.isEmpty(title)||TextUtils.isEmpty(content)) {
                    Toast.makeText(ShopNoticeEditActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                setAddGG(title,content);
                finish();
            }
        });
        mIvBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_notice_edit);
        toNext();
        assignViews();
    }
    private void setAddGG(String title, String content) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", MainActivity.userid);
        map.put("shop_gg_title", title);
        map.put("shop_con", content);
        XUtils.xUtilsPost(ContactURL.SHOP_ADD_GG, map, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d("mafuhua", result.toString());


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d("mafuhua", isOnCallback + "");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {

            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&&event.getAction()== MotionEvent.ACTION_UP) {

            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
