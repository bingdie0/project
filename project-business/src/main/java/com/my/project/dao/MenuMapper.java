package com.my.project.dao;

import com.my.project.entity.Menu;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据菜单id查询菜单信息
     *
     * @param menuId
     * @return:
     * @author: Mr.WangJie
     */
    Menu getMenuById(@Param("menuId") Long menuId);

    /**
     * 更新菜单数据
     *
     * @param menu 菜单
     * @return:
     * @author: Mr.WangJie
     */
    void updateMenuById(Menu menu);

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     * @return:
     * @author: Mr.WangJie
     */
    void deleteMenuById(@Param("menuId") Long menuId);

    /**
     * 删除菜单
     *
     * @param parentId 父id
     * @return:
     * @author: Mr.WangJie
     */
    void deleteMenuByParentId(@Param("parentId") Long parentId);

}