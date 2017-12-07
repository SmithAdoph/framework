/**
 * 用户列表视图
 *
 * @author Adoph
 * @since 2017/11/30
 */
Ext.create('Ext.data.Store', {
    storeId: 'userStore',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'userName', type: 'string'}
    ],
    pageSize: 5,
    proxy: {
        type: 'ajax',
        method: 'POST',
        url: '/sysUser/list.do',
        reader: {
            type: 'json',
            rootProperty: 'content',
            totalProperty: 'totalElements'
        }
    },
    autoLoad: false
});
Ext.define('Framework.view.admin.user.User', {
    extend: 'Ext.grid.Panel',
    xtype: 'user-grid',
    title: '用户列表',
    store: 'userStore',
    columnLines: true,
    columns: [{
        xtype: 'rownumberer'
    }, {
        text: 'id',
        width: 65,
        sortable: false,
        dataIndex: 'id',
        align: 'center'
    }, {
        text: '用户名',
        width: 95,
        sortable: true,
        dataIndex: 'userName',
        align: 'center'
    }],
    bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true
    },
    tbar: [{
        xtype: 'button',
        text: '新增',
        cls: 'font-color-add',
        iconCls: 'x-fa fa-plus'
    }, {
        xtype: 'button',
        text: '编辑',
        cls: 'font-color-edit',
        iconCls: 'x-fa fa-edit'
    }, '-', {
        xtype: 'button',
        text: '删除',
        cls: 'font-color-remove',
        iconCls: 'x-fa fa-times'
    }]
});