package com.my.project.dto;

import lombok.Data;

@Data
public class UrlQueryDTO {

    private int currentPage = 1;

    private int pageSize = 20;

    private String title;

    private Long menuId;

}
