/**
 * 用户列表视图
 *
 * @author Adoph
 * @since 2017/11/30
 */
Ext.define('Framework.view.admin.user.User', {
    extend: 'Ext.grid.Panel',
    requires: ['Framework.store.admin.user.User'],
    xtype: 'user-grid',
    title: '用户列表',
    width: 750,
    height: 350,
    store: 'admin.user.User',
    columns: [{
        text: 'id',
        flex: 1,
        sortable: false,
        dataIndex: 'id'
    }, {
        text: '用户名',
        width: 95,
        sortable: true,
        dataIndex: 'userName'
    }]
});