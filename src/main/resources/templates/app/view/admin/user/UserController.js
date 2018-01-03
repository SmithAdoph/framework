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
    //首次加载
    onGridAfterrender: function (grid, eOpts) {
        var me = this;
        var store = grid.getStore();
        store.on('beforeload', function (store, options) {
            var grid = Ext.ComponentQuery.query('user-grid')[0];
            var form = grid.getComponent('userQueryForm');
            var params = me.getQueryParams(form);
            Ext.apply(store.proxy.extraParams, params);
        });
        store.load();
    },
    //获取查询参数
    getQueryParams: function (form) {
        var values = form.getValues(),
            startCreateTime = values.startCreateTime,
            endCreateTime = values.endCreateTime;
        return {
            userName: values.userName || null,
            startCreateTime: startCreateTime ? new Date(startCreateTime).getTime() : null,
            endCreateTime: endCreateTime ? new Date(endCreateTime).getTime() + 1000 * 60 * 60 * 24 - 1000 : null
        };
    },
    //查询
    queryUser: function () {
        var grid = this.lookup('userGrid');
        var form = this.lookup('userQueryForm');
        var params = this.getQueryParams(form);
        grid.getStore().reload({
            params: params
        });
    },
    //添加
    addUser: function () {
        console.log('addUser！');
    },
    //编辑
    editUser: function () {
        console.log('editUser！');
    },
    //删除
    delUser: function () {
        console.log('delUser！');
    }
});