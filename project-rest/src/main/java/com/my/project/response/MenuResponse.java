package com.my.project.response;

import lombok.Data;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-20
 **/
@Data
public class MenuResponse {
    private Long menuId;

    private String menuName;

    private Long parentId;

    private Integer level;
}
