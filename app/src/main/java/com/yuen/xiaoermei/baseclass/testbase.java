package com.yuen.xiaoermei.baseclass;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuen.xiaoermei.R;

import java.util.List;

/**
 * Created by Administrator on 2016/4/14.
 */
public class testbase extends AppCompatActivity {
    class base extends DefaultAdapter {
        public base(List datas) {
            super(datas);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.layout_outmoney_list_item, null);
            }
            return super.getView(position, convertView, parent);
        }

        @Override
        public BaseHolder getHolder() {
            return null;
        }


        public class ViewHolder {
            public final TextView tvcontent;
            public final TextView tvtime;
            public final View root;

            public ViewHolder(View root) {
                tvcontent = (TextView) root.findViewById(R.id.tv_content);
                tvtime = (TextView) root.findViewById(R.id.tv_time);
                this.root = root;
            }
        }



}


}
