package com.adoph.framework.permission.vo;

import com.adoph.framework.web.request.BasePageRequest;

import java.io.Serializable;

/**
 * 角色列表表查询请求参数
 *
 * @author Adoph
 * @version v1.0
 * @date 2018/1/15
 */
public class RoleRequest extends BasePageRequest implements Serializable {

    /**
     * 角色名称
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
