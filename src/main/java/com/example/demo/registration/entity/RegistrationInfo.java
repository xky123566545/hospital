package com.example.demo.registration.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "registration")
public class RegistrationInfo {
    /**
     * 预约id
     */
    private String registrationId;
    /**
     * 预约人
     */
    private String userCode;
    /**
     * 预约人名字
     */
    private String userName;
    /**
     * 科室名称
     */
    private String departmentName;
    /**
     * 科室描述
     */
    private String departmentDescribe;
    /**
     * 医生姓名
     */
    private String doctorName;
    /**
     * 医生职业名字
     */
    private String jobName;
    /**
     * 状态 0已挂号 1已完成 2已取消
     */
    private String status;

}
