/**
 * Framework主页
 */
Ext.define('Framework.view.main.Main', {
    extend: 'Ext.panel.Panel',
    xtype: 'app-main',
    requires: [
        'Ext.plugin.Viewport',
        'Ext.layout.container.Border',
        ['Ext.Menu'],
        'Ext.ux.BoxReorderer',

        'Framework.view.main.MainController'
    ],
    controller: 'main',
    layout: 'border',
    plugins: 'viewport',
    defaults: {
        collapsible: true,
        split: true,
        bodyPadding: 10,
        xtype: 'panel'
    },
    items: [{
        title: 'Framework后台管理系统',
        region: 'north',
        height: 100,
        minHeight: 75,
        maxHeight: 150,
        fullscreen: true,
        items: [{
            xtype: 'menu',
            width: 100,
            plain: true,
            floating: false,  // usually you want this set to True (default)
            renderTo: Ext.getBody(),  // usually rendered by it's containing component
            items: [{
                text: 'plain item 1'
            },{
                text: 'plain item 2'
            },{
                text: 'plain item 3'
            }]
        }]
    }, {
        title: 'Footer',
        region: 'south',
        height: 100,
        minHeight: 75,
        maxHeight: 150,
        html: '<p>Footer content</p>'
    }, {
        title: 'Navigation',
        region: 'west',
        margin: '5 0 0 0',
        width: 125,
        minWidth: 100,
        maxWidth: 250,
        html: '<p>Secondary content like navigation links could go here</p>',
    }, {
        title: 'Main Content',
        collapsible: false,
        region: 'center',
        margin: '5 0 0 0',
        html: '<h2>Main Page</h2><p>This is where the main content would go</p>'
    }
    ]
})
;