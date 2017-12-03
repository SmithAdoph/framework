Ext.define('Framework.view.pages.Error404Window', {
    extend: 'Framework.view.pages.ErrorBase',
    xtype: 'page404',

    requires: [
        'Framework.view.authentication.AuthenticationController',
        'Ext.container.Container',
        'Ext.form.Label',
        'Ext.layout.container.VBox',
        'Ext.toolbar.Spacer'
    ],

    items: [
        {
            xtype: 'container',
            width: 400,
            cls:'error-page-inner-container',
            layout: {
                type: 'vbox',
                align: 'center',
                pack: 'center'
            },
            items: [
                {
                    xtype: 'label',
                    cls: 'error-page-top-text',
                    text: '404'
                },
                {
                    xtype: 'label',
                    cls: 'error-page-desc',
                    html: '<div>未找到该页面!</div><div> 返回 <a href="/index.do"> 首 页 </a></div>'
                },
                {
                    xtype: 'tbspacer',
                    flex: 1
                }
            ]
        }
    ]
});
