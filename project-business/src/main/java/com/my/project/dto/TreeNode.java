package com.my.project.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-14
 **/
@Data
public class TreeNode {

    private Long nodeId;

    private String name;

    private Long parenId;

    private List<TreeNode> childNodes;

    private int level;
}
