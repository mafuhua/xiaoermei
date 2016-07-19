package com.yuen.xiaoermei.utils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ContactURL {

    /**
     * BaseURL
     */
    public static String BASE_URL = "http://114.215.210.112/xiaoermei/";
    /**
     * 登陆
     */
    public static String LOGIN_URL = BASE_URL + "shop/login";
    /**
     * 商户端所有分类
     */
    public static String SHOP_TYPE = BASE_URL + "shop/shoptype/id/";
    /**
     * 商户端所有品牌
     */
    public static String SHOP_BRAND = BASE_URL + "shop/shopbrand";
    /**
     * 配送须知
     */
    public static String SHOP_DISTRIBUTION = BASE_URL + "shop/shop_notice/id/1";
    /**
     * 开店须知
     */
    public static String SHOP_NOTICE = BASE_URL + "shop/shop_notice/id/0";
    /**
     * 商品列表
     */
    public static String COMMODITY_LIST = BASE_URL + "shop/product/shop_userid/";
    /**
     * 商品搜索
     */
    public static String COMMODITY_SEARCH_LIST = BASE_URL + "shop/product/shop_userid/";
    /**
     * 商品描述
     */
    public static String COMMODITY_DEC = BASE_URL + "shop/product_detail/product_id/";
    /**
     * 营业时间
     */
    public static String SHOP_TIME = BASE_URL + "shop/store_time/user_id/";
    /**
     * 保存时间
     */
    public static String SHOP_ADD_TIME = BASE_URL + "shop/store_add_time";
    /**
     * 添加产品
     */
    public static String SHOP_ADD_PRO = BASE_URL + "shop/add_product";
    /**
     * 修改产品
     */
    public static String SHOP_EDIT_PRO = BASE_URL + "shop/save_product";
    /**
     * 删除产品
     */
    public static String SHOP_DEL_PRO = BASE_URL + "shop/del_pro";
    /**
     * 配送距离调取
     */
    public static String SHOP_GET_DISTANCE = BASE_URL + "shop/store_distance/user_id/";
    /**
     * 配送距离保存
     */
    public static String SHOP_ADD_DISTANCE = BASE_URL + "shop/store_add_distance";
    /**
     * 配送费保存
     */
    public static String SHOP_ADD_FREIGHT = BASE_URL + "shop/store_add_freight";
    /**
     * 配送费调取
     */
    public static String SHOP_GET_FREIGHT = BASE_URL + "shop/store_freight/user_id/";
    /**
     * 公告调取
     */
    public static String SHOP_GET_GG = BASE_URL + "shop/store_gg/user_id/";
    /**
     * 公告保存
     */
    public static String SHOP_ADD_GG = BASE_URL + "shop/store_add_gg";
    /**
     * 店铺地址调取
     */
    public static String SHOP_GET_ADDRESS = BASE_URL + "shop/store_adds/user_id/";
    /**
     * 接单手机号调取
     */
    public static String SHOP_GET_PHONENUM = BASE_URL + "shop/store_tel/user_id/";
    /**
     * 店铺地址保存
     */
    public static String SHOP_ADD_ADDRESS = BASE_URL + "shop/store_add_adds";
    /**
     * 删除接单手机
     */
    public static String SHOP_DEL_PHONENUM = BASE_URL + "shop/store_del_tel/id/";
    /**
     * 短信接口
     */
    public static String SHOP_GET_DUANXIN = BASE_URL + "shop/duanxin";
    /**
     * 添加手机号
     */
    public static String SHOP_ADD_TEL = BASE_URL + "shop/store_add_tel";
    /**
     * 我的头像
     */
    public static String SHOP_STORE_TOU = BASE_URL + "shop/store_tou/user_id/";
    /**
     * 修改我的头像
     */
    public static String SHOP_EDIT_TOU = BASE_URL + "shop/store_add_tou";
    /**
     * 店铺主昵称
     */
    public static String SHOP_GET_NICK = BASE_URL + "shop/store_shop_name/user_id/";
    /**
     * 店铺主昵称保存
     */
    public static String SHOP_ADD_NICK = BASE_URL + "shop/store_add_shop_name";
    /**
     * 店铺名称调取
     */
    public static String SHOP_GET_SHOPTITILE = BASE_URL + "shop/store_shop_title/user_id/";
    /**
     * 店铺名称保存
     */
    public static String SHOP_ADD_SHOPTITILE = BASE_URL + "shop/store_add_shop_title";
    /**
     * 编辑商品
     */
    public static String SHOP_EDIT_COMMODITY = BASE_URL + "shop/product_save_detail/";
    /**
     * 帮助中心,关于我们，营销学院
     */
    public static String SHOP_GET_HELP = BASE_URL + "shop/page_s/";
    /**
     * 获取验证码
     */
    public static String SHOP_GET_YANZHENGMA = BASE_URL + "shop/yan";
    /**
     * 重新设置密码
     */
    public static String SHOP_SAVE_MIMA = BASE_URL + "shop/save_mima";
    /**
     * 获得聊天对象的信息
     */
    public static String GET_FRIEND_INFO = BASE_URL + "shop/get_name/uid/";
    /**
     * 31.订单列表
     */
    public static String GET_ORDER_LIST = BASE_URL + "shop/order_list/shop_id/";
    /**
     * 发货
     */
    public static String SEND_ORDER = BASE_URL + "shop/fa_order/order_id/";
    /**
     * 发货
     */
    public static String GET_CLENT = BASE_URL + "shop/kehu/shop_id/";
    /**
     * 发货
     */
    public static String GET_ORDERNUM = BASE_URL + "shop/order_num/shop_id/";
    /**
     * 财务管理
     */
    public static String GET_MONEY = BASE_URL + "shop/caiwu/id/";
    /**
     * 提现
     */
    public static String OUT_MONEY = BASE_URL + "shop/add_tixian"; /**
     * 提现记录
     */
    public static String OUT_MONEYLIST = BASE_URL + "shop/ti_list/shop_id/";
    /**
     * 编辑商品
     */
    // public static String SHOP_EDIT_COMMODITY = BASE_URL + "shop/product_save_detail/";

}
