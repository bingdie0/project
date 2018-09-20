package com.my.project.dao;

import com.my.framework.boot.redis.datasource.mapper.BaseMapper;
import com.my.project.dto.UrlQueryDTO;
import com.my.project.entity.Url;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
public interface UrlMapper extends BaseMapper<Url> {

    /**
     * 根据id逻辑删除链接
     * @param id 主键id
     */
    void deleteUrlById(@Param("id") int id);

    /**
     * 查询链接
     * @param dto 查询参数
     * @return 返回url信息
     */
    List<Url> queryUrl(@Param("dto")UrlQueryDTO dto);

    /**
     * 查询链接
     * @param dto 查询参数
     * @return 返回url信息
     */
    int countUrl(@Param("dto")UrlQueryDTO dto);

}