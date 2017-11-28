/**
 * 登录控制器
 *
 * @author adoph
 * @since 2017/11/14
 */
Ext.define('Framework.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    onLoginClick: function () {
        var LOGIN_ID_TAG = "FRAMEWORK_LOGIN_ID";
        var loginId = localStorage.getItem(LOGIN_ID_TAG);
        if (!loginId) {
            localStorage.setItem(LOGIN_ID_TAG, uuidv4());
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
                debugger;
                Ext.Msg.alert("系统提示", "登录异常，请联系管理员！");
            },
            scope: this
        });
    },

    //登录
    doLogin: function (loginId, pubKey) {
        var url = "/login/doLogin.do";
        var form = this.lookup('form').getForm();
        var params = form.getFieldValues();
        params.password = this.encrypt(params.password, pubKey);
        params.loginId = loginId;
        Ext.Ajax.request({
            url: url,
            params: params,
            // extraParams: {
            //     loginId: loginId
            // },
            success: function (response, opts) {
                var r = Ext.decode(response.responseText);
                debugger;
                if (r.status == 'success') {
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
                    // localStorage.setItem("TutorialLoggedIn", true);
                    // this.getView().destroy();
                    // Ext.create({
                    //     xtype: 'app-main'
                    // });
                } else {
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
                debugger;
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
    }
});