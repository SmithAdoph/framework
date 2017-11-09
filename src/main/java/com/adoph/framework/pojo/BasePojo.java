package com.adoph.framework.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共pojo
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/11/3
 */
public class BasePojo implements Serializable {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人ID
     */
    private Long updateBy;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasePojo basePojo = (BasePojo) o;

        if (createTime != null ? !createTime.equals(basePojo.createTime) : basePojo.createTime != null) return false;
        if (createBy != null ? !createBy.equals(basePojo.createBy) : basePojo.createBy != null) return false;
        if (updateTime != null ? !updateTime.equals(basePojo.updateTime) : basePojo.updateTime != null) return false;
        return updateBy != null ? updateBy.equals(basePojo.updateBy) : basePojo.updateBy == null;
    }

    @Override
    public int hashCode() {
        int result = createTime != null ? createTime.hashCode() : 0;
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BasePojo{" +
                "createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                '}';
    }
}
