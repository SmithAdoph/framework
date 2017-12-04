/**
 * 登录ViewModel
 *
 * @author Adoph
 * @since 2017/12/4
 */
Ext.define('Framework.view.authentication.AuthenticationModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.authentication',
    data: {
        userName: '',
        password: '',
        persist: false,
        agrees: false
    }
});