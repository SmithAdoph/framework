package com.adoph.framework.permission.pojo;

import com.adoph.framework.pojo.BasePojo;

import java.io.Serializable;
import java.util.List;

/**
 * 系统菜单表
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/9/14
 */
public class SysMenu extends BasePojo implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 父节点id
     */
    private Long pid;

    /**
     * 类型：1，链接；2，目录
     */
    private int type;

    /**
     * 菜单名称
     */
    private String text;

    /**
     * 菜单图标
     */
    private String iconCls;

    /**
     * 是否展开：0，否；1，是
     */
    private int expanded;

    /**
     * 是否叶子节点：0，否；1，是
     */
    private int leaf;

    /**
     * 排序序号
     */
    private Long sort;

    /**
     * 关联ExtJS routeId
     */
    private String routeId;

    /**
     * 关联ExtJS xtype
     */
    private String viewType;

    /**
     * 子节点
     */
    private List<SysMenu> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getExpanded() {
        return expanded;
    }

    public void setExpanded(int expanded) {
        this.expanded = expanded;
    }

    public int getLeaf() {
        return leaf;
    }

    public void setLeaf(int leaf) {
        this.leaf = leaf;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
}
