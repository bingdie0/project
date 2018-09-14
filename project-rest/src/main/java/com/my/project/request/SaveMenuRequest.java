package com.my.project.request;

import lombok.Data;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
@Data
public class SaveMenuRequest {

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父节点id
     */
    private Long parentId;
}
