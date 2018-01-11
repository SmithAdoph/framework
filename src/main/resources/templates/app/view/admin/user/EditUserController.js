Ext.define('Framework.view.admin.user.EditUserController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.edit-user',

    //取消
    onFormCancel: function () {
        this.getView('edit-user').close();
    },

    //保存
    onFormSubmit: function () {
        var me = this,
            formPanel = me.lookupReference('editUserForm'),
            form = formPanel.getForm();

        if (form.isValid()) {
            form.submit({
                url: 'sysUser/save.do',
                method: 'POST',
                success: function () {
                    me.getView('edit-user').close();
                    Ext.ComponentQuery.query('user-grid grid')[0].getStore().reload();
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