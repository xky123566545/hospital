package com.example.demo.picture.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "picture")
public class ImgInfo {
    /**
     *  图片编号
     */
    private String imgId;
    /**
     * 图片路径
     */
    private String imgPath;
    /**
     * 图片排序
     */
    private String sortNum;
    /**
     * 作废标记
     */
    private String isDelete;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private Date createTime;
}
