package com.yuen.xiaoermei.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ShopNoticeBean {

    /**
     * code : 0
     * msg : 成功
     * data : [{"id":"3","q":"444","a":"555","state":"0","time":"1458283079"},{"id":"1","q":"123","a":"321","state":"0","time":"1458282160"}]
     */

    private String code;
    private String msg;
    /**
     * id : 3
     * q : 444
     * a : 555
     * state : 0
     * time : 1458283079
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
        private String q;
        private String a;
        private String state;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQ() {
            return q;
        }

        public void setQ(String q) {
            this.q = q;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
