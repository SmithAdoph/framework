// 通用反馈工具
var RequestHelper = $req = {};

/**
 * 成功或失败
 * @param status
 * @returns {boolean}
 */
RequestHelper.success = function (status) {
    return status === 'success';
};