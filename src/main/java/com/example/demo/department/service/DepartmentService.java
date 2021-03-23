package com.example.demo.department.service;

import com.example.demo.department.entity.DepartmentInfo;
import com.example.demo.util.AppResponse;

import java.util.List;

public interface DepartmentService {
    /**
     * 新增科室信息
     */
    AppResponse addDepartment(DepartmentInfo departmentInfo);
    /**
     * 列表查询科室信息
     */
    AppResponse listDepartment(DepartmentInfo departmentInfo,Integer pageNo,Integer pageSize);
    /**
     * 修改科室信息
     */
    AppResponse updateDepartment(DepartmentInfo departmentInfo);
    /**
     * 删除科室信息
     */
    AppResponse deleteDepartment(List<String> departmentId);
}
