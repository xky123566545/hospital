package com.example.demo.user.service;

import com.example.demo.user.entity.UserInfo;
import com.example.demo.util.AppResponse;

import java.util.List;

/**
 * @Description 用户信息增删查改
 * @author xukunyuan
 * @date 2021-03-15 21:25
 */
public interface UserService {
    /**
     * 新增用户信息
     */
    AppResponse addUser(UserInfo userInfo);
    /**
     * 列表查询用户信息
     */
    AppResponse listUser(UserInfo userInfo,Integer pageNo,Integer pageSize);
    /**
     * 删除用户信息
     */
    AppResponse deleteUser(List<String> userId);
    /**
     * 修改用户信息
     */
    AppResponse updateUser(UserInfo userInfo);
    /**
     * 修改密码
     */
    AppResponse updatePassword(String userId,String rawPassword,String newPassword);
}
