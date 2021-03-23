package com.example.demo.user.servicelmpl;

import com.example.demo.user.entity.UserInfo;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.service.UserService;
import com.example.demo.util.AppResponse;
import com.example.demo.util.PagedData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description 用户信息增删查改
 * @author xukunyuan
 * @date 2021-03-15 21:25
 */
@Service
public class UserServicelmpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public AppResponse addUser(UserInfo userInfo) {
        //密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(userInfo.getUserPassword());
        userInfo.setUserPassword(password);
        //生产用户id
        userInfo.setUserId(UUID.randomUUID().toString().replace("-",""));
        if (userMapper.countUserCode(userInfo.getUserCode()) != 0){
            return AppResponse.bizError("用户账号已存在，请重新输入");
        }
        if (userMapper.addUser(userInfo) == 0){
            return AppResponse.bizError("新增失败");
        }
        return AppResponse.success("新增成功");
    }

    /**
     * 列表查询用户信息
     * @param userInfo
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public AppResponse listUser(UserInfo userInfo, Integer pageNo, Integer pageSize) {
        Map<String,Object> param = new HashMap<>();
        param.put("userInfo",userInfo);
        Page<UserInfo> page = PageHelper.startPage(pageNo,pageSize).doSelectPage(() -> {
            userMapper.listUser(param);
        });
        return AppResponse.success("查询成功", PagedData.getInstance(page));
    }

    @Override
    public AppResponse deleteUser(List<String> userId) {
        if (userMapper.deleteUser(userId) == 0){
            return AppResponse.bizError("删除失败");
        }
        return AppResponse.success("删除成功");
    }

    @Override
    public AppResponse updateUser(UserInfo userInfo) {
        if (userMapper.updateUser(userInfo) == 0){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }

    @Override
    public AppResponse updatePassword(String userId, String rawPassword, String newPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //查询用户密码
        String password = userMapper.getPassword(userId);
        if (password == null){
            return AppResponse.bizError("查询密码失败，用户不存在");
        }
        if (bCryptPasswordEncoder.matches(rawPassword,password) == false){
            return AppResponse.bizError("原密码输入错误,请重试");
        }
        //密码加密
        String nPassword = bCryptPasswordEncoder.encode(newPassword);
        if (userMapper.updatePassword(userId,nPassword) == 0){
            return  AppResponse.bizError("修改密码失败");
        }
        return AppResponse.success("修改密码成功");
    }

}
