/**
 * 系统菜单视图
 *
 * @author Adoph
 * @date 2017/11/30
 */
Ext.create('Ext.data.TreeStore', {
    storeId: 'menuStore',
    proxy: {
        type: 'ajax',
        url: '/menu/queryMenusByPid.do',
        reader: {
            type: 'json',
            rootProperty: 'data',
            idProperty: 'id'
        }
    },
    nodeParam: 'pid',
    root: {
        text: 'Framework菜单管理',
        id: 0,
        expanded: false
    },
    autoLoad: false
});
Ext.define('Framework.view.admin.menu.Menu', {
    extend: 'Ext.tree.Panel',
    requires: ['Framework.view.admin.menu.MenuController'],
    xtype: 'menu-grid',
    controller: 'menu',
    title: '菜单列表',
    rootVisible: true,
    border: false,
    height: 300,
    store: 'menuStore',
    tbar: [{
        text: '新增根节点',
        cls: 'font-color-add',
        iconCls: 'x-fa fa-plus',
        handler: 'addRoot'
    }, {
        text: '新增子节点',
        cls: 'font-color-add',
        iconCls: 'x-fa fa-plus',
        handler: 'add'
    }, {
        text: '编辑',
        cls: 'font-color-edit',
        iconCls: 'x-fa fa-edit',
        handler: 'edit'
    }, '-', {
        text: '删除',
        cls: 'font-color-remove',
        iconCls: 'x-fa fa-times',
        handler: 'del'
    }],
    tools: [{
        type: 'refresh',
        tooltip: 'Refresh form Data',
        // hidden:true,
        handler: function (event, toolEl, panelHeader) {
            // refresh logic
        }
    }, {
        type: 'help',
        tooltip: 'Get Help',
        callback: function (panel, tool, event) {
            // show help here
        }
    }]
});