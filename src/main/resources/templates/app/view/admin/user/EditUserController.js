Ext.define('Framework.view.admin.user.EditUserController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.edit-user',

    //取消
    onFormCancel: function () {
        this.getView('edit-user').close();
    },

    //保存
    onFormSubmit: function () {
        var me = this;
        var formPanel = me.lookupReference('editUserForm'),
            form = formPanel.getForm();

        if (form.isValid()) {
            form.submit({
                url: 'sysUser/save.do',
                method: 'POST',
                success: function () {
                    me.getView('edit-user').close();
                    Ext.ComponentQuery.query('user-grid grid')[0].getStore().reload();
                    Ext.Msg.alert('系统提示!', '保存成功！');
                }
            });
        }
    }
});