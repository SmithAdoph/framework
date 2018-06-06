/**
 * ExtJS启动入口
 *
 * @author adoph
 * @date 2017/11/14
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
        console.log("Application launch!");
        var loggedIn = localStorage.getItem(Constant.LOGGED_IN_TAG);
        if (!loggedIn) {
            localStorage.clear();
        }
        Ext.create({
            xtype: loggedIn ? 'app-main' : 'login'
        });
    }
});