package com.example.demo.user.controller;

import com.example.demo.user.entity.UserInfo;
import com.example.demo.user.service.UserService;
import com.example.demo.util.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Description 用户信息增删查改
 * @author xukunyuan
 * @date 2021-03-15 21:25
 */
@RestController
@RequestMapping("/api1")
@Api(value = "用户信息增删查改",description = "用户信息增删查改api",tags = {"用户信息增删查改"})
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;
    /**
     * @Description 新增用户信息
     * @author xukunyuan
     * @date 2021-03-15 21:27
     */
    @ApiOperation("新增用户信息")
    @PostMapping("/addUser")
    public AppResponse addUser(@RequestBody UserInfo userInfo){
        try{
            return userService.addUser(userInfo);
        }catch (Exception e){
            logger.error("新增失败");
            System.out.println(e.toString());
            throw e;
        }
    }
    @ApiOperation("列表查询角色信息")
    @PostMapping("/listUser")
    public AppResponse listUser(@RequestBody UserInfo userInfo,
                                @RequestParam(value = "pageNo",required = false) Integer pageNo,
                                @RequestParam(value = "pageSize",required = false) Integer pageSize){

       try{
           pageNo = (pageNo == null)? 1 : pageNo;
           pageSize = (pageSize == null)? 20 : pageSize;
           return userService.listUser(userInfo,pageNo,pageSize);
       }catch (Exception e){
           logger.error("查询失败");
           System.out.println(e.toString());
           throw e;
       }
    }

    @ApiOperation("删除用户信息")
    @PostMapping("/deleteUser")
    public AppResponse deleteUser(@RequestBody List<String> userId){
        try{
            return userService.deleteUser(userId);
        }catch (Exception e){
            logger.error("删除失败");
            System.out.println(e.toString());
            throw e;
        }
    }
    @ApiOperation("修改用户信息")
    @PostMapping("/updateUser")
    public AppResponse updateUser(@RequestBody UserInfo userInfo){
        try{
          return  userService.updateUser(userInfo);
        }catch (Exception e){
            logger.error("修改失败");
            e.toString();
            throw e;
        }
    }
    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public AppResponse updatePassword(String userId,String rawPassword,String newPassword){
        try{
            return userService.updatePassword(userId,rawPassword,newPassword);
        }catch (Exception e){
            logger.error("修改失败");
            System.out.println(e.toString());
            throw e;
        }
    }

}
