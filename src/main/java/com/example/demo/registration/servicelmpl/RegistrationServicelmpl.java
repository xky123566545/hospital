package com.example.demo.registration.servicelmpl;

import com.example.demo.registration.entity.RegistrationInfo;
import com.example.demo.registration.mapper.RegistrationMapper;
import com.example.demo.registration.service.RegistrationService;
import com.example.demo.util.AppResponse;
import com.example.demo.util.PagedData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class RegistrationServicelmpl implements RegistrationService {
    @Resource
    private RegistrationMapper registrationMapper;
    @Override
    public AppResponse addRegistration(RegistrationInfo registrationInfo) {
        //生成挂号编号
        registrationInfo.setRegistrationId(UUID.randomUUID().toString().replace("-",""));
        if (registrationMapper.addRegistration(registrationInfo) == 0){
            return AppResponse.success("挂号失败");
        }
        return AppResponse.success("挂号成功");
    }

    @Override
    public AppResponse listRegistration(Integer pageNo, Integer pageSize) {
        Page<RegistrationInfo> page = PageHelper.startPage(pageNo,pageSize).doSelectPage( () ->{
            registrationMapper.listRegistration();
        });
        return AppResponse.success("查询成功", PagedData.getInstance(page));
    }

    @Override
    public AppResponse countDoctorName(String doctorName) {
        return AppResponse.success("查询成功，前面还有"+registrationMapper.countDoctorName(doctorName) + "人");
    }

    @Override
    public AppResponse updateStatus(List<String> rId,String status) {
        if(registrationMapper.updateStatus(rId,status) == 0){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }
}
