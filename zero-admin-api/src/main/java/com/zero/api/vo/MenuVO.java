package com.zero.api.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.zero.common.base.domain.entity.SysMenu;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author LiaoJunJie
 * @version V1.0
 * @description 菜单VO
 * @package com.zero.admin.vo
 * @date 2019-07-05 15:41
 */
@Data
public class MenuVO {
    private String menuId;
    private String title;
    private String icon;
    private String href;
    private Boolean spread = false;
    private String target;
    private List<MenuVO> children;

    private final static Map<String, String> fieldMap = new HashMap<String, String>(5) {{
        put("menuId", "menuId");
        put("menuName", "title");
        put("icon", "icon");
        put("url", "href");
        put("target", "target");
        put("children", "children");
    }};

    public static List<MenuVO> convert(List<SysMenu> list) {
        List<MenuVO> menuVoList = new LinkedList<MenuVO>();
        for (SysMenu sysMenu : list) {
            MenuVO vo = new MenuVO();
            BeanUtil.copyProperties(sysMenu, vo, CopyOptions.create().setFieldMapping(fieldMap));
            if (!sysMenu.getChildren().isEmpty()) {
                vo.setChildren(convert(sysMenu.getChildren()));
            }
            menuVoList.add(vo);
        }
        return menuVoList;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "menuId='" + menuId + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", spread=" + spread +
                ", target='" + target + '\'' +
                ", children=" + children +
                '}';
    }
}
