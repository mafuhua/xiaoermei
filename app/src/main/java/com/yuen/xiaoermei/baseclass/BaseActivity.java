package com.yuen.xiaoermei.baseclass;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.yuen.xiaoermei.R;

/**
 * Created by Administrator on 2016/3/19.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }
    public void toNext(){
        overridePendingTransition(R.anim.next_in, R.anim.next_out);
    }
    public void goBack(){
        overridePendingTransition(R.anim.pre_in, R.anim.pre_out);
        finish();
    }

}
