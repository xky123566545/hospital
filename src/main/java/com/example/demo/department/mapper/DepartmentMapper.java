package com.example.demo.department.mapper;

import com.example.demo.department.entity.DepartmentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper {
    /**
     * 查询科室code是否存在
     */
    int countDepartment(@Param("departmentCode") String departmentCode);
    /**
     * 新增科室的信息
     */
    int addDepartment(DepartmentInfo departmentInfo);
    /**
     * 列表查询科室信息
     */
    List<DepartmentInfo> listDepartment(Map<String,Object> param);
    /**
     * 修改科室信息
     */
    int updateDepartment(DepartmentInfo departmentInfo);
    /**
     * 删除科室信息
     */
    int deleteDepartment(@Param("departmentId") List<String> departmentId);
}
