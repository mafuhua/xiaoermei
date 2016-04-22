package com.yuen.xiaoermei.bean;

/**
 * Created by Administrator on 2016/4/22.
 */
public class HelpBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"id":"6","page_name":"营销学院","page_ename":"","content":"这里是营销学院！！！！","time":"1460627768"}
     */

    private String code;
    private String msg;
    /**
     * id : 6
     * page_name : 营销学院
     * page_ename :
     * content : 这里是营销学院！！！！
     * time : 1460627768
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
        private String page_name;
        private String page_ename;
        private String content;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPage_name() {
            return page_name;
        }

        public void setPage_name(String page_name) {
            this.page_name = page_name;
        }

        public String getPage_ename() {
            return page_ename;
        }

        public void setPage_ename(String page_ename) {
            this.page_ename = page_ename;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
