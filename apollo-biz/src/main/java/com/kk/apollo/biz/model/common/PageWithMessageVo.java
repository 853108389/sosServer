package com.kk.apollo.biz.model.common;

import com.kk.apollo.common.Constant;

/**
 * Created by Administrator on 2017/11/1.
 */
public class PageWithMessageVo<T> extends ModelWithMessageVo {
    private Integer pageIndex; //当前页数
    private Integer pageSize = Constant.PageSize; //每页容量
    private Integer pageNum; //总页数
    private Boolean hasNext;//下一页
    private Boolean hasPre;//上一页

    public PageWithMessageVo() {

    }

    @Override
    public void setTotal(int total) {
        super.total = total;
        this.pageNum = getTotal() % pageSize == 0 ? getTotal()
                / pageSize : getTotal() / pageSize + 1;
        if (this.pageIndex > 1) {
            this.hasPre = true;
        } else {
            this.hasPre = false;
        }
        if (this.pageIndex < pageNum) {
            this.hasNext = true;
        } else {
            this.hasNext = false;
        }
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPre() {
        return hasPre;
    }

    public void setHasPre(Boolean hasPre) {
        this.hasPre = hasPre;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
