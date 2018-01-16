/**
 * 新增或者编辑用户
 *
 * @author Adoph
 * @since 2018/1/8
 */
Ext.define('Framework.view.admin.user.EditUser', {
    extend: 'Ext.window.Window',
    xtype: 'edit-user',
    requires: ['Framework.view.admin.user.EditUserController'],
    controller: 'edit-user',
    width: 500,
    closeAction: 'destroy',
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
            xtype: 'hiddenfield',
            name: 'id',
            fieldLabel: 'id'
        }, {
            name: 'userName',
            fieldLabel: '用户名',
            bind: '{userName}'
        }, {
            name: 'password',
            allowBlank: true,
            fieldLabel: '密　码',
            bind: '{userName}_123',
            editable: false
        }, {
            xtype: 'fieldset',
            title: '用户角色',
            items: [{
                xtype: 'checkboxgroup',
                columns: 4,
                listeners: {
                    render: function (obj, opt) {
                        Ext.Ajax.request({
                            url: 'sysRole/queryAllRoles.do',
                            method: 'GET',
                            success: function (response, opts) {
                                var r = Ext.decode(response.responseText),
                                    data = r.data;
                                var items = [];
                                for (var index in data) {
                                    var item = data[index];
                                    items.push(Ext.create({
                                        xtype: 'checkbox',
                                        name: 'roles',
                                        boxLabel: item.roleName,
                                        inputValue: item.id
                                    }));
                                }
                                this.items.addAll(items);
                                this.updateLayout();//重新布局
                            },
                            scope: this
                        });
                    }
                }
            }]
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