package com.my.project.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/

@Data
public class Menu implements Serializable {
    private Integer id;

    private Long menuId;

    private String menuName;

    private Long parentId;

    private Integer level;

    private Integer disable;

    private Date createTime;

    private Date updateTime;

}