package com.ntvu.web2.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页显示类
 * @param <T> 对应的业务对象类型
 */
public class Pager<T> {

    /**
     * 页面索引
     */
    private int pageIndex;

    /**
     * 页面大小
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总记录条数
     */
    private int totalRecord;

    private List<T> data;

    public Pager() {
        this.pageIndex = 1;
        this.pageSize = 10;
        this.totalPage = 0;
        this.totalRecord = 0;
        this.data = new ArrayList<T>();
    }

    public Pager(int pageIndex, int pageSize, int totalPage, int totalRecord, List<T> data) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalRecord = totalRecord;
        this.data = data;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
