package com.gong.core.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/9 14:10
 */
@Getter
@Setter
public class TreeNodeBO {
    /*
     * 节点编号
     */
    private String id;
    /*
     * 节点名称
     */
    private String name;
    /*
     * 节点图标
     */
    private String icon;
    /*
     * 父节点
     */
    private String parentId;

    /**
     * 父节点名称
     */
    private String parentName;
    /*
     * 节点类型
     */
    private Long typeId;
    /**
     * 是否启用
     */
    private Boolean isEnabled;
    /*
     * 节点数据
     */
    private Object data;
}
