package com.my.project.service;

import com.my.framework.boot.redis.datasource.service.BaseSqlService;
import com.my.project.dto.Page;
import com.my.project.dto.UrlQueryDTO;
import com.my.project.entity.Url;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
public interface UrlService extends BaseSqlService<Url> {

    /**
     * 保存url
     *
     * @param url url
     * @return:
     * @author: Mr.WangJie
     */
    void save(Url url);

    /**
     * 更新url
     *
     * @param url url
     * @return:
     * @author: Mr.WangJie
     */
    void update(Url url);

    /**
     * 删除url
     *
     * @param id id
     * @return:
     * @author: Mr.WangJie
     */
    void delete(int id);


    /**
     * 查询url
     *
     * @param dto dto
     * @return:
     * @author: Mr.WangJie
     */
    Page<Url> queryUrl(UrlQueryDTO dto);
}
