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
            startLastLoginTime = values.startLastLoginTime,
            endLastLoginTime = values.endLastLoginTime;
        return {
            userName: values.userName || null,
            startLastLoginTime: startLastLoginTime ? new Date(startLastLoginTime).getTime() : null,
            endLastLoginTime: endLastLoginTime ? new Date(endLastLoginTime).getTime() + 1000 * 60 * 60 * 24 - 1000 : null
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
        Ext.require('Framework.view.admin.user.EditUser', function () {
            Ext.create({
                xtype: 'edit-user',
                title: '新增用户'
            }).show();
        });
    },

    //编辑
    editUser: function () {
        var me = this,
            grid = me.lookupReference('userGrid'),
            row = grid.getSelection()[0];
        if (row) {
            Ext.require('Framework.view.admin.user.EditUser', function () {
                var editUserWin = Ext.create({
                    xtype: 'edit-user',
                    title: '编辑用户'
                });
                editUserWin.show();
                var form = editUserWin.lookupReference('editUserForm');
                form.loadRecord(row);
                Ext.Ajax.request({
                    url: 'sysUser/queryUserRoles.do',
                    async: false,
                    params: {
                        userId: row.data.id
                    },
                    success: function (response) {
                        var r = Ext.decode(response.responseText);
                        var userRolesGroup = this.lookup('userRoles');
                        if (r.data.length > 0) {
                            var values = [];
                            for (var i in r.data) {
                                values.push(r.data[i].id);
                            }
                            debugger;
                            userRolesGroup.setValue({
                                sysRoles: values
                            });
                        }
                    },
                    scope: editUserWin
                });
            });
        } else {
            Ext.Msg.show({
                title: '系统提示',
                message: '请选择一条数据！',
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.INFO
            });
        }
    },

    //删除
    delUser: function () {
        var me = this,
            grid = me.lookupReference('userGrid'),
            row = grid.getSelection()[0];
        if (row) {
            Ext.Msg.show({
                title: '系统提示',
                message: '确定删除此条数据！',
                buttons: Ext.Msg.OKCANCEL,
                icon: Ext.Msg.QUESTION,
                closable: false,
                fn: function (btn) {
                    if (btn === 'ok') {
                        var logoutMask = new Ext.LoadMask({
                            msg: '正在删除数据...',
                            target: Ext.ComponentQuery.query('app-main')[0]
                        });
                        logoutMask.show();
                        Ext.Ajax.request({
                            url: 'sysUser/del.do',
                            params: {
                                id: row.data.id
                            },
                            success: function (response) {
                                logoutMask.hide();
                                var r = Ext.decode(response.responseText);
                                if (r.success === true) {
                                    grid.getStore().reload({page: 1, start: 0});
                                    Ext.toast({
                                        title: '系统提示',
                                        html: '删除成功！',
                                        closable: false,
                                        align: 't',
                                        slideInDuration: 400,
                                        minWidth: 350,
                                        iconCls: 'right-icon',
                                        autoCloseDelay: 2500
                                    });
                                } else {
                                    Ext.Msg.show({
                                        title: '系统提示',
                                        message: r.msg,
                                        buttons: Ext.Msg.OK,
                                        icon: Ext.Msg.ERROR
                                    });
                                }
                            }
                        });
                    }
                }
            });
        } else {
            Ext.Msg.show({
                title: '系统提示',
                message: '请选择一条数据！',
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.INFO
            });
        }
    }
});