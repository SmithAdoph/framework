package com.adoph.framework.permission.service.sys.impl;

import com.adoph.framework.permission.dao.sys.SysMenuMapper;
import com.adoph.framework.permission.pojo.SysMenu;
import com.adoph.framework.permission.service.sys.SysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单管理
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/30
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> queryMenus() {
        List<SysMenu> menus = new ArrayList<>();
        //1.查出所有菜单
        List<SysMenu> sysMenus = sysMenuMapper.selectMenus();//所有菜单
        List<SysMenu> remainMenus = new ArrayList<>(sysMenus);
        //2.组装树形结构
        for (SysMenu item : sysMenus) {
            if (item.getLeaf() == 0) {
                menus.add(item);
                remainMenus.remove(item);
            }
        }
        for (SysMenu root : menus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu item : remainMenus) {
                if (item.getPid().equals(root.getId())) {
                    children.add(item);
                }
            }
            if (!children.isEmpty()) {
                root.setChildren(children);
            }
        }
        return menus;
    }

    @Override
    public List<SysMenu> queryMenusByPid(Long pid) {
        return sysMenuMapper.selectMenusByPid(pid);
    }

    @Transactional
    @Override
    public void saveMenu(SysMenu menu) {
        Long id = menu.getId();
        if (id == null) {
            sysMenuMapper.insertMenu(menu);
        } else {
            sysMenuMapper.updateMenu(menu);
        }
    }

    @Transactional
    @Override
    public void delMenu(Long id) {
        Assert.notNull(id, "id不能为空！");
        sysMenuMapper.deleteMenu(id);
    }

}
