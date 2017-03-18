//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.entity;

import java.io.Serializable;

public class PageEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalResultSize;
    private int totalPageSize;
    private boolean first = false;
    private boolean last = false;

    public PageEntity() {
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalResultSize() {
        return this.totalResultSize;
    }

    public void setTotalResultSize(int totalResultSize) {
        this.totalResultSize = totalResultSize;
    }

    public int getTotalPageSize() {
        return this.totalPageSize;
    }

    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public boolean isFirst() {
        return this.getCurrentPage() <= 1;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return this.getCurrentPage() >= this.getTotalPageSize();
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
