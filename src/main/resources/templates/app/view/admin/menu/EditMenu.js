/**
 * 新增或者编辑菜单
 *
 * @author Adoph
 * @date 2018/1/8
 */
Ext.define('Framework.view.admin.menu.EditMenu', {
    extend: 'Ext.window.Window',
    xtype: 'edit-menu',
    // requires: ['Framework.view.admin.menu.EditMenuController'],
    // controller: 'edit-menu',
    width: 500,
    closeAction: 'destroy',
    scrollable: false,
    bodyPadding: 10,
    constrain: true,
    closeToolText: '关闭窗口',
    resizable: false,
    draggable: false,
    modal: true,
    items: [{
        xtype: 'form',
        reference: 'editMenuForm',
        border: false,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        defaults: {
            xtype: 'textfield',
            labelWidth: 60,
            labelAlign: 'top',
            allowBlank: false
        },
        items: [{
            xtype: 'hiddenfield',
            name: 'id',
            fieldLabel: 'id'
        }, {
            name: 'pid',
            fieldLabel: '父节点'
        }, {
            xtype: 'combobox',
            fieldLabel: 'Choose State',
            queryMode: 'local',
            displayField: 'name',
            valueField: 'abbr',
            store: [
                {abbr: 'AL', name: 'Alabama'},
                {abbr: 'AK', name: 'Alaska'},
                {abbr: 'AZ', name: 'Arizona'}
            ]
        }, {
            name: 'text',
            fieldLabel: '菜单名称'
        }, {
            name: 'iconCls',
            fieldLabel: '菜单图标'
        }, {
            name: 'expanded',
            fieldLabel: '是否展开'
        }, {
            name: 'leaf',
            fieldLabel: '是否叶子节点'
        }, {
            name: 'sort',
            fieldLabel: '排序序号'
        }, {
            name: 'routeId',
            fieldLabel: 'routeId'
        }, {
            name: 'viewType',
            fieldLabel: 'viewType'
        }]
    }],
    bbar: ['->', {
        text: '保存',
        cls: 'font-color-save',
        iconCls: 'x-fa fa-save',
        handler: 'onFormSubmit'
    }, {
        text: '取消',
        cls: 'font-color-cancel',
        iconCls: 'x-fa fa-ban',
        handler: 'onFormCancel'
    }]
});