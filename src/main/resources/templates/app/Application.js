/**
 * ExtJS启动入口
 *
 * @author adoph
 * @since 2017/11/14
 */
Ext.define('Framework.Application', {
    extend: 'Ext.app.Application',
    name: 'Framework',
    views: ['login.Login'],

    launch: function () {
        var loggedIn = false;
        Ext.create({
            xtype: loggedIn ? 'app-main' : 'login'
        });
    }
});