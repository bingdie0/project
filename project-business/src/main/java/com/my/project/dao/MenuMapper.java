package com.my.project.dao;

import com.my.framework.boot.redis.datasource.mapper.BaseMapper;
import com.my.project.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
public interface MenuMapper extends BaseMapper<Menu> {


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
     * @param menuId 菜单id
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