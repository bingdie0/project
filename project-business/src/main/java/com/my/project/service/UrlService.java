package com.my.project.service;

import com.my.project.dto.Page;
import com.my.project.dto.UrlQueryDTO;
import com.my.project.entity.Url;

public interface UrlService {

    void save(Url url);

    void update(Url url);

    void delete(int id);

    Page<Url> queryUrl(UrlQueryDTO dto);
}
