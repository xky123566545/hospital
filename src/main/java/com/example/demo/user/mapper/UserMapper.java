package com.example.demo.user.mapper;

import com.example.demo.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 用户信息增删查改
 * @author xukunyuan
 * @date 2021-03-15 21:25
 */
@Mapper
public interface UserMapper {
    /**
     * 新增用户信息
     */
    int addUser(UserInfo userInfo);
    /**
     * 查询账号是否存在
     */
    int countUserCode(String userCode);
    /**
     * 列表查询用户信息
     */
    List<UserInfo> listUser(Map<String,Object> param);
    /**
     * 删除用户
     */
    int deleteUser(@Param("userId") List<String> userId);
    /**
     * 修改用户
     */
    int updateUser(UserInfo userInfo);
    /**
     * 获取当前密码
     */
    String getPassword(String userId);
    /**
     * 修改密码
     */
    int updatePassword(String userId,String password);
}
