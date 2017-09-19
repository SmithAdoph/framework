// 通用反馈工具
var RequestHelper = $req = {};

/**
 * 成功或失败
 * @param status
 * @returns {boolean}
 */
RequestHelper.isSuccess = function (status) {
    return status === 'success';
};