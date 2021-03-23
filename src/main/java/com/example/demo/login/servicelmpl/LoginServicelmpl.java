package com.example.demo.login.servicelmpl;

import com.example.demo.login.entity.LoginEntity;
import com.example.demo.login.mapper.LoginMapper;
import com.example.demo.login.redis.RedisClient;
import com.example.demo.login.redis.RedisConstant;
import com.example.demo.login.service.LoginService;
import com.example.demo.login.util.Base64Util;
import com.example.demo.login.util.JwtUtil;
import com.example.demo.util.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Service
public class LoginServicelmpl implements LoginService {
    @Resource
    LoginMapper loginMapper;
    @Autowired
    private RedisClient redis;
    @Value("${config.refreshToken-expireTime}")
    private String refreshTokenExpireTime;

    @Override
    public AppResponse findByUserName(LoginEntity loginEntity, HttpServletResponse response) {
        String username = loginEntity.getUsername();
        String password = loginEntity.getPassword();
        //String password = AESUtil.decrypt(rawpassword);
        //省略 账号密码验证
        LoginEntity loginEntity1 = loginMapper.findByUserName(username);
        if (loginEntity1 == null){
            return AppResponse.bizError("没有此账号，请重新输入！");
        }
        //解密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = loginEntity1.getPassword();
        if (!bCryptPasswordEncoder.matches(password,rawPassword)){
            return AppResponse.bizError("密码错误，请重新输入！");
        }
        // 清除可能存在的shiro权限信息缓存
        if (redis.hasKey(RedisConstant.PREFIX_SHIRO_CACHE + username)) {
            redis.del(RedisConstant.PREFIX_SHIRO_CACHE + username);
        }
        // 设置RefreshToken，时间戳为当前时间戳，直接设置即可（不用先删后设，会覆盖已有的RefreshToken）
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        redis.set(RedisConstant.PREFIX_SHIRO_REFRESH_TOKEN + username,currentTimeMillis,Integer.parseInt(refreshTokenExpireTime));

        // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
        String token = JwtUtil.sign(username, currentTimeMillis);
        response.setHeader("Authorization",token);
        response.setHeader("Access-Control-Expose-Headers","Authorization");
        if (token != null){
            return AppResponse.success("认证成功",token);
        }
        return AppResponse.bizError("认证失败");
    }
}
