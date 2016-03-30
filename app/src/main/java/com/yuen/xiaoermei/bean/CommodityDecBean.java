package com.yuen.xiaoermei.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class CommodityDecBean {


    /**
     * code : 0
     * msg : 成功
     * data : {"id":"1","brand_id":"7","type_id":"1,2,8","shop_userid":"1","pro_name":"aaa","pro_shelves":"0","pro_img":[{"img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg"},{"img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=938096994,3074232342&fm=116&gp=0.jpg"},{"img":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3432927473,3545292754&fm=116&gp=0.jpg"}],"pro_price":"1.00","pro_h_price":"3.00","pro_kg":"1","pro_origin":"北京","pro_size":"M","pro_color":"红色","pro_ml":"100","pro_taste":"辣","pro_inventory":"5","state":"1","pro_content":"","time":"0"}
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * brand_id : 7
     * type_id : 1,2,8
     * shop_userid : 1
     * pro_name : aaa
     * pro_shelves : 0
     * pro_img : [{"img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg"},{"img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=938096994,3074232342&fm=116&gp=0.jpg"},{"img":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3432927473,3545292754&fm=116&gp=0.jpg"}]
     * pro_price : 1.00
     * pro_h_price : 3.00
     * pro_kg : 1
     * pro_origin : 北京
     * pro_size : M
     * pro_color : 红色
     * pro_ml : 100
     * pro_taste : 辣
     * pro_inventory : 5
     * state : 1
     * pro_content :
     * time : 0
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
        private String brand_id;
        private String type_id;
        private String shop_userid;
        private String pro_name;
        private String pro_shelves;
        private String pro_price;
        private String pro_h_price;
        private String pro_kg;
        private String pro_origin;
        private String pro_size;
        private String pro_color;
        private String pro_ml;
        private String pro_taste;
        private String pro_inventory;
        private String state;
        private String pro_content;
        private String time;
        /**
         * img : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg
         */

        private List<ProImgBean> pro_img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public String getShop_userid() {
            return shop_userid;
        }

        public void setShop_userid(String shop_userid) {
            this.shop_userid = shop_userid;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getPro_shelves() {
            return pro_shelves;
        }

        public void setPro_shelves(String pro_shelves) {
            this.pro_shelves = pro_shelves;
        }

        public String getPro_price() {
            return pro_price;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public String getPro_h_price() {
            return pro_h_price;
        }

        public void setPro_h_price(String pro_h_price) {
            this.pro_h_price = pro_h_price;
        }

        public String getPro_kg() {
            return pro_kg;
        }

        public void setPro_kg(String pro_kg) {
            this.pro_kg = pro_kg;
        }

        public String getPro_origin() {
            return pro_origin;
        }

        public void setPro_origin(String pro_origin) {
            this.pro_origin = pro_origin;
        }

        public String getPro_size() {
            return pro_size;
        }

        public void setPro_size(String pro_size) {
            this.pro_size = pro_size;
        }

        public String getPro_color() {
            return pro_color;
        }

        public void setPro_color(String pro_color) {
            this.pro_color = pro_color;
        }

        public String getPro_ml() {
            return pro_ml;
        }

        public void setPro_ml(String pro_ml) {
            this.pro_ml = pro_ml;
        }

        public String getPro_taste() {
            return pro_taste;
        }

        public void setPro_taste(String pro_taste) {
            this.pro_taste = pro_taste;
        }

        public String getPro_inventory() {
            return pro_inventory;
        }

        public void setPro_inventory(String pro_inventory) {
            this.pro_inventory = pro_inventory;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPro_content() {
            return pro_content;
        }

        public void setPro_content(String pro_content) {
            this.pro_content = pro_content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<ProImgBean> getPro_img() {
            return pro_img;
        }

        public void setPro_img(List<ProImgBean> pro_img) {
            this.pro_img = pro_img;
        }

        public static class ProImgBean {
            private String img;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
