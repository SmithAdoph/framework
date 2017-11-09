package com.adoph.framework.pojo.layui;

import java.io.Serializable;
import java.util.List;

/**
 * 基于layui的分页POJO
 *
 * @author Adoph
 * @version v1.0
 * @since 2017/10/19
 */
public class Page<T> implements Serializable {

    /**
     * 当前第几页
     */
    private int page;

    /**
     * 每页显示条数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 总条数
     */
    private int count;

    /**
     * 当前页数据
     */
    private List<T> rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", count=" + count +
                '}';
    }
}
