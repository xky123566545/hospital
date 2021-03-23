package com.example.demo.picture.mapper;

import com.example.demo.picture.entity.ImgInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImgMapper {
    /**
     * 新增首页轮播图
     */
    int saveImg(ImgInfo imgInfo);
    /**
     * 列表查询图片详情
     */
    List<ImgInfo> listImg();
    /**
     * 修改图片信息
     */
    int updateImg(ImgInfo imgInfo);
    /**
     * 删除图片信息
     */
    int deleteImg(@Param("imgId") List<String> imgId);
}
