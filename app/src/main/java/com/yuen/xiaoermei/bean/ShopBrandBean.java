package com.yuen.xiaoermei.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ShopBrandBean {

    /**
     * code : 0
     * msg : 成功
     * data : [{"id":"8","brand":"Lay\u2019s/乐事","type":"0","state":"1"},{"id":"7","brand":"阿尔卑斯","type":"0","state":"1"},{"id":"6","brand":"ORION/好丽友","type":"0","state":"1"},{"id":"5","brand":"德芙","type":"0","state":"1"},{"id":"4","brand":"康师傅","type":"0","state":"1"},{"id":"3","brand":"统一","type":"0","state":"1"}]
     */

    private String code;
    private String msg;
    /**
     * id : 8
     * brand : Lay’s/乐事
     * type : 0
     * state : 1
     */

    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String brand;
        private String type;
        private String state;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
