package com.yuen.xiaoermei.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/30.
 */
public class MoneyBean implements Serializable{


    /**
     * zong : 0.01
     * yu : 0.01
     * price_num : 0.00
     */

    private String zong;
    private String yu;
    private String price_num;

    public String getZong() {
        return zong;
    }

    public void setZong(String zong) {
        this.zong = zong;
    }

    public String getYu() {
        return yu;
    }

    public void setYu(String yu) {
        this.yu = yu;
    }

    public String getPrice_num() {
        return price_num;
    }

    public void setPrice_num(String price_num) {
        this.price_num = price_num;
    }
}
