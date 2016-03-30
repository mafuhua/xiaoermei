package com.yuen.xiaoermei.bean;

/**
 * Created by Administrator on 2016/3/26.
 */
public class ShopTimeBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"id":"2","user_id":"2","shop_time":"8","shop_etime":"23"}
     */

    private String code;
    private String msg;
    /**
     * id : 2
     * user_id : 2
     * shop_time : 8
     * shop_etime : 23
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
        private String shop_time;
        private String shop_etime;

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

        public String getShop_time() {
            return shop_time;
        }

        public void setShop_time(String shop_time) {
            this.shop_time = shop_time;
        }

        public String getShop_etime() {
            return shop_etime;
        }

        public void setShop_etime(String shop_etime) {
            this.shop_etime = shop_etime;
        }
    }
}
