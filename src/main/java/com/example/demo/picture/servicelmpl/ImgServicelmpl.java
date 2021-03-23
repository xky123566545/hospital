package com.example.demo.picture.servicelmpl;

import com.example.demo.picture.entity.ImgInfo;
import com.example.demo.picture.mapper.ImgMapper;
import com.example.demo.picture.service.ImgService;
import com.example.demo.util.AppResponse;
import com.example.demo.util.PagedData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ImgServicelmpl implements ImgService {
    @Resource
    private ImgMapper imgMapper;

    /**
     * 新增首页轮播图信息
     * @param imgInfo
     * @return
     */
    @Override
    public AppResponse saveImg(ImgInfo imgInfo) {
        //生成图片编号
        imgInfo.setImgId(UUID.randomUUID().toString().replace("-",""));
        if (imgMapper.saveImg(imgInfo) == 0){
            return AppResponse.bizError("新增失败");
        }
        return AppResponse.success("新增成功");
    }

    @Override
    public AppResponse listImg(Integer pageNo, Integer pageSize) {
        Page<ImgInfo> page = PageHelper.startPage(pageNo,pageSize).doSelectPage( () ->{
            imgMapper.listImg();
        });
        return AppResponse.success("查询成功", PagedData.getInstance(page));
    }

    @Override
    public AppResponse updateImg(ImgInfo imgInfo) {
        if (imgMapper.updateImg(imgInfo) == 0){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }

    @Override
    public AppResponse deleteImg(List<String> imgId) {
        if (imgMapper.deleteImg(imgId) == 0){
            return AppResponse.bizError("删除失败");
        }
        return AppResponse.success("删除成功");
    }


}
