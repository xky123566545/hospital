package com.example.demo.util;


import com.example.demo.login.util.JsonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * <p>系统统一返回格式</p>
 * <p>创建日期：2018-01-01</p>
 *
 * @author 杨洲 yangzhou@neusoft.com
 */
@ApiModel(description = "系统统一返回格式")
public class AppResponse<T> implements Serializable {

    /**
     * 返回编码
     */
    @ApiModelProperty("返回编码")
    private int code;

    /**
     * 消息描述
     */
    @ApiModelProperty("消息描述")
    private String msg;

    /**
     * 返回内容
     */
    @ApiModelProperty("返回内容")

    private T data;

    @ApiModelProperty("返回图片")

    private T imagePath;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public T getImagePath() {
        return imagePath;
    }
    /**
     * 构建操作成功返回体
     *
     * @return 结果
     */
    public static AppResponse success() {
        return builder().code(AppResponseStatus.SUCCESS).build();
    }

    /**
     * 构建操作成功返回体
     * <p>
     * * @param msg  操作说明
     *
     * @return 结果
     */
    public static AppResponse success(String msg) {
        return builder().code(AppResponseStatus.SUCCESS).msg(msg).build();
    }

    /**
     * 构建操作成功返回体
     *
     * @param msg  操作说明
     * @param data 返回结果
     * @return 结果
     */
    public static <T> AppResponse success(String msg, T data) {
        return builder().code(AppResponseStatus.SUCCESS).msg(msg).data(data).build();
    }

    /**
     * 构建操作成功返回体
     *
     * @param msg  操作说明
     * @param imagePath 返回结果
     * @return 结果
     */
    public static <T> AppResponse success(String msg, T date, T imagePath) {
        return builder().code(AppResponseStatus.SUCCESS).msg(msg).imagePath(imagePath).build();
    }

    /**
     * 构建没有查询到数据返回体
     *
     * @return 结果
     */
    public static AppResponse notFound() {
        return builder().code(AppResponseStatus.NOT_FOUND).build();
    }

    /**
     * 构建没有查询到数据返回体
     *
     * @param msg 结果说明
     * @return 结果
     */
    public static AppResponse notFound(String msg) {
        return builder().code(AppResponseStatus.NOT_FOUND).msg(msg).build();
    }

    /**
     * 构建访问被拒绝返回体
     *
     * @return 结果
     */
    public static AppResponse serversAreTooBusy(String msg) {
        return builder().code(AppResponseStatus.SERVERS_ARE_TOO_BUSY).msg(msg).build();
    }

    /**
     * 构建无权限错误返回体
     *
     * @param msg 错误说明
     * @return 结果
     */
    public static AppResponse noPermission(String msg) {
        return builder().code(AppResponseStatus.NO_PERMISSION).build();
    }

    /**
     * 构建未知异常返回体
     *
     * @return 返回体
     */
    public static AppResponse unknownError() {
        return builder().code(AppResponseStatus.UNKNOWN_EXCEPTION).build();
    }

    /**
     * 构建未知异常返回体
     *
     * @return 返回体
     */
    public static AppResponse unknownError(String msg) {
        return builder().code(AppResponseStatus.UNKNOWN_EXCEPTION).msg(msg).build();
    }

    /**
     * 构建调用端异常返回体
     *
     * @return 返回体
     */
    public static AppResponse clientError() {
        return builder().code(AppResponseStatus.CLIENT_EXCEPTION).build();
    }

    /**
     * 构建调用端异常返回体
     *
     * @return 返回体
     */
    public static AppResponse clientError(String msg) {
        return builder().code(AppResponseStatus.CLIENT_EXCEPTION).msg(msg).build();
    }

    /**
     * 构建调用端参数错误返回体
     *
     * @return 返回体
     */
    public static AppResponse paramError() {
        return builder().code(AppResponseStatus.PARAM_EXCEPTION).build();
    }

    /**
     * 构建调用端参数错误返回体
     *
     * @return 返回体
     */
    public static AppResponse paramError(String msg) {
        return builder().code(AppResponseStatus.PARAM_EXCEPTION).msg(msg).build();
    }

    /**
     * 构建服务端参数错误返回体
     *
     * @return 返回体
     */
    public static AppResponse serverError() {
        return builder().code(AppResponseStatus.SERVER_EXCEPTION).build();
    }

    /**
     * 构建服务端参数错误返回体
     *
     * @param msg 错误描述
     * @return 返回体
     */
    public static AppResponse serverError(String msg) {
        return builder().code(AppResponseStatus.SERVER_EXCEPTION).msg(msg).build();
    }


    /**
     * 构建业务异常返回体
     *
     * @param msg 异常描述
     * @return
     */
    public static AppResponse bizError(String msg) {
        return builder().code(AppResponseStatus.ERROR).msg(msg).build();
    }

    /**
     * 构建数据版本变化异常返回体
     *
     * @param msg 异常描述
     * @return
     */
    public static AppResponse versionError(String msg) {
        return builder().code(AppResponseStatus.ERROR).msg(msg).build();
    }

    /**
     * 验证码校验失败异常返回体
     *
     * @param msg 异常描述
     * @return
     */
    public static AppResponse invalidImage(String msg) {
        return builder().code(AppResponseStatus.INVALID_IMAGE).msg(msg).build();
    }

    private AppResponse() {
    }

    private AppResponse(int code, String msg, T data, T imagePath) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.imagePath = imagePath;
    }

    public static <T> AppResponseBuilder builder() {
        return new AppResponseBuilder<T>();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public String toJson() {
        return JsonUtils.toJson(this);
    }

    public static class AppResponseBuilder<T> {

        private int code;

        private String msg;

        private T data;

        private T imagePath;

        public AppResponse build() {
            AppResponse appResponse = new AppResponse<>(this.code, this.msg, this.data,this.imagePath);
            return appResponse;
        }

        public AppResponseBuilder code(AppResponseStatus status) {
            this.code = status.code;
            return this;
        }

        public AppResponseBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public AppResponseBuilder data(T data) {
            this.data = data;
            return this;
        }
        public AppResponseBuilder imagePath(T imagePath) {
            this.imagePath = imagePath;
            return this;
        }
    }
}

