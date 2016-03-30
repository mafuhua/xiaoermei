package com.yuen.xiaoermei.bean;

/**
 * Created by Administrator on 2016/3/24.
 */
public class LoginBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"id":"1","name":"admin","password":"e10adc3949ba59abbe56e057f20f883e","tel":"15821972617","type":"0","state":"1","time":"1458120212"}
     */

    private String code;
    private String msg;
    /**
     * id : 1
     * name : admin
     * password : e10adc3949ba59abbe56e057f20f883e
     * tel : 15821972617
     * type : 0
     * state : 1
     * time : 1458120212
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
        private String name;
        private String password;
        private String tel;
        private String type;
        private String state;
        private String time;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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
    }
}
