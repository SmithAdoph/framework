package com.adoph.framework.pojo;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 公共pojo
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/11/3
 */
@MappedSuperclass
public class BasePojo implements Serializable, Cloneable {

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
        return Objects.equals(createTime, basePojo.createTime) &&
                Objects.equals(createBy, basePojo.createBy) &&
                Objects.equals(updateTime, basePojo.updateTime) &&
                Objects.equals(updateBy, basePojo.updateBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createTime, createBy, updateTime, updateBy);
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
