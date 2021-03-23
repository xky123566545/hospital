package com.example.demo.uploadImg;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * @Description 图片上传控制器
 * @author xukunyuan
 * @date 2021-02-05 8:32
 */
@RestController
@RequestMapping(value = "/api1")
@Api(description = "图片上传api接口",value = "图片上传",tags = {"图片上传"})
public class UploadController {
    @Value("${spring.tengxun.accessKey}")
    private String accessKey;
    @Value("${spring.tengxun.secretKey}")
    private String secretKey;
    @Value("${spring.tengxun.bucket}")
    private String bucket;
    @Value("${spring.tengxun.bucketName}")
    private String bucketName;
    @Value("${spring.tengxun.path}")
    private String path;
    @Value("${spring.tengxun.qianzui}")
    private String qianzui;

    /**
    * @Description: 上传到云服务器
    * @Param:
    * @return:
    * @Author: xukunyuan
    * @Date: 2021/2/5
    */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public Object Upload(@RequestParam(value = "file") MultipartFile file, HttpSession session){
        if(file == null){
            return new UploadMsg(0,"文件为空",null);
        }
        String oldFileName = file.getOriginalFilename();
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + eName;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        //初始化用户身份信息（secretId，secretKey）
        COSCredentials cred = new BasicCOSCredentials(accessKey,secretKey);
        //设置bucket的区域，COS地域的简称请参照https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        //生成cos客户端
        COSClient cosClient = new COSClient(cred,clientConfig);
        // bucket的命名规则为{name}-{appid}，此处填写的存储桶名称必须为此格式
        String bucketName = this.bucketName;

        //简单文件上传，最大支持5GB，适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try{
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
            // 指定要上传到COS上的路径
            String key = "/" + this.qianzui+"/"+year+"/"+month+"/"+day+"/"+newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,key,localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return new UploadMsg(1,"上传成功",this.path + putObjectRequest.getKey());
        } catch (IOException e) {
            return new UploadMsg(-1,e.getMessage(),null);
        }finally {
            //关闭客户端（关闭后台线程）
            cosClient.shutdown();
        }

    }

    private class UploadMsg{
        public int status;
        public String msg;
        public String path;

        public UploadMsg(){
            super();
        }

        public UploadMsg(int status,String msg,String path){
            this.status = status;
            this.msg = msg;
            this.path = path;
        }
    }
}
