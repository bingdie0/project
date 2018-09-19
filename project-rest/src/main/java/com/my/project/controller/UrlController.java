package com.my.project.controller;

import com.my.framework.web.response.ResultConstant;
import com.my.project.dto.Page;
import com.my.project.dto.UrlQueryDTO;
import com.my.project.entity.Url;
import com.my.project.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/save")
    public String save(@RequestBody Url url) {
        urlService.save(url);
        return ResultConstant.SUCCESS;
    }

    @PutMapping("/update")
    public String update(Url url) {
        urlService.update(url);
        return ResultConstant.SUCCESS;
    }

    @DeleteMapping("/delete")
    public String delete(int id) {
        urlService.delete(id);
        return ResultConstant.SUCCESS;
    }

    @GetMapping("/list")
    public Page<Url> queryUrl(UrlQueryDTO dto) {

        return urlService.queryUrl(dto);
    }
}
