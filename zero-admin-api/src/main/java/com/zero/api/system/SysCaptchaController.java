package com.zero.api.system;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.zero.common.base.controller.BaseController;
import com.zero.common.constant.ShiroConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @title SysCaptchaController
 * @package com.zero.admin.controller.system
 * @description 图片验证码
 * @author jackie liao
 * @date 2019/6/28 18:07
 * @version V1.0
 */
@Controller
@RequestMapping("/captcha")
public class SysCaptchaController extends BaseController {

    /**
     * 验证码生成
     */
    @GetMapping(value = "/captchaImage")
    public ModelAndView getCaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            out = response.getOutputStream();
            //定义图形验证码的长、宽、验证码字符数、干扰线宽度
            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(80, 40, 5, 15);
            //图形验证码读取，可以写出到流
            String code = captcha.getCode();
            captcha.write(out);
            session.setAttribute(ShiroConstants.CAPTCHA_SESSION_KEY, code);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}