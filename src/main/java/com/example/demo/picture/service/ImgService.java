package com.example.demo.picture.service;

import com.example.demo.picture.entity.ImgInfo;
import com.example.demo.util.AppResponse;

import java.util.List;

public interface ImgService {
    AppResponse saveImg(ImgInfo imgInfo);
    AppResponse listImg(Integer pageNo,Integer pageSize);
    AppResponse updateImg(ImgInfo imgInfo);
    AppResponse deleteImg(List<String> imgId);
}
