package com.zero.api.system;

import com.zero.common.base.controller.BaseController;
import com.zero.common.utils.security.SecurityUtils;
import com.zero.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 个人信息 业务处理
 *
 * @author zero
 */
@Slf4j
@Controller
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {

    private String prefix = "system/user/profile";

    @Resource
    private ISysUserService userService;
    
   /* @Autowired
    private SysPasswordService passwordService;*/

    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap) {
//        SysUser user = ShiroUtils.getSysUser();
        UserDetails user = SecurityUtils.getCurrentUser();
        Long userId = SecurityUtils.getCurrentUserId();
        mmap.put("user", user);
        mmap.put("roleGroup", userService.selectUserRoleGroup(userId));
        mmap.put("postGroup", userService.selectUserPostGroup(userId));
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        /*SysUser user = ShiroUtils.getSysUser();
        if (passwordService.matches(user, password)) {
            return true;
        }*/
        return false;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap) {
        mmap.put("user", userService.selectUserById(SecurityUtils.getCurrentUserId()));
        return prefix + "/resetPwd";
    }

   /* @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword) {
        SysUser user = ShiroUtils.getSysUser();
        if (StringUtils.isNotEmpty(newPassword) && passwordService.matches(user, oldPassword)) {
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
            if (userService.resetUserPwd(user) > 0) {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
                return success();
            }
            return error();
        } else {
            return error("修改密码失败，旧密码错误");
        }
    }*/

    /**
     * 修改用户
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap) {
        mmap.put("user", userService.selectUserById(SecurityUtils.getCurrentUserId()));
        return prefix + "/edit";
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap) {
        mmap.put("user", userService.selectUserById(SecurityUtils.getCurrentUserId()));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
/*    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(SysUser user) {
        SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());
        if (userService.updateUserInfo(currentUser) > 0) {
            ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));
            return success();
        }
        return error();
    }*/

    /**
     * 保存头像
     */
/*    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file) {
        SysUser currentUser = ShiroUtils.getSysUser();
        try {
            if (!file.isEmpty()) {
                String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
                currentUser.setAvatar(avatar);
                if (userService.updateUserInfo(currentUser) > 0) {
                    ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));
                    return success();
                }
            }
            return error();
        } catch (Exception e) {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }*/
}
