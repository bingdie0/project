package com.my.project.service.impl;

import com.my.framework.boot.redis.datasource.service.impl.BaseSqlServiceImpl;
import com.my.project.dao.UrlMapper;
import com.my.project.dto.Page;
import com.my.project.dto.UrlQueryDTO;
import com.my.project.entity.Url;
import com.my.project.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
@Service
public class UrlServiceImpl extends BaseSqlServiceImpl<Url> implements UrlService {

    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(urlMapper);
    }

    @Override
    public void save(Url url) {
        urlMapper.insertSelective(url);
    }

    @Override
    public void update(Url url) {
        urlMapper.updateByPrimaryKeySelective(url);
    }

    @Override
    public void delete(int id) {
        urlMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page<Url> queryUrl(UrlQueryDTO dto) {
        Page<Url> page = new Page<>();
        page.setCurrentPage(dto.getCurrentPage());
        page.setPageSize(dto.getPageSize());
        int count = urlMapper.countUrl(dto);
        page.setTotalCount(count);
        page.setTotalPage(count % dto.getCurrentPage() == 0 ? count / dto.getCurrentPage()
                : count / dto.getCurrentPage() + 1);
        if (count > 0) {
            List<Url> urls = urlMapper.queryUrl(dto);
            page.setList(urls);
        } else {
            page.setList(Collections.emptyList());
        }
        return page;
    }
}
