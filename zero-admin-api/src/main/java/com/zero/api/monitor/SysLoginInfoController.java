package com.zero.api.monitor;

import com.zero.common.annotation.Log;
import com.zero.common.base.controller.BaseController;
import com.zero.common.base.domain.AjaxResult;
import com.zero.common.base.page.TableDataInfo;
import com.zero.common.enums.BusinessType;
import com.zero.system.domain.SysLogininfor;
import com.zero.system.service.ISysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统访问记录
 *
 * @author zero
 */
@Controller
@RequestMapping("/monitor/logininfo")
public class SysLoginInfoController extends BaseController {
    private String prefix = "monitor/loginInfo";
    @Autowired
    private ISysLoginInfoService loginInfoService;

//    @RequiresPermissions("monitor:logininfo:view")
    @GetMapping()
    public String loginInfo() {
        return prefix + "/loginInfo";
    }

//    @RequiresPermissions("monitor:logininfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLogininfor logininfor) {
        startPage();
        List<SysLogininfor> list = loginInfoService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

/*    @Log(title = "登陆日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:logininfor:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysLogininfor logininfor)
    {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        return util.exportExcel(list, "登陆日志");
    }*/

//    @RequiresPermissions("monitor:logininfo:remove")
    @Log(title = "登陆日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(loginInfoService.deleteLogininforByIds(ids));
    }

//    @RequiresPermissions("monitor:logininfo:remove")
    @Log(title = "登陆日志", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        loginInfoService.cleanLogininfor();
        return success();
    }
}
