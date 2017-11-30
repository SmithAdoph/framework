Ext.define('Framework.store.admin.user.User', {
    extend: 'Ext.data.Store',
    requires: ['Framework.model.admin.user.User'],
    model: 'admin.user.User',
    // proxy: {
    //     type: 'ajax',
    //     url: '/sysUser/list.do',
    //     reader: {
    //         type: 'json',
    //         rootProperty: 'data'
    //     }
    // },
    autoLoad: false
});