/**
 * 新增或者编辑菜单
 *
 * @author Adoph
 * @since 2018/1/8
 */
Ext.define('Framework.view.admin.menu.EditMenu', {
    extend: 'Ext.window.Window',
    xtype: 'edit-menu',
    requires: ['Framework.view.admin.role.EditMenuController'],
    controller: 'edit-menu',
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
            name: 'roleName',
            fieldLabel: '角色名称'
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