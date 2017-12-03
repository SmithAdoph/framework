Ext.define('Framework.store.admin.user.User', {
    extend: 'Ext.data.Store',
    alias: 'store.user',
    storeId: 'userStore',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'userName', type: 'string'}
    ],
    data: [
        {id: 1, userName: 'admin'},
        {id: 2, userName: 'test'}
    ],
    // proxy: {
    //     type: 'ajax',
    //     url: '/sysUser/list.do',
    //     reader: {
    //         type: 'json',
    //         rootProperty: 'data'
    //     }
    // },
    autoLoad: true
});