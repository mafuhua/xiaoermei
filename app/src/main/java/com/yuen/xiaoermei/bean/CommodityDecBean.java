package com.yuen.xiaoermei.bean;

/**
 * Created by Administrator on 2016/3/25.
 */
public class CommodityDecBean {

    /**
     * code : 0
     * data : {"brand_id":"7","id":"2","pro_color":"","pro_content":"","pro_h_price":"0.00","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_inventory":"0","pro_kg":"0","pro_ml":"","pro_name":"bb","pro_origin":"","pro_price":"0.00","pro_shelves":"0","pro_size":"","pro_taste":"","shop_userid":"1","state":"1","time":"0","type_id":"1,2,8"}
     * msg : 成功
     */

    private String code;
    /**
     * brand_id : 7
     * id : 2
     * pro_color :
     * pro_content :
     * pro_h_price : 0.00
     * pro_img : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg
     * pro_inventory : 0
     * pro_kg : 0
     * pro_ml :
     * pro_name : bb
     * pro_origin :
     * pro_price : 0.00
     * pro_shelves : 0
     * pro_size :
     * pro_taste :
     * shop_userid : 1
     * state : 1
     * time : 0
     * type_id : 1,2,8
     */

    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private String brand_id;
        private String id;
        private String pro_color;
        private String pro_content;
        private String pro_h_price;
        private String pro_img;
        private String pro_inventory;
        private String pro_kg;
        private String pro_ml;
        private String pro_name;
        private String pro_origin;
        private String pro_price;
        private String pro_shelves;
        private String pro_size;
        private String pro_taste;
        private String shop_userid;
        private String state;
        private String time;
        private String type_id;

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
            this.brand_id = brand_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPro_color() {
            return pro_color;
        }

        public void setPro_color(String pro_color) {
            this.pro_color = pro_color;
        }

        public String getPro_content() {
            return pro_content;
        }

        public void setPro_content(String pro_content) {
            this.pro_content = pro_content;
        }

        public String getPro_h_price() {
            return pro_h_price;
        }

        public void setPro_h_price(String pro_h_price) {
            this.pro_h_price = pro_h_price;
        }

        public String getPro_img() {
            return pro_img;
        }

        public void setPro_img(String pro_img) {
            this.pro_img = pro_img;
        }

        public String getPro_inventory() {
            return pro_inventory;
        }

        public void setPro_inventory(String pro_inventory) {
            this.pro_inventory = pro_inventory;
        }

        public String getPro_kg() {
            return pro_kg;
        }

        public void setPro_kg(String pro_kg) {
            this.pro_kg = pro_kg;
        }

        public String getPro_ml() {
            return pro_ml;
        }

        public void setPro_ml(String pro_ml) {
            this.pro_ml = pro_ml;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getPro_origin() {
            return pro_origin;
        }

        public void setPro_origin(String pro_origin) {
            this.pro_origin = pro_origin;
        }

        public String getPro_price() {
            return pro_price;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public String getPro_shelves() {
            return pro_shelves;
        }

        public void setPro_shelves(String pro_shelves) {
            this.pro_shelves = pro_shelves;
        }

        public String getPro_size() {
            return pro_size;
        }

        public void setPro_size(String pro_size) {
            this.pro_size = pro_size;
        }

        public String getPro_taste() {
            return pro_taste;
        }

        public void setPro_taste(String pro_taste) {
            this.pro_taste = pro_taste;
        }

        public String getShop_userid() {
            return shop_userid;
        }

        public void setShop_userid(String shop_userid) {
            this.shop_userid = shop_userid;
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

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }
    }
}
