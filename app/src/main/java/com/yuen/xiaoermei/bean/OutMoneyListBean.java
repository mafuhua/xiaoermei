package com.yuen.xiaoermei.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class OutMoneyListBean {

    /**
     * code : 0
     * data : [{"id":"1","name":"石韧","price":"1.00","shop_id":"1","time":"2016-05-27","type":"1","zhifubao":"15821972616"},{"id":"2","name":"石韧","price":"1.00","shop_id":"1","time":"2016-05-27","type":"2","zhifubao":"15821972616"},{"id":"3","name":"咯","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"吧"},{"id":"4","name":"咯","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"吧"},{"id":"5","name":"咯","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"吧"},{"id":"6","name":"咯","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"吧"},{"id":"7","name":"咯","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"吧"},{"id":"8","name":"咯","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"吧"},{"id":"9","name":"咯","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"吧"},{"id":"10","name":"简历","price":"5.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"咯"},{"id":"11","name":"简历","price":"5.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"咯"},{"id":"12","name":"简历","price":"5.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"咯"},{"id":"13","name":"简历","price":"5.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"咯"},{"id":"14","name":"简历","price":"5.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"咯"},{"id":"15","name":"简历","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"咯"},{"id":"16","name":"简历","price":"3.00","shop_id":"1","time":"2016-05-30","type":"1","zhifubao":"咯"}]
     * msg : 成功
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * name : 石韧
     * price : 1.00
     * shop_id : 1
     * time : 2016-05-27
     * type : 1
     * zhifubao : 15821972616
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
        private String name;
        private String price;
        private String shop_id;
        private String time;
        private String type;
        private String zhifubao;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getZhifubao() {
            return zhifubao;
        }

        public void setZhifubao(String zhifubao) {
            this.zhifubao = zhifubao;
        }
    }
}
