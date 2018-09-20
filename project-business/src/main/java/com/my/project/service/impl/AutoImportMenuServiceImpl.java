package com.my.project.service.impl;

import com.my.framework.common.utils.UUIDGenerator;
import com.my.project.dao.MenuMapper;
import com.my.project.entity.Menu;
import com.my.project.service.AutoImportMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-19
 **/
@Service
public class AutoImportMenuServiceImpl implements AutoImportMenuService {

    private Site site;

    @Autowired
    private MenuMapper menuMapper;

    public AutoImportMenuServiceImpl() {
        this.site = Site.me().setRetryTimes(3).setSleepTime(100);
    }

    @Override
    public void process(Page page) {
        List<Selectable> firstNodes = page.getHtml().xpath("div[@class='home-category-list J_Module']").nodes();
        firstNodes.forEach(firstNode -> {
            Html firstHtml = new Html(firstNode.toString());
            String firstName = firstHtml.xpath("[@class='category-name category-name-level1 J_category_hash']/html()").toString();
            Long firstMenuId = UUIDGenerator.getNextId();
            saveMenu(firstName, null, firstMenuId, 1);

            List<Selectable> secondNodes = firstHtml.xpath("[@class='category-list-item']").nodes();
            secondNodes.forEach(secondNode -> {
                Html secondHtml = new Html(secondNode.toString());
                String secondName = secondHtml.xpath("[@class='category-list-item']/[@class='category-name']/html()").toString();
                Long secondMenuId = UUIDGenerator.getNextId();
                saveMenu(secondName, firstMenuId, secondMenuId, 2);

                List<Selectable> thirdNodes = secondHtml.xpath("[@class='category-items']").nodes();
                thirdNodes.forEach(thirdNode -> {
                    Html thirdHtml = new Html(thirdNode.toString());
                    List<String> thirdNameList = thirdHtml.xpath("[@class='category-items']/[@class='category-name']/html()").all();
                    thirdNameList.forEach(thirdName -> {
                        Long thirdMenuId = UUIDGenerator.getNextId();
                       saveMenu(thirdName, secondMenuId, thirdMenuId, 3);
                    });
                });
            });
        });
    }

    @Override
    public Site getSite() {
        return site;
    }

    private void saveMenu(String name, Long parentId, Long menuId, int level) {
        Menu menu = new Menu();
        menu.setMenuId(menuId);
        menu.setMenuName(name);
        menu.setParentId(parentId);
        menu.setLevel(level);
        menuMapper.insertSelective(menu);
    }
}
