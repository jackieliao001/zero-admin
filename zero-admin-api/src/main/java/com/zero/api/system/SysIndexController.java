package com.zero.api.system;

import com.zero.common.base.controller.BaseController;
import com.zero.common.config.Global;
import com.zero.common.utils.security.SecurityUtils;
import com.zero.system.domain.SysMenu;
import com.zero.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author zero
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    /**
     * 系统首页框架 跳转控制
     * @param mmap
     * @return
     */
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        UserDetails user = SecurityUtils.getCurrentUser();
        // 根据用户id取出菜单
        SysMenu menu = new SysMenu();
        menu.setParentId(0L);
        List<SysMenu> modes = menuService.selectMenuList(menu,SecurityUtils.getCurrentUserId());
        mmap.put("modes", modes);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        return "index";
    }

    /**
     * 系统介绍 跳转控制
     * @param mmap
     * @return
     */
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
