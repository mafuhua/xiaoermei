package com.yuen.xiaoermei.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 */
public class ConversationListBean implements Serializable{

    /**
     * code : 0
     * msg : 成功
     * data : [{"uid":"367","nickname":"顶你个肺","avatar":"avatar/201607/1469088000-96583.jpg"},{"uid":"362","nickname":"哎呀我去","avatar":"avatar/201607/1468894519-86684.jpg"},{"uid":"357","nickname":"awoke patios ","avatar":"avatar/201607/1469001618-54445.jpg"}]
     */

    private String code;
    private String msg;
    /**
     * uid : 367
     * nickname : 顶你个肺
     * avatar : avatar/201607/1469088000-96583.jpg
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

    public static class DataBean implements Serializable{
        private String uid;
        private String nickname;
        private String avatar;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
