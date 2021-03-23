package com.example.demo.registration.service;

import com.example.demo.registration.entity.RegistrationInfo;
import com.example.demo.util.AppResponse;

import java.util.List;

public interface RegistrationService {
    /**
     * 新增挂号信息
     */
    AppResponse addRegistration(RegistrationInfo registrationInfo);
    /**
     * 查询挂号信息
     */
    AppResponse listRegistration(Integer pageNo,Integer pageSize);
    /**
     * 查询前面的人数
     */
    AppResponse countDoctorName(String doctorName);
    /**
     * 修改预约状态
     */
    AppResponse updateStatus(List<String> rId,String status);
}
