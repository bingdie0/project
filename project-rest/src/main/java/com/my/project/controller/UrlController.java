package com.my.project.controller;

import com.my.framework.web.response.ResultConstant;
import com.my.project.dto.Page;
import com.my.project.dto.UrlQueryDTO;
import com.my.project.entity.Url;
import com.my.project.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    /**
     * 保存url
     *
     * @param url url
     * @return:
     * @author: Mr.WangJie
     */
    @PostMapping("/save")
    public String save(@RequestBody Url url) {
        urlService.save(url);
        return ResultConstant.SUCCESS;
    }

    /**
     * 更新url
     *
     * @param url url
     * @return:
     * @author: Mr.WangJie
     */
    @PutMapping("/update")
    public String update(Url url) {
        urlService.update(url);
        return ResultConstant.SUCCESS;
    }

    /**
     * 删除url
     *
     * @param id id
     * @return:
     * @author: Mr.WangJie
     */
    @DeleteMapping("/delete")
    public String delete(int id) {
        urlService.delete(id);
        return ResultConstant.SUCCESS;
    }

    /**
     * 查询url
     *
     * @param dto dto
     * @return:
     * @author: Mr.WangJie
     */
    @GetMapping("/list")
    public Page<Url> queryUrl(UrlQueryDTO dto) {

        return urlService.queryUrl(dto);
    }
}
