package com.my.project.service;

import com.my.project.dto.TreeNode;
import com.my.project.entity.Menu;

import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
public interface MenuService {

    /**
     * 保存菜单
     *
     * @param menu 菜单信息
     * @author: Mr.WangJie
     */
    void saveMenu(Menu menu);

    /**
     * 查询菜单tree
     *
     * @return: 菜单列表
     * @author: Mr.WangJie
     */
    List<TreeNode> findMenuTree();

    /**
     * 查询菜单列表
     *
     * @return: 菜单列表
     * @author: Mr.WangJie
     */
    List<Menu> findMenuList();

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
    void deleteMenuById(Long menuId);
}
