package com.adoph.framework.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 公共pojo
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/11/3
 */
public class BasePojo implements Serializable, Cloneable {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private Long createdBy;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人ID
     */
    private Long updatedBy;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasePojo basePojo = (BasePojo) o;
        return Objects.equals(createTime, basePojo.createTime) &&
                Objects.equals(createdBy, basePojo.createdBy) &&
                Objects.equals(updateTime, basePojo.updateTime) &&
                Objects.equals(updatedBy, basePojo.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createTime, createdBy, updateTime, updatedBy);
    }

    @Override
    public String toString() {
        return "BasePojo{" +
                "createTime=" + createTime +
                ", createdBy=" + createdBy +
                ", updateTime=" + updateTime +
                ", updatedBy=" + updatedBy +
                '}';
    }
}
