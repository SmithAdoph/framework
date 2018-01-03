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
        {name: 'userName', type: 'string'},
        {name: 'createTime', type: 'number'},
        {name: 'updateTime', type: 'number'}
    ],
    pageSize: 5,
    proxy: {
        type: 'ajax',
        method: 'POST',
        url: '/sysUser/list.do',
        reader: {
            type: 'json',
            rootProperty: 'data.rows',
            totalProperty: 'data.count',
            idProperty: 'id'
        }
    },
    autoLoad: false
});
Ext.define('Framework.view.admin.user.User', {
    extend: 'Ext.panel.Panel',
    requires: ['Framework.view.admin.user.UserController'],
    xtype: 'user-grid',
    controller: 'user',
    title: '用户列表',
    border: false,
    height: 300,
    layout: 'border',
    items: [{
        region: 'north',
        xtype: 'form',
        reference: 'userQueryForm',
        itemId: 'userQueryForm',
        border: false,
        bodyPadding: '5 5 5 5',
        layout: 'hbox',
        defaults: {
            margin: '0 20 0 0'
        },
        items: [{
            xtype: 'textfield',
            name: 'userName',
            labelWidth: 60,
            fieldLabel: '用户名'
        }, {
            xtype: 'fieldcontainer',
            layout: 'hbox',
            items: [{
                xtype: 'datefield',
                name: 'startCreateTime',
                fieldLabel: '创建日期',
                maxDate: new Date(),
                labelWidth: 60,
                dateFormat: 'Y年m月d日',
                submitFormat: 'Y-m-d',
                editable: true
            }, {
                xtype: 'displayfield',
                value: '至',
                padding: '0 5 0 5'
            }, {
                xtype: 'datefield',
                name: 'endCreateTime',
                maxDate: new Date(),
                dateFormat: 'Y-m-d',
                submitFormat: 'Y-m-d',
                editable: true
            }]
        }, {
            xtype: 'button',
            text: '查询',
            iconCls: 'x-fa fa-search',
            width: 69,
            itemId: 'query',
            formBind: true,
            handler: 'queryUser'
        }]
    }, {
        region: 'center',
        xtype: 'grid',
        reference: 'userGrid',
        store: 'userStore',
        columnLines: true,
        columns: [{
            xtype: 'rownumberer',
            width: 40
        }, {
            text: '用户名',
            width: 95,
            sortable: true,
            dataIndex: 'userName',
            align: 'center'
        }, {
            text: '创建时间',
            width: 95,
            sortable: true,
            dataIndex: 'createTime',
            align: 'center',
            renderer: function (val) {
                return Ext.util.Format.date(new Date(val), 'Y-m-d');
            }
        }, {
            text: '修改时间',
            width: 95,
            sortable: true,
            dataIndex: 'updateTime',
            align: 'center',
            renderer: function (val) {
                return Ext.util.Format.date(new Date(val), 'Y-m-d');
            }
        }],
        bbar: {
            xtype: 'pagingtoolbar',
            displayInfo: true
        },
        tbar: [{
            text: '新增',
            cls: 'font-color-add',
            iconCls: 'x-fa fa-plus',
            itemId: 'add',
            handler: 'addUser'
        }, {
            text: '编辑',
            cls: 'font-color-edit',
            iconCls: 'x-fa fa-edit',
            itemId: 'edit',
            handler: 'editUser'
        }, '-', {
            text: '删除',
            cls: 'font-color-remove',
            iconCls: 'x-fa fa-times',
            itemId: 'del',
            handler: 'delUser'
        }]
    }]
});