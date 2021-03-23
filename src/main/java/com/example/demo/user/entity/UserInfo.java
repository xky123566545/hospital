package com.example.demo.user.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@ApiModel(value = "用户实体类",description = "对应用户表")
@Table(name = "user")
public class UserInfo {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 图片路径
     */
    private String imgPath;
    /**
     * 部门编号
     */
    private String departmentId;
    /**
     * 人员描述
     */
    private String userDescribe;
    /**
     * 角色名字
     */
    private String jobName;
    /**
     * 用户角色 0为管理员 1为医生 2为用户
     */
    private String role;
    /**
     * 用户状态 0为正常 1为作废
     */
    private String status;

}
