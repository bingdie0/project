package com.my.project.service.impl;

import com.my.framework.utils.UUIDGenerator;
import com.my.project.dao.MenuMapper;
import com.my.project.dto.TreeNode;
import com.my.project.entity.Menu;
import com.my.project.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void saveMenu(Menu menu) {
        menu.setMenuId(UUIDGenerator.getNextId());
        if (Objects.isNull(menu.getParentId())) {
            menu.setLevel(1);
        } else {
            Menu parentMenu = menuMapper.getMenuById(menu.getParentId());
            if (Objects.isNull(parentMenu)) {

            }
            menu.setLevel(parentMenu.getLevel() + 1);
        }
        menuMapper.insertSelective(menu);
    }

    @Override
    public List<TreeNode> findMenuList() {
        List<Menu> menuList = menuMapper.findMenuList();
        return getTree(menuList);
    }

    @Override
    public void updateMenuById(Menu menu) {
        menuMapper.updateMenuById(menu);
    }

    @Override
    @Transactional
    public void deleteMenuById(Long menuId) {
        menuMapper.deleteMenuById(menuId);
        menuMapper.deleteMenuByParentId(menuId);
    }

    private List<TreeNode> getTree(List<Menu> list) {
        // 如果当前节点的parentId == null 则为跟节点
        List<TreeNode> trees = new ArrayList<>();
       list.forEach(menu -> {
           if (Objects.isNull(menu.getParentId())) {
               trees.add(findChild(packageTreeNode(menu), list));
           }
       });
       return trees;
    }

    private TreeNode findChild(TreeNode treeNode, List<Menu> list) {
        list.forEach(menu -> {
            if (Objects.equals(menu.getParentId(), treeNode.getNodeId())) {
                if (treeNode.getChildNodes() == null) {
                    treeNode.setChildNodes(new ArrayList<>());
                }
                treeNode.getChildNodes().add(findChild(packageTreeNode(menu), list));
            }
        });
        return treeNode;
    }

    private TreeNode packageTreeNode(Menu menu) {
        TreeNode node = new TreeNode();
        node.setName(menu.getMenuName());
        node.setNodeId(menu.getMenuId());
        node.setParenId(menu.getMenuId());
        node.setLevel(menu.getLevel());
        return node;
    }
}
