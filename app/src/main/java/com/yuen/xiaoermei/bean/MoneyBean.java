package com.yuen.xiaoermei.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/30.
 */
public class MoneyBean implements Serializable{

    /**
     * zong : 133185.00
     * yu : 133184
     * price_num : 1.00
     */

    private String zong;
    private int yu;
    private String price_num;

    public String getZong() {
        return zong;
    }

    public void setZong(String zong) {
        this.zong = zong;
    }

    public int getYu() {
        return yu;
    }

    public void setYu(int yu) {
        this.yu = yu;
    }

    public String getPrice_num() {
        return price_num;
    }

    public void setPrice_num(String price_num) {
        this.price_num = price_num;
    }
}
