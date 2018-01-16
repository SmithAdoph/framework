Ext.define('Framework.view.admin.role.EditRoleController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.edit-role',

    //取消
    onFormCancel: function () {
        this.getView('edit-role').close();
    },

    //保存
    onFormSubmit: function () {
        var me = this,
            formPanel = me.lookupReference('editRoleForm'),
            form = formPanel.getForm();

        if (form.isValid()) {
            form.submit({
                url: 'sysRole/save.do',
                method: 'POST',
                success: function () {
                    me.getView('edit-role').close();
                    Ext.ComponentQuery.query('role-grid grid')[0].getStore().reload();
                    Ext.toast({
                        title: '系统提示',
                        html: '保存成功！',
                        closable: false,
                        align: 't',
                        slideInDuration: 400,
                        minWidth: 350,
                        iconCls: 'right-icon',
                        autoCloseDelay: 2500
                    });
                },
                failure: function (form, action) {
                    Ext.Msg.show({
                        title: '系统提示',
                        message: action.result.msg,
                        buttons: Ext.Msg.OK,
                        icon: Ext.Msg.ERROR
                    });
                }
            });
        }
    }
});