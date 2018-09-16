package com.my.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private int currentPage = 1;

    private int pageSize = 20;

    private List<T> list;

    private int totalCount;

    private int totalPage;

}
