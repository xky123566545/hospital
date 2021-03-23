package com.example.demo.login.util;

import com.google.gson.Gson;

/**
 * <p>json工具类</p>
 * <p>创建日期：2018-01-09</p>
 *
 * @author 杨洲 yangzhou@neusoft.com
 */
public class JsonUtils {

    private JsonUtils() {
    }

    private static Gson gson = new Gson();

    /**
     * json转换为对象方法
     *
     * @param json  json字符串
     * @param clazz 对象class
     * @return 转换的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }


    /**
     * 对象转换为json字符串
     *
     * @param obj 需要转换的对象
     * @return json字符串
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

}
