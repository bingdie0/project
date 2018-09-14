package com.my.project.controller;

import com.my.framework.response.ResultConstant;
import com.my.project.dto.TreeNode;
import com.my.project.entity.Menu;
import com.my.project.request.SaveMenuRequest;
import com.my.project.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/save")
    public String saveMenu(@RequestBody SaveMenuRequest request) {
        Menu menu = new Menu();
        menu.setMenuName(request.getMenuName());
        menu.setParentId(request.getParentId());
        menuService.saveMenu(menu);
        return ResultConstant.SUCCESS;
    }

    @GetMapping("/list")
    public List<TreeNode> findMenuList() {
        return menuService.findMenuList();
    }

    /**
     * 更新菜单数据
     *
     * @param menuId 菜单id
     * @param menuName 菜单名称
     * @return:
     * @author: Mr.WangJie
     */
    @PutMapping("/update")
    public String updateMenuById(Long menuId, String menuName) {
        Menu menu = new Menu();
        menu.setMenuId(menuId);
        menu.setMenuName(menuName);
        menuService.updateMenuById(menu);
        return ResultConstant.SUCCESS;
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     * @return:
     * @author: Mr.WangJie
     */
    @DeleteMapping("/delete")
    public String deleteMenuById(Long menuId) {
        menuService.deleteMenuById(menuId);
        return ResultConstant.SUCCESS;
    }
}
