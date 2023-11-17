package com.gong.core.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    /**
     * 子节点
     */
    private List<TreeNodeBO> children;

    public TreeNodeBO() {
    }

    public TreeNodeBO(String id, String name, String icon, String parentId, String parentName, Long typeId, Boolean isEnabled, Object data, List<TreeNodeBO> children) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.parentId = parentId;
        this.parentName = parentName;
        this.typeId = typeId;
        this.isEnabled = isEnabled;
        this.data = data;
        this.children = children;
    }
}
