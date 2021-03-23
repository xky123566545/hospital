package com.example.demo.login.controller;

import com.example.demo.login.entity.LoginEntity;
import com.example.demo.login.mapper.LoginMapper;
import com.example.demo.login.redis.RedisClient;
import com.example.demo.login.redis.RedisConstant;
import com.example.demo.login.service.LoginService;
import com.example.demo.login.util.JwtConstant;
import com.example.demo.login.util.JwtUtil;
import com.example.demo.util.AppResponse;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @Description 登录
 * @author xukunyuan
 * @date 2021-02-01 16:57
 */
@RestController
@RequestMapping("/login")
@Api(description = "登录接口api",value = "pc端app端登录接口",tags = {"pc端app端登录接口"})
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    LoginService loginService;
    @Resource
    LoginMapper loginMapper;
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    private RedisClient redis;

    @PostMapping(value ="/auth")
    public AppResponse login(@RequestBody LoginEntity loginEntity, HttpServletResponse response){
       try{
           return loginService.findByUserName(loginEntity,response);
       }catch (Exception e){
           logger.error("登录认证失败");
           System.out.println(e.toString());
           throw e;
       }
    }

    @PostMapping(value = "/logout")
    public AppResponse logout(){
        try{
            String token = "";
            //获取头部信息
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                if ("Authorization".equalsIgnoreCase(key)){
                    token = value;
                }
            }
            //校验token
            if (StringUtils.isBlank(token)){
                return AppResponse.bizError("token失效或不正确！");
            }
            String username = JwtUtil.getClaim(token, JwtConstant.ACCOUNT);
            if (StringUtils.isBlank(username)){
                return AppResponse.bizError("token失效或不正确");
            }
            //清除shiro权限信息缓存
            if (redis.hasKey(RedisConstant.PREFIX_SHIRO_CACHE + username)){
                redis.del(RedisConstant.PREFIX_SHIRO_CACHE + username);
            }
            //清除RefreshToken
            redis.del(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + username);
            return AppResponse.success("登出成功！");
        }catch (Exception e){
            logger.error("登出失败，请重试！");
            System.out.println(e.toString());
            throw e;
        }
    }
    @PostMapping("/current")
    public AppResponse current(){
        try{
            LoginEntity loginEntity = new LoginEntity();
            Subject subject = SecurityUtils.getSubject();
            if (subject != null){
                //String token = (String) subject.getPrincipal();
                String token = "";
                //获取头部信息
                Enumeration<String> headerNames = request.getHeaderNames();
                while (headerNames.hasMoreElements()){
                    String key = (String) headerNames.nextElement();
                    String value = request.getHeader(key);
                    if ("Authorization".equalsIgnoreCase(key)){
                        token = value;
                        break;
                    }
                }
                if(StringUtils.isNotBlank(token)){
                    String account = JwtUtil.getClaim(token, JwtConstant.ACCOUNT);
                    if (StringUtils.isNotBlank(account)){
                        loginEntity = loginMapper.findByUserName(account);
                    }
                }
            }
            return AppResponse.success("查询成功",loginEntity);
        }catch (Exception e){
            logger.error("查询失败");
            System.out.println(e.toString());
            throw e;
        }
    }
}
