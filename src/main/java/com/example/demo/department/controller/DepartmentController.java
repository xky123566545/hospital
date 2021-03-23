package com.example.demo.department.controller;

import com.example.demo.department.entity.DepartmentInfo;
import com.example.demo.department.service.DepartmentService;
import com.example.demo.util.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 科室信息增删查改
 */
@RestController
@Api(value = "科室信息增删查改",description = "科室信息增删查改",tags = {"科室信息增删查改"})
@RequestMapping("/api1")
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Resource
    private DepartmentService departmentService;

    @ApiOperation("新增科室信息")
    @PostMapping("/addDepartment")
    public AppResponse addDepartment(@RequestBody DepartmentInfo departmentInfo){
        try{
            return departmentService.addDepartment(departmentInfo);
        }catch (Exception e){
            logger.error("新增失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    @ApiOperation("查询科室信息")
    @PostMapping("/listDepartment")
    public AppResponse listDepartment(@RequestBody DepartmentInfo departmentInfo,
                                      @RequestParam(value = "pageNo",required = false) Integer pageNo,
                                      @RequestParam(value = "pageSize",required = false) Integer pageSize){
        pageNo = (pageNo == null)? 1 : pageNo;
        pageSize = (pageSize == null)? 20 : pageSize;
        return departmentService.listDepartment(departmentInfo,pageNo,pageSize);
    }
    @ApiOperation("修改科室信息")
    @PostMapping("/updateDepartment")
    public AppResponse updateDepartment(@RequestBody DepartmentInfo departmentInfo){
        try{
            return departmentService.updateDepartment(departmentInfo);
        }catch (Exception e){
            logger.error("修改失败");
            System.out.println(e.toString());
            throw e;
        }
    }
    @ApiOperation("删除科室信息")
    @PostMapping("/deleteDepartment")
    public AppResponse deleteDepartment(@RequestBody List<String> departmentId){
        try{
            return departmentService.deleteDepartment(departmentId);
        }catch (Exception e){
            logger.error("修改失败");
            System.out.println(e.toString());
            throw e;
        }
    }
}
