package com.yuen.xiaoermei.bean;

/**
 * Created by Administrator on 2016/4/1.
 */
public class UserIconBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"id":"1","shop_img":"","shop_imgs":"http://192.168.2.120"}
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * shop_img :
     * shop_imgs : http://192.168.2.120
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
        private String shop_img;
        private String shop_imgs;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShop_img() {
            return shop_img;
        }

        public void setShop_img(String shop_img) {
            this.shop_img = shop_img;
        }

        public String getShop_imgs() {
            return shop_imgs;
        }

        public void setShop_imgs(String shop_imgs) {
            this.shop_imgs = shop_imgs;
        }
    }
}
