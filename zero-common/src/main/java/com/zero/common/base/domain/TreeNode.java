package com.zero.common.base.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zero.common.base.domain.entity.SysDept;
import com.zero.common.base.domain.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jackie liao
 * @version V1.0
 * @title LayTree
 * @package com.zero.common.base.domain
 * @description layui tree 组件
 * @date 2019/7/8 15:59
 */
@Data
public class TreeNode implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点唯一索引
     */
    private Long id;

    /**
     * 节点标题
     */
    private String title;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeNode> children;

    /**
     * 点击节点弹出新窗口对应的 url。需开启 isJump 参数
     */
    private String href;

    /**
     * 是否勾选
     */
    private boolean checked = false;

    /**
     * 是否展开
     */
    private boolean spread = false;

    /**
     * 节点是否为禁用状态。默认 false
     */
    private boolean disabled = false;

    /**
     * 节点是否为禁用状态。默认 false
     */
    private Map attr;

    public TreeNode() {

    }

    public TreeNode(SysDept dept) {
        this.id = dept.getDeptId();
        this.title = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeNode::new).collect(Collectors.toList());
    }

    public TreeNode(SysMenu menu) {
        this.id = menu.getMenuId();
        this.title = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeNode::new).collect(Collectors.toList());
    }
}
