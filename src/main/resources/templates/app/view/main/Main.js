/**
 * Framework主页
 */
Ext.define('Framework.view.main.Main', {
    extend: 'Ext.container.Viewport',
    xtype: 'app-main',
    requires: [
        'Ext.button.Segmented',
        'Ext.list.Tree',

        'Framework.view.main.MainContainerWrap',
        'Framework.view.admin.user.User',
        'Framework.view.admin.role.Role',
        'Framework.view.admin.menu.Menu',

        'Framework.view.main.MainController'
    ],
    viewModel: {
        data: {userName: null}
    },

    controller: 'main',

    cls: 'sencha-dash-viewport',
    itemId: 'mainView',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [
        {
            xtype: 'toolbar',
            cls: 'sencha-dash-dash-headerbar shadow',
            height: 64,
            itemId: 'headerBar',
            items: [
                {
                    xtype: 'component',
                    reference: 'senchaLogo',
                    cls: 'sencha-logo',
                    html: '<div class="main-logo"><img src="/image/logo.png">Framework后台管理</div>',
                    width: 250
                },
                {
                    margin: '0 0 0 8',
                    ui: 'header',
                    iconCls: 'x-fa fa-navicon',
                    id: 'main-navigation-btn',
                    handler: 'onToggleNavigationSize'
                },
                '->',
                // {
                //     iconCls: 'x-fa fa-th-large',
                //     ui: 'header',
                //     href: '#profile',
                //     hrefTarget: '_self',
                //     tooltip: 'See your profile'
                // },
                {
                    iconCls: 'x-fa fa-sign-out',
                    ui: 'header',
                    handler: 'logout',
                    tooltip: '注销登录'
                },
                '-',
                {
                    xtype: 'tbtext',
                    bind: '{userName}',
                    cls: 'top-user-name'
                },
                {
                    xtype: 'image',
                    cls: 'header-right-profile-image',
                    height: 35,
                    width: 35,
                    alt: 'current user image',
                    src: '/image/admin/defualt.jpg'
                }
            ]
        },
        {
            xtype: 'maincontainerwrap',
            id: 'main-view-detail-wrap',
            reference: 'mainContainerWrap',
            flex: 1,
            items: [
                {
                    xtype: 'treelist',
                    width: 250,
                    reference: 'navigationTreeList',
                    itemId: 'navigationTreeList',
                    ui: 'navigation',
                    store: {
                        root: {
                            expanded: true,
                            children: [{
                                text: ' 系统管理',
                                expanded: true,
                                iconCls: 'x-fa fa-cogs',
                                children: [{
                                    text: ' 用户管理',
                                    leaf: true,
                                    iconCls: 'x-fa fa-user-plus',
                                    // routeId: 'user-grid',
                                    viewType: 'user-grid'
                                }, {
                                    text: ' 菜单管理',
                                    leaf: true,
                                    iconCls: 'x-fa fa-bars',
                                    viewType: 'menu-grid'
                                }, {
                                    text: ' 角色管理',
                                    leaf: true,
                                    iconCls: 'x-fa fa-user-secret',
                                    viewType: 'role-grid'
                                }, {
                                    text: ' 权限管理',
                                    leaf: true,
                                    iconCls: 'x-fa fa-ship'
                                }]
                            }]
                        }
                    },
                    expanderFirst: false,
                    expanderOnly: false,
                    listeners: {
                        selectionchange: 'onNavigationTreeSelectionChange'
                    }
                },
                {
                    margin: '20 20 20 20',
                    xtype: 'container',
                    flex: 1,
                    reference: 'mainCardPanel',
                    cls: 'sencha-dash-right-main-container',
                    itemId: 'contentPanel',
                    layout: {
                        type: 'card',
                        anchor: '100%'
                    }
                }
            ]
        }
    ]
});