package com.zero.api.system;

import com.zero.common.annotation.Log;
import com.zero.common.base.controller.BaseController;
import com.zero.common.base.domain.AjaxResult;
import com.zero.common.base.page.TableDataInfo;
import com.zero.common.enums.BusinessType;
import com.zero.common.utils.security.SecurityUtils;
import com.zero.system.domain.SysPost;
import com.zero.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位信息操作处理
 *
 * @author zero
 */
@Controller
@RequestMapping("/system/post")
public class SysPostController extends BaseController {
    private String prefix = "system/post";

    @Autowired
    private ISysPostService postService;

    // @RequiresPermissions("system:post:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/post";
    }

    // @RequiresPermissions("system:post:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPost post) {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    // @RequiresPermissions("system:post:remove")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(postService.deletePostByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 新增岗位
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存岗位
     */
    // @RequiresPermissions("system:post:add")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysPost post) {
        post.setCreateBy(SecurityUtils.getCurrentUsername());
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, ModelMap mmap) {
        mmap.put("post", postService.selectPostById(postId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    // @RequiresPermissions("system:post:edit")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysPost post) {
        post.setUpdateBy(SecurityUtils.getCurrentUsername());
        return toAjax(postService.updatePost(post));
    }

    /**
     * 校验岗位名称
     */
    @PostMapping("/checkPostNameUnique")
    @ResponseBody
    public String checkPostNameUnique(SysPost post) {
        return postService.checkPostNameUnique(post);
    }

    /**
     * 校验岗位编码
     */
    @PostMapping("/checkPostCodeUnique")
    @ResponseBody
    public String checkPostCodeUnique(SysPost post) {
        return postService.checkPostCodeUnique(post);
    }
}
