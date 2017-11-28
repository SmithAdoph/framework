/**
 * 登录视图
 *
 * @author adoph
 * @since 2017/11/14
 */
Ext.define('Framework.view.login.Login', {
    extend: 'Ext.window.Window',
    xtype: 'login',
    requires: [
        'Framework.view.login.LoginController',
        'Ext.form.Panel'
    ],
    controller: 'login',
    bodyPadding: 10,
    title: 'Framework 后台管理登录框',
    closable: false,
    autoShow: true,
    resizable: false,
    draggable: false,

    items: {
        xtype: 'form',
        reference: 'form',
        defaultType: 'textfield',
        border: false,
        items: [{
            allowBlank: false,
            fieldLabel: '用户名',
            name: 'userName',
            emptyText: '请输入用户名！'
        }, {
            allowBlank: false,
            fieldLabel: '密　码',
            name: 'password',
            emptyText: '请输入密码！',
            inputType: 'password'
        }],
        buttons: [{
            text: '登录',
            formBind: true,
            listeners: {
                click: 'onLoginClick'
            }
        }],
        defaults: {
            anchor: '100%',
            labelWidth: 90
        }
    }
});