package com.zero.common.base.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

}
