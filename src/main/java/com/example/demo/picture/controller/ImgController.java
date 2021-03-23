package com.example.demo.picture.controller;

import com.example.demo.picture.entity.ImgInfo;
import com.example.demo.picture.service.ImgService;
import com.example.demo.util.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页轮播图增删查改
 * @author Administrator
 */
@RestController
@Api(value = "首页轮播图增删查改",description = "首页轮播图增删查改",tags = {"首页轮播图增删查改"})
@RequestMapping("/api1")
public class ImgController {
    private final static Logger logger = LoggerFactory.getLogger(ImgController.class);
    @Resource
    private ImgService imgService;

    @ApiOperation("新增首页轮播图")
    @PostMapping("/saveImg")
    public AppResponse saveImg(@RequestBody ImgInfo imgInfo){
        try{
            return imgService.saveImg(imgInfo);
        }catch (Exception e){
            logger.error("新增失败");
            System.out.println(e.toString());
            throw e;
        }
    }

    @ApiOperation("查询首页轮播图信息")
    @PostMapping("/listImg")
    public AppResponse listImg(@RequestParam(value = "pageNo",required = false) Integer pageNo,
                               @RequestParam(value = "pageSize",required = false) Integer pageSize){
        try {
            pageNo = (pageNo == null) ? 1 : pageNo;
            pageSize = (pageSize == null) ? 20 : pageSize;
            return imgService.listImg(pageNo,pageSize);
        }
        catch (Exception e){
            logger.error("查询失败");
            System.out.println(e.toString());
            throw e;
        }
    }
    @ApiOperation("修改首页轮播图")
    @PostMapping("/updateImg")
    public AppResponse updateImg(@RequestBody ImgInfo imgInfo){
        try{
            return imgService.updateImg(imgInfo);
        }catch (Exception e){
            logger.error("修改失败");
            System.out.println(e.toString());
            throw e;
        }
    }
    @ApiOperation("删除首页轮播图")
    @PostMapping("/deleteImg")
    public AppResponse deleteImg(@RequestBody List<String> imgId){
        try{
            return imgService.deleteImg(imgId);
        }catch (Exception e){
            logger.error("删除失败");
            System.out.println(e.toString());
            throw e;
        }
    }
}
