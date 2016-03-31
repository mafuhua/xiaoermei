package com.yuen.xiaoermei.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/31.
 */
public class ShopPhoneNumSuccBean {

    /**
     * code : 0
     * data : [{"id":"20","tel":"15136105241","user_id":"1"}]
     * msg : 成功
     */

    private String code;
    private String msg;
    /**
     * id : 20
     * tel : 15136105241
     * user_id : 1
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
        private String tel;
        private String user_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
