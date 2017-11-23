/**
 * 登录视图
 *
 * @author adoph
 * @since 2017/11/14
 */
Ext.define('Framework.view.login.Login', {
    extend: 'Ext.window.Window',
    xtype: 'login',
    id: 'loginWin',
    resizable: false,
    draggable: false,

    requires: [
        'Framework.view.login.LoginController',
        'Ext.form.Panel'
    ],
    controller: 'login',

    bodyPadding: 10,
    title: 'Framework 后台管理登录框',
    closable: false,
    autoShow: true,//自动显示
    defaultType: 'textfield',

    items: [{
        allowBlank: false,
        fieldLabel: '用户名',
        name: 'userName',
        emptyText: '请输入用户名！'
    }, {
        allowBlank: false,
        fieldLabel: '密码',
        name: 'password',
        emptyText: '请输入密码！',
        inputType: 'password'
    }],

    buttons: [{
        text: '登录',
        handler: 'onLoginClick'
    }],

    defaults: {
        anchor: '100%',
        labelWidth: 90
    }
});