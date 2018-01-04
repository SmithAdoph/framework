// 通用反馈工具
var RequestHelper = $Req = {};

/**
 * 成功或失败
 * @param status
 * @returns {boolean}
 */
RequestHelper.isSuccess = function (status) {
    return status === 'success';
};

var Constant = $Constant = {
    /*登录id*/
    LOGIN_ID_TAG: 'FRAMEWORK_LOGIN_ID',
    /*是否登录*/
    LOGGED_IN_TAG: 'FRAMEWORK_LOGGED_IN',
    /*登录用户信息*/
    LOGIN_ONLINE: 'FRAMEWORK_LOGGED_ONLINE'
};

var Sys = {};

/**
 * 获取当前登录用户信息
 */
Sys.getOnline = function () {
    var loggedIn = localStorage.getItem($Constant.LOGGED_IN_TAG);
    if (loggedIn === '1') {
        var online = Ext.decode(localStorage.getItem($Constant.LOGIN_ONLINE));
        if (online) {
            return online['sysUser'];
        }
    }
    return null;
};

/**
 * 是否登录
 * @type {boolean}
 */
window.loggedIn = false;
