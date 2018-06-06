/**
 * 用户列表视图
 *
 * @author Adoph
 * @date 2017/11/30
 */
Ext.create('Ext.data.Store', {
    storeId: 'roleStore',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'roleName', type: 'string'}
    ],
    pageSize: 5,
    proxy: {
        type: 'ajax',
        method: 'POST',
        url: '/sysRole/list.do',
        reader: {
            type: 'json',
            rootProperty: 'data.rows',
            totalProperty: 'data.count',
            idProperty: 'id'
        }
    },
    autoLoad: false
});
Ext.define('Framework.view.admin.role.Role', {
    extend: 'Ext.panel.Panel',
    requires: ['Framework.view.admin.role.RoleController'],
    xtype: 'role-grid',
    controller: 'role',
    title: '角色列表',
    border: false,
    height: 300,
    layout: 'border',
    items: [{
        region: 'north',
        xtype: 'form',
        reference: 'queryForm',
        border: false,
        bodyPadding: '5 5 5 5',
        layout: 'hbox',
        defaults: {
            margin: '0 20 0 0'
        },
        items: [{
            xtype: 'textfield',
            name: 'roleName',
            labelWidth: 60,
            fieldLabel: '角色名称'
        }, {
            xtype: 'button',
            text: '查询',
            iconCls: 'x-fa fa-search',
            width: 69,
            itemId: 'query',
            formBind: true,
            handler: 'queryRole'
        }]
    }, {
        region: 'center',
        xtype: 'grid',
        reference: 'roleGrid',
        store: 'roleStore',
        columnLines: true,
        selModel: Ext.create("Ext.selection.CheckboxModel", {
            injectCheckbox: 0,//checkbox位于哪一列，默认值为0
            mode: "single",//multi,simple,single；默认为多选multi
            checkOnly: false,//如果值为true，则只用点击checkbox列才能选中此条记录
            allowDeselect: true,//如果值true，并且mode值为单选（single）时，可以通过点击checkbox取消对其的选择
            enableKeyNav: true
        }),
        columns: [{
            xtype: 'rownumberer',
            width: 40
        }, {
            text: '角色名称',
            width: 95,
            sortable: true,
            dataIndex: 'roleName',
            align: 'center'
        }],
        bbar: {
            xtype: 'pagingtoolbar',
            displayInfo: true
        },
        tbar: [{
            text: '新增',
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
        }]
    }]
});