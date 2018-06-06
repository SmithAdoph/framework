package com.adoph.framework.web.request;

import java.io.Serializable;

/**
 * 分页基本请求参数
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/12/3
 */
public class BasePageRequest implements Serializable {
    /**
     * 当前第几页：默认第1页
     */
    private long page = 1;

    /**
     * 从第几条开始
     */
    private long start = 0;

    /**
     * 每页显示条数：默认15条
     */
    private int limit = 15;

    public long getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }
}
