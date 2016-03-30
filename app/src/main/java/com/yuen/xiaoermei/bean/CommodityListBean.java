package com.yuen.xiaoermei.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class CommodityListBean {

    /**
     * code : 0
     * msg : 成功
     * data : [{"id":"1","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"aaa","pro_price":"0.00","pro_inventory":"0"},{"id":"2","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"bb","pro_price":"0.00","pro_inventory":"0"},{"id":"4","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"5","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"12312","pro_price":"0.00","pro_inventory":"0"},{"id":"6","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123123","pro_price":"0.00","pro_inventory":"0"},{"id":"7","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"12321","pro_price":"0.00","pro_inventory":"0"},{"id":"8","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"9","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"10","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"11","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"23","pro_price":"0.00","pro_inventory":"0"},{"id":"12","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"13","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"14","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"15","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"16","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"},{"id":"17","pro_img":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg","pro_name":"123","pro_price":"0.00","pro_inventory":"0"}]
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * pro_img : https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2763667293,935777619&fm=111&gp=0.jpg
     * pro_name : aaa
     * pro_price : 0.00
     * pro_inventory : 0
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
        private String pro_img;
        private String pro_name;
        private String pro_price;
        private String pro_shelves;

        public String getPro_shelves() {
            return pro_shelves;
        }

        public void setPro_shelves(String pro_shelves) {
            this.pro_shelves = pro_shelves;
        }

        private String pro_inventory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPro_img() {
            return pro_img;
        }

        public void setPro_img(String pro_img) {
            this.pro_img = pro_img;
        }

        public String getPro_name() {
            return pro_name;
        }

        public void setPro_name(String pro_name) {
            this.pro_name = pro_name;
        }

        public String getPro_price() {
            return pro_price;
        }

        public void setPro_price(String pro_price) {
            this.pro_price = pro_price;
        }

        public String getPro_inventory() {
            return pro_inventory;
        }

        public void setPro_inventory(String pro_inventory) {
            this.pro_inventory = pro_inventory;
        }
    }
}
