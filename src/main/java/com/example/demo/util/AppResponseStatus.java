package com.example.demo.util;

/**
 * <p>AppResponse状态码</p>
 * <p>创建日期：2018-01-10</p>
 *
 * @author 杨洲 yangzhou@neusoft.com
 */
public enum AppResponseStatus {

    /**
     * 操作成功
     */
    SUCCESS(0),

    /**
     * 服务繁忙
     */
    SERVERS_ARE_TOO_BUSY(6),

    /**
     * 未查询到数据
     */
    NOT_FOUND(5),

    /**
     * 操作失败
     */
    ERROR(-1),

    /**
     * 未登录或token非法
     */
    INVALID_TOKEN(2001),

    /**
     *
     */
    NO_PERMISSION(2003),

    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(3000),

    /**
     * 调用端异常
     */
    CLIENT_EXCEPTION(4000),

    /**
     * 请求参数非法
     */
    PARAM_EXCEPTION(4010),

    /**
     * 服务端异常
     */
    SERVER_EXCEPTION(5000),

    /**
     * 业务异常
     */
    BIZ_ERROR(10000),

    /**
     * 验证码错误
     */
    INVALID_IMAGE(6000);

    int code;

    AppResponseStatus(int code) {
        this.code = code;
    }
}
