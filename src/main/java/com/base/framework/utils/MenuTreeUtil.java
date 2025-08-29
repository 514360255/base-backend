package com.base.framework.utils;

import com.base.framework.admin.model.vo.SysMenuVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 郭郭
 * @Create: 2025/8/29
 * @Description:
 **/
public class MenuTreeUtil {

    /**
     * 将扁平的 VO 列表构建成树形结构
     * @param list 扁平列表
     * @return 树形结构的根节点列表
     */
    public static List<SysMenuVO> buildTree(List<SysMenuVO> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        // 1. 构建 id -> node 映射，便于快速查找
        Map<Long, SysMenuVO> map = list.stream()
                .collect(Collectors.toMap(SysMenuVO::getId, v -> v));

        // 2. 存放根节点
        List<SysMenuVO> rootList = new ArrayList<>();

        // 3. 遍历所有节点，构建父子关系
        for (SysMenuVO vo : list) {
            Long parentId = vo.getParentId();

            // 判断是否为根节点（根据业务，可能是 0 或 null）
            if (parentId == null || parentId == 0) {
                rootList.add(vo);
            } else {
                SysMenuVO parent = map.get(parentId);
                if (parent != null) {
                    if (parent.getChildren() == null) {
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(vo);
                }
            }
        }

        // 4. 排序（可选）：按 orderNum 排序
        sortChildren(rootList);

        return rootList;
    }

    /**
     * 递归排序每个节点的 children
     */
    private static void sortChildren(List<SysMenuVO> nodes) {
        if (nodes == null) return;

        // 按 orderNum 升序，null 值排后面
        nodes.sort(Comparator.comparing(SysMenuVO::getSortOrder, Comparator.nullsLast(Integer::compareTo)));

        for (SysMenuVO node : nodes) {
            sortChildren(node.getChildren());
        }
    }
}