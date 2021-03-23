package com.example.demo.login.service;

import com.example.demo.login.entity.LoginEntity;
import com.example.demo.util.AppResponse;

import javax.servlet.http.HttpServletResponse;

public interface LoginService{
    /**
     * 根据用户名查找用户信息
     * @param loginEntity
     * @return
     */
    AppResponse findByUserName(LoginEntity loginEntity, HttpServletResponse response);
}
