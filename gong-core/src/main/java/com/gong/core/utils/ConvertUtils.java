package com.gong.core.utils;

import com.gong.core.bo.TreeNodeBO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class ConvertUtils {

    /**
     * 转换列表为树
     *
     * @param nodeList
     * @return
     */
    public static List<TreeNodeBO> convertToTree(List<TreeNodeBO> nodeList) {
        List<TreeNodeBO> treeNodes = new ArrayList<>();
        for (TreeNodeBO treeNodeBO : nodeList) {
            if (treeNodeBO.getParentId() == null || treeNodeBO.getParentId().isEmpty()) {
                treeNodeBO.setChildren(buildChildren(treeNodeBO.getId(), nodeList, node -> Boolean.TRUE.equals(node.getIsEnabled())));
                treeNodes.add(treeNodeBO);
            }
        }
        return treeNodes;
    }

    /**
     * 转换列表为树
     *
     * @param nodeList 节点列表
     * @return
     */
    public static List<TreeNodeBO> convertToTree(List<TreeNodeBO> nodeList, Predicate<TreeNodeBO> predicate) {
        List<TreeNodeBO> treeNodes = new ArrayList<>();
        for (TreeNodeBO treeNodeBO : nodeList) {
            if (treeNodeBO.getParentId() == null || treeNodeBO.getParentId().isEmpty()) {
                treeNodeBO.setIsEnabled(predicate.test(treeNodeBO));
                treeNodeBO.setChildren(buildChildren(treeNodeBO.getId(), nodeList, predicate));
                treeNodes.add(treeNodeBO);
            }
        }
        return treeNodes;
    }

    /**
     * 构建节点
     *
     * @param parentId 父节点ID
     * @param nodeList 节点列表
     * @return
     */
    private static List<TreeNodeBO> buildChildren(String parentId, List<TreeNodeBO> nodeList, Predicate<TreeNodeBO> predicate) {
        List<TreeNodeBO> children = new ArrayList<>();
        for (TreeNodeBO node : nodeList) {
            if (parentId.equals(node.getParentId())) {
                node.setIsEnabled(predicate.test(node));
                node.setChildren(buildChildren(node.getId(), nodeList, predicate));
                children.add(node);
            }
        }
        return children;
    }
}
