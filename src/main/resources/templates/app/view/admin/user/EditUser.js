/**
 * 新增或者编辑用户
 *
 * @author Adoph
 * @since 2018/1/8
 */
Ext.define('Framework.view.admin.user.EditUser', {
    extend: 'Ext.window.Window',
    xtype: 'edit-user',
    reference: 'editUserWin',
    requires: ['Framework.view.admin.user.EditUserController'],
    controller: 'edit-user',
    width: 500,
    scrollable: false,
    bodyPadding: 10,
    constrain: true,
    closeToolText: '关闭窗口',
    resizable: false,
    draggable: false,
    modal: true,
    viewModel: {
        data: {
            userName: ''
        }
    },

    items: [{
        xtype: 'form',
        reference: 'editUserForm',
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
            name: 'userName',
            fieldLabel: '用户名',
            bind: '{userName}'
        }, {
            name: 'password',
            allowBlank: true,
            fieldLabel: '默认密码',
            bind: '{userName}_123',
            editable: false
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