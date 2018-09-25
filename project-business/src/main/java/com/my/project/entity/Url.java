package com.my.project.entity;

import com.my.framework.boot.redis.datasource.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
@Data
public class Url extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String url;

    private String title;

    private String listPic;

    private Double price;

    private String shopName;

    private String shopLevelBanner;

    private Long menuId;

}