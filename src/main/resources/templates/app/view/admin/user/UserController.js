/**
 * 用户管理Controller
 */
Ext.define('Framework.view.admin.user.UserController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.user',
    control: {
        'grid': {
            afterrender: 'onGridAfterrender'
        }
    },
    onGridAfterrender: function (grid, eOpts) {
        grid.getStore().load();
    },
    queryUser: function () {
        // debugger;
        var grid = this.lookup('userGrid');
        var form = this.lookup('userQueryForm');
        grid.getStore().reload({
            params : form.getValues()
        });
    },
    addUser: function () {
        console.log('addUser！');
    },
    editUser: function () {
        console.log('editUser！');
    },
    delUser: function () {
        console.log('delUser！');
    }
});