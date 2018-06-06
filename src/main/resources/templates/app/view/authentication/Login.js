/**
 * 登录视图
 *
 * @author Adoph
 * @date 2017/12/4
 */
Ext.define('Framework.view.authentication.Login', {
    extend: 'Framework.view.authentication.LockingWindow',
    xtype: 'login',

    requires: [
        'Framework.view.authentication.Dialog',
        'Framework.view.authentication.AuthenticationController'
    ],

    header: false,
    border: false,
    defaultFocus: 'authdialog',
    controller: 'authentication',

    items: [
        {
            xtype: 'authdialog',
            reference: 'loginForm',
            defaultButton: 'loginButton',
            autoComplete: true,
            bodyPadding: '20 20',
            cls: 'auth-dialog-login',
            title: 'Framework后台管理系统',
            width: 415,
            layout: {
                type: 'vbox',
                align: 'stretch'
            },

            defaults: {
                margin: '5 0'
            },

            items: [
                {
                    xtype: 'label',
                    text: '请登录你的账户'
                },
                {
                    xtype: 'textfield',
                    cls: 'auth-textbox',
                    name: 'userName',
                    height: 55,
                    hideLabel: true,
                    allowBlank: false,
                    emptyText: '请输入用户名',
                    triggers: {
                        glyphed: {
                            cls: 'trigger-glyph-noop auth-email-trigger'
                        }
                    }
                },
                {
                    xtype: 'textfield',
                    cls: 'auth-textbox',
                    height: 55,
                    hideLabel: true,
                    emptyText: '请输入密码',
                    inputType: 'password',
                    name: 'password',
                    allowBlank: false,
                    triggers: {
                        glyphed: {
                            cls: 'trigger-glyph-noop auth-password-trigger'
                        }
                    }
                },
                {
                    xtype: 'panel',
                    reference: 'verifyCodePanel',
                    hidden: true,
                    border: false,
                    layout: {
                        type: 'hbox',
                        pack: 'start',
                        align: 'stretch'
                    },
                    items: [{
                        flex: 1,
                        xtype: 'textfield',
                        allowBlank: false,
                        disabled: true,
                        name: 'verifyCode',
                        reference: 'verifyCode',
                        emptyText: '请输入验证码'
                    }, {
                        padding: '0 0 0 10',
                        width: 140,
                        xtype: 'panel',
                        height: 56,
                        reference: 'verifyCodeImg',
                        cls: 'verifyCode',
                        html: '<img width="130" height="56" src="" alt="点击刷新" id="verifyCodeImg"/>'
                    }]
                },
                {
                    xtype: 'button',
                    reference: 'loginButton',
                    scale: 'large',
                    ui: 'soft-green',
                    iconAlign: 'right',
                    iconCls: 'x-fa fa-angle-right',
                    text: '登录',
                    formBind: true,
                    listeners: {
                        click: 'onLoginClick'
                    }
                }
            ]
        }
    ],

    initComponent: function () {
        this.addCls('user-login-register-container');
        this.callParent(arguments);
    }
});
