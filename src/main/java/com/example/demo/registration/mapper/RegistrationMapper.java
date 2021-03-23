package com.example.demo.registration.mapper;

import com.example.demo.registration.entity.RegistrationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegistrationMapper {
    /**
     * 新增挂号信息
     */
    int addRegistration(RegistrationInfo registrationInfo);
    /**
     * 列表查询挂号信息
     */
    List<RegistrationInfo> listRegistration();
    /**
     * 查询人数
     */
    int countDoctorName(@Param("doctorName") String doctorName);
    /**
     * 修改状态
     */
    int updateStatus(@Param("rId") List<String> rId,String status);
}
