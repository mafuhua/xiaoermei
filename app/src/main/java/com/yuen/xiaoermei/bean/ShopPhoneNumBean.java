package com.yuen.xiaoermei.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/31.
 */
public class ShopPhoneNumBean {


    /**
     * code : 0
     * msg : 成功
     * data : [{"id":"5","user_id":"1","tel":"15821972623"},{"id":"2","user_id":"1","tel":"15821972613"},{"id":"4","user_id":"1","tel":"15821972612"},{"id":"6","user_id":"1","tel":"15821972313"},{"id":"7","user_id":"1","tel":"15821974613"},{"id":"9","user_id":"1","tel":"15821472613"}]
     */

    private String code;
    private String msg;
    /**
     * id : 5
     * user_id : 1
     * tel : 15821972623
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
        private String user_id;
        private String tel;

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

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
