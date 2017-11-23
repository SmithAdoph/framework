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
        debugger;
        var url = "/login/doLogin.do";
        Ext.Ajax.request({
            url: url,
            data: params,
            // defaultPostHeader : "",
            success: function (response, opts) {
                var obj = Ext.decode(response.responseText);
                console.dir(obj);
            },
            failure: function (response, opts) {
                console.log('server-side failure with status code ' + response.status);
            }
        });
        localStorage.setItem("TutorialLoggedIn", true);
        // Remove Login Window
        this.getView().destroy();
        Ext.create({
            xtype: 'app-main'
        });
    }
});