package com.example.demo.login.mapper;

import com.example.demo.login.entity.LoginEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper  {
    /**
     * 根据用户名查找用户信息
     * @param userName
     * @return
     */
    LoginEntity findByUserName(String userName);
}
