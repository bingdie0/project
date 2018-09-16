package com.my.project.dao;

import com.my.project.dto.UrlQueryDTO;
import com.my.project.entity.Url;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UrlMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Url record);

    Url selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Url record);

    void deleteUrlById(@Param("id") int id);

    List<Url> queryUrl(@Param("dto")UrlQueryDTO dto);

    int countUrl(@Param("dto")UrlQueryDTO dto);

}