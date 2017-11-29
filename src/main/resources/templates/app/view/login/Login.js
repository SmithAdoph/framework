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
            emptyText: '请输入用户名！',
            height: 30
        }, {
            allowBlank: false,
            fieldLabel: '密　码',
            name: 'password',
            emptyText: '请输入密码！',
            inputType: 'password',
            height: 30
        }, {
            xtype: 'panel',
            reference: 'verifyCodePanel',
            hidden: true,
            border: false,
            defaults: {
                border: false,
                xtype: 'panel',
                flex: 1,
                layout: 'anchor'
            },
            layout: 'hbox',
            items: [{
                items: [{
                    xtype: 'textfield',
                    fieldLabel: '验证码',
                    allowBlank: false,
                    disabled: true,
                    name: 'verifyCode',
                    reference: 'verifyCode',
                    labelWidth: 90,
                    width: 190,
                    height: 30
                }]
            }, {
                padding: '0 0 0 10',
                reference: 'verifyCodeImg',
                html: '<img class="codeImage" width="130" height="30" src="" id="verifyCodeImg"/>'
            }]
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