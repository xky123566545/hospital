package com.example.demo;

import com.example.demo.login.util.Base64Util;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

    public static void main(String[] args) {
        try {
            // 编写main方法直接调用上面的方法进行运行测试即可
//            Test.textToSpeech("jacob文字转换语音成功");
            String pass = "admin";
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String hashPass = bCryptPasswordEncoder.encode(pass);
            System.out.println(hashPass);
            boolean f = bCryptPasswordEncoder.matches("admin",hashPass);
            System.out.println(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("播放失败");
    }

}
