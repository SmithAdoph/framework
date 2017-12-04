/**
 * ExtJS启动入口
 *
 * @author adoph
 * @since 2017/11/14
 */
Ext.define('Framework.Application', {
    extend: 'Ext.app.Application',
    name: 'Framework',
    appFolder: 'app',
    views: [
        'pages.ErrorBase',
        'pages.Error404Window',
        'authentication.Login',
        'main.Main'
    ],

    launch: function () {
        localStorage.removeItem(Constant.LOGIN_ID_TAG);
        var loggedIn = localStorage.getItem(Constant.LOGGED_IN_TAG);
        Ext.create({
            xtype: loggedIn ? 'app-main' : 'login'
        });
    }
});