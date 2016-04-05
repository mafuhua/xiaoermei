package com.yuen.xiaoermei.bean;

/**
 * Created by Administrator on 2016/4/1.
 */
public class ShopNameBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"id":"1","user_id":"1","shop_name":null}
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * user_id : 1
     * shop_name : null
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String user_id;
        private String shop_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }
    }
}
