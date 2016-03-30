package com.yuen.xiaoermei.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ShopTypeBean {

    /**
     * code : 0
     * msg : 成功
     * data : [{"id":"1","nav_name":"家用电器","parent_id":"0"},{"id":"92","nav_name":"手机、数码","parent_id":"0"},{"id":"173","nav_name":"电脑、办公","parent_id":"0"},{"id":"264","nav_name":"家居、家具、家装、厨具","parent_id":"0"},{"id":"368","nav_name":"男装、女装","parent_id":"0"},{"id":"491","nav_name":"个护化妆、清洁用品","parent_id":"0"},{"id":"541","nav_name":"鞋靴、箱包、钟表、奢侈品","parent_id":"0"},{"id":"631","nav_name":"运动户外","parent_id":"0"},{"id":"742","nav_name":"母婴、玩具乐器","parent_id":"0"},{"id":"836","nav_name":"食品、酒类、生鲜","parent_id":"0"}]
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * nav_name : 家用电器
     * parent_id : 0
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
        private String nav_name;
        private String parent_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNav_name() {
            return nav_name;
        }

        public void setNav_name(String nav_name) {
            this.nav_name = nav_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }
    }
}
