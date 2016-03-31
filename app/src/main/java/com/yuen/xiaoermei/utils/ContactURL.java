package com.yuen.xiaoermei.utils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ContactURL {
    /**
     * BaseURL
     */
    public static String BASE_URL = "http://192.168.2.120/xiaoermei/";
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
}
