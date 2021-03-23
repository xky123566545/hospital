package com.example.demo.department.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "department")
public class DepartmentInfo {
    /**
     * 科室id
     */
    private String departmentId;
    /**
     * 科室楼层
     */
    private  String departmentCode;
    /**
     * 科室名称
     */
    private String departmentName;
    /**
     * 科室描述
     */
    private String departmentDescribe;
    /**
     * 科室状态 0使用 1取消
     */
    private String status;

}
