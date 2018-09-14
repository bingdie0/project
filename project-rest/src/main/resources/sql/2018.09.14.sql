CREATE TABLE `menu` (
`id` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键id',
`menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
`menu_name` varchar(255) CHARACTER SET latin1 DEFAULT NULL COMMENT '菜单名称',
`parent_id` bigint(20) DEFAULT NULL COMMENT '父节点id',
`disable` tinyint(4) DEFAULT '1' COMMENT '是否可用，1可用，2不可用，默认可用',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT DEFAULT CHARSET=utf8 COMMENT='菜单表';