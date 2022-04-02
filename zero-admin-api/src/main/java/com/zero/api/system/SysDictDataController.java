package com.zero.api.system;

import com.zero.common.annotation.Log;
import com.zero.common.base.controller.BaseController;
import com.zero.common.base.domain.AjaxResult;
import com.zero.common.base.page.TableDataInfo;
import com.zero.common.enums.BusinessType;
import com.zero.common.utils.security.SecurityUtils;
import com.zero.system.domain.SysDictData;
import com.zero.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据字典信息
 *
 * @author zero
 */
@Controller
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    private String prefix = "system/dict/data";

    @Autowired
    private ISysDictDataService dictDataService;

    // @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictData() {
        return prefix + "/data";
    }

    @PostMapping("/list")
    // @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, ModelMap mmap) {
        mmap.put("dictType", dictType);
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    // @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysDictData dict) {
        dict.setCreateBy(SecurityUtils.getCurrentUsername());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, ModelMap mmap) {
        mmap.put("dict", dictDataService.selectDictDataById(dictCode));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    // @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysDictData dict) {
        dict.setUpdateBy(SecurityUtils.getCurrentUsername());
        return toAjax(dictDataService.updateDictData(dict));
    }

    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    // @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dictDataService.deleteDictDataByIds(ids));
    }
}
