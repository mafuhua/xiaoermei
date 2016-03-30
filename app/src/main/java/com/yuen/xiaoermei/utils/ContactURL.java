package com.yuen.xiaoermei.utils;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ContactURL {
    /**
     * BaseURL
     */
    public static String BASE_URL = "http://192.168.2.128/xiaoermei/";
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
    public static String COMMODITY_LIST = BASE_URL + "shop/product/shop_userid/1/";
    /**
     * 商品描述
     */
    public static String COMMODITY_DEC = BASE_URL + "shop/product_detail/product_id/2";
}
