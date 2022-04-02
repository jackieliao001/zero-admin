package com.zero.api.monitor;

import com.zero.common.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * druid 监控
 *
 * @author zero
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController {
    private static String prefix = "/monitor/druid";

//    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index() {
        return redirect(prefix + "/index");
    }
}
