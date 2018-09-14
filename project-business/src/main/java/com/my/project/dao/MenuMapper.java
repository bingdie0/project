package com.my.project.dao;

import com.my.project.entity.Menu;

import java.util.List;

public interface MenuMapper {

    /**
     * 保存菜单
     *
     * @param menu 菜单信息
     * @author: Mr.WangJie
     */
    void insertSelective(Menu menu);

    /**
     * 查询菜单列表
     *
     * @return: 菜单列表
     * @author: Mr.WangJie
     */
    List<Menu> findMenuList();
}