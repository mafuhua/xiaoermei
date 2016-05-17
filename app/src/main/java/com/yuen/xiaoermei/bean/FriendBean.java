package com.yuen.xiaoermei.bean;

/**
 * Created by Administrator on 2016/5/17.
 */
public class FriendBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"uid":"352","nickname":"懒猫啊啦啊？黑龙江咯","avatar":"avatar/201605/1462333026-45735.jpg","img":"http://192.168.2.117/xiaoermei/upload/avatar/201605/1462333026-45735.jpg"}
     */

    private String code;
    private String msg;
    /**
     * uid : 352
     * nickname : 懒猫啊啦啊？黑龙江咯
     * avatar : avatar/201605/1462333026-45735.jpg
     * img : http://192.168.2.117/xiaoermei/upload/avatar/201605/1462333026-45735.jpg
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
        private String uid;
        private String nickname;
        private String avatar;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
