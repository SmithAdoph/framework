package com.adoph.framework.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 *
 * @author Adoph
 * @version v1.0
 * @date 2017/10/19
 */
public class Page<T> implements Serializable {

    public Page() {
    }

    public Page(long page, int limit, long count, List<T> rows) {
        this.page = page;
        this.limit = limit;
        this.count = count;
        this.rows = rows;
        this.totalPages = count / limit + (count % limit > 0 ? 1 : 0);
    }

    /**
     * 当前第几页
     */
    private long page;

    /**
     * 每页显示条数
     */
    private int limit;

    /**
     * 总页数
     */
    private long totalPages;

    /**
     * 总条数
     */
    private long count;

    /**
     * 当前页数据
     */
    private List<T> rows;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", limit=" + limit +
                ", totalPages=" + totalPages +
                ", count=" + count +
                '}';
    }
}
