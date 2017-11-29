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
    LOGIN_ID_TAG: 'FRAMEWORK_LOGIN_ID',
    LOGGED_IN_TAG: 'FRAMEWORK_LOGGED_IN'
};

/**
 * 是否登录
 * @type {boolean}
 */
window.loggedIn = false;
