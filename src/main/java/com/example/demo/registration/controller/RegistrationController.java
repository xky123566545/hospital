package com.example.demo.registration.controller;

import com.example.demo.registration.entity.RegistrationInfo;
import com.example.demo.registration.service.RegistrationService;
import com.example.demo.util.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 预约挂号增删查
 */

@RestController
@Api(value = "预约挂号增删查",description = "预约挂号增删查",tags = {"预约挂号增删查"})
@RequestMapping("/api1")
public class RegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Resource
    private RegistrationService registrationService;

    @ApiOperation("新增挂号信息")
    @PostMapping("/addRegistration")
    public AppResponse addRegistration(@RequestBody RegistrationInfo registrationInfo){
        try{
            return registrationService.addRegistration(registrationInfo);
        }catch (Exception e){
            logger.error("新增失败");
            System.out.println(e.toString());
            throw e;
        }
    }
    @ApiOperation("查询挂号信息")
    @PostMapping("/listRegistration")
    public AppResponse listRegistration(@RequestParam(value = "pageNo",required = false) Integer pageNo,
                                        @RequestParam(value = "pageSize",required = false) Integer pageSize){
        try{
            pageNo = (pageNo == null)? 1 : pageNo;
            pageSize = (pageSize == null)? 20 : pageSize;
            return registrationService.listRegistration(pageNo,pageSize);
        }catch (Exception e){
            logger.error("查询失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    @ApiOperation("查询某科室前面还有多少人数")
    @PostMapping("/countDoctorName")
    public AppResponse countDoctorName(String doctorName){
        try{
            return registrationService.countDoctorName(doctorName);
        }catch (Exception e){
            logger.error("查询失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    @ApiOperation("修改预约状态")
    @PostMapping("/updateStatus")
    public AppResponse updateStatus(@RequestBody List<String> registrationId,String status){
        try{
            return registrationService.updateStatus(registrationId,status);
        }catch (Exception e){
            logger.error("修改失败");
            System.out.println(e.toString());
            throw e;
        }
    }
}
