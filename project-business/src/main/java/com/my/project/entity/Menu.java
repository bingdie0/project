package com.my.project.entity;

import com.my.framework.boot.redis.datasource.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class Menu extends BaseEntity implements Serializable {

    private Long menuId;

    private String menuName;

    private Long parentId;

    private Integer level;

}