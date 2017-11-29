/**
 * 登录控制器
 *
 * @author adoph
 * @since 2017/11/14
 */
Ext.define('Framework.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',
    requires: ['Framework.view.main.Main'],
    // views: ['main.Main'],

    onLoginClick: function () {
        var loginId = localStorage.getItem(Constant.LOGIN_ID_TAG);
        if (!loginId) {
            loginId = uuidv4();
            localStorage.setItem(Constant.LOGIN_ID_TAG, loginId);
        }
        this.authPubKey(loginId);
    },

    //获取公钥
    authPubKey: function (loginId) {
        var url = "/login/authPubKey.do?loginId=" + loginId;
        Ext.Ajax.request({
            url: url,
            success: function (response, opts) {
                this.doLogin(loginId, response.responseText);
            },
            failure: function (response, opts) {
                Ext.Msg.alert("系统提示", "登录异常，请联系管理员！");
            },
            scope: this
        });
    },

    //登录
    doLogin: function (loginId, pubKey) {
        var me = this;
        var url = "/login/doLogin.do";
        var form = this.lookup('form').getForm();
        var params = form.getFieldValues();
        params.password = this.encrypt(params.password, pubKey);
        params.loginId = loginId;
        Ext.Ajax.request({
            url: url,
            params: params,
            success: function (response, opts) {
                var r = Ext.decode(response.responseText);
                if (r.status === 'success') {
                    Ext.toast({
                        html: '登录成功！',
                        closable: false,
                        align: 't',
                        slideInDuration: 400,
                        minWidth: 350,
                        iconCls: 'right-icon',
                        title: '登录提示',
                        autoCloseDelay: 4000
                    });
                    localStorage.setItem(Constant.LOGGED_IN_TAG, '1');
                    me.getView().destroy();
                    Ext.create({
                        xtype: 'app-main'
                    })
                } else {
                    if (r.data && r.data['showVerifyCode'] === 1) {
                        var verifyCodePanel = me.lookup('verifyCodePanel');
                        var verifyCode = verifyCodePanel.query('textfield[name=verifyCode]')[0];
                        if (verifyCodePanel.isVisible()) {
                            verifyCode.setValue();
                        } else {
                            verifyCodePanel.show();
                            verifyCode.enable();
                        }
                        me.refreshCode(loginId);
                    }
                    Ext.toast({
                        html: r.msg,
                        closable: false,
                        align: 't',
                        slideInDuration: 400,
                        minWidth: 350,
                        iconCls: 'error-icon',
                        title: '登录提示',
                        autoCloseDelay: 4000
                    });
                }
            },
            failure: function (response, opts) {
                Ext.Msg.alert("系统提示", "登录异常，请联系管理员！");
            },
            scope: this
        });
    },

    //加密
    encrypt: function (password, pubKey) {
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey(pubKey);
        return encrypt.encrypt(password);
    },

    //刷新验证码
    refreshCode: function (loginId) {
        var second = new Date().getTime();
        var verifyCodeImg = Ext.get("verifyCodeImg");
        verifyCodeImg.dom.src = "/login/verifyCode.do?loginId=" + loginId + "&uuid=" + second;
    }
});