package com.yuen.xiaoermei.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public class CommodityEditDecBean implements Serializable {

    /**
     * code : 0
     * msg : 成功
     * data : {"id":"1","brand_id":"1","type_id":"1,2,8","shop_user_id":"1","pro_name":"1","pro_shelves":"0","pro_img":"product/201604/1460346615-89747.jpg,product/201604/1460346615-22972.jpg,product/201604/1460346616-92798.jpg","pro_price":"4.00","pro_h_price":"5.00","pro_kg":"6","pro_origin":"7","pro_size":"8","pro_color":"9","pro_ml":"10","pro_taste":"11","pro_inventory":"1","state":"1","pro_content":"12","time":"0","type0":"家用电器","type1":"大家电","type2":"平板电视","brand_name":"统一","pro_imgs":[{"img":"http://192.168.2.128/xiaoermei/upload/product/201604/1460346615-89747.jpg"},{"img":"http://192.168.2.128/xiaoermei/upload/product/201604/1460346615-22972.jpg"},{"img":"http://192.168.2.128/xiaoermei/upload/product/201604/1460346616-92798.jpg"}]}
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * brand_id : 1
     * type_id : 1,2,8
     * shop_user_id : 1
     * pro_name : 1
     * pro_shelves : 0
     * pro_img : product/201604/1460346615-89747.jpg,product/201604/1460346615-22972.jpg,product/201604/1460346616-92798.jpg
     * pro_price : 4.00
     * pro_h_price : 5.00
     * pro_kg : 6
     * pro_origin : 7
     * pro_size : 8
     * pro_color : 9
     * pro_ml : 10
     * pro_taste : 11
     * pro_inventory : 1
     * state : 1
     * pro_content : 12
     * time : 0
     * type0 : 家用电器
     * type1 : 大家电
     * type2 : 平板电视
     * brand_name : 统一
     * pro_imgs : [{"img":"http://192.168.2.128/xiaoermei/upload/product/201604/1460346615-89747.jpg"},{"img":"http://192.168.2.128/xiaoermei/upload/product/201604/1460346615-22972.jpg"},{"img":"http://192.168.2.128/xiaoermei/upload/product/201604/1460346616-92798.jpg"}]
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

    public static class DataBean implements Serializable {
        private String id;
        private String brand_id;
        private String type_id;
        private String shop_user_id;
        private String pro_name;
        private String pro_shelves;
        private String pro_img;
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
        private String type0;
        private String type1;
        private String type2;
        private String type3;
        private String brand_name;
        /**
         * img : http://192.168.2.128/xiaoermei/upload/product/201604/1460346615-89747.jpg
         */

        private List<ProImgsBean> pro_imgs;

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

        public String getShop_user_id() {
            return shop_user_id;
        }

        public void setShop_user_id(String shop_user_id) {
            this.shop_user_id = shop_user_id;
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

        public String getPro_img() {
            return pro_img;
        }

        public void setPro_img(String pro_img) {
            this.pro_img = pro_img;
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

        public String getType0() {
            return type0;
        }

        public void setType0(String type0) {
            this.type0 = type0;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public void setType3(String type3) {
            this.type3 = type3;
        }

        public String getType3() {
            return type3;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public List<ProImgsBean> getPro_imgs() {
            return pro_imgs;
        }

        public void setPro_imgs(List<ProImgsBean> pro_imgs) {
            this.pro_imgs = pro_imgs;
        }

        public static class ProImgsBean implements Serializable {
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
