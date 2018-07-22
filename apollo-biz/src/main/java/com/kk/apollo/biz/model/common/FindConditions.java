package com.kk.apollo.biz.model.common;

import com.kk.apollo.common.Constant;

/**
 * Created by Administrator on 2017/11/1.
 */
public class FindConditions<T> {
    private Integer pageIndex; //当前页码
    private Integer pageSize = Constant.PageSize; //每页容量
    private String key;//模糊查询的key
    private String value; //模糊查询的value
    private String orderBy; //排序的key
    private String isDesc; //是否降序
    private Integer pageIndexNum; //当前页数-1*每页容量
    private T data; //要查询的对象


    public String getIsDesc() {
        return isDesc;
    }

    public void setIsDesc(String isDesc) {
        this.isDesc = isDesc;
    }

    public Integer getPageIndexNum() {
        return pageIndexNum;
    }

    public void setPageIndexNum(Integer pageIndexNum) {
        this.pageIndexNum = pageIndexNum;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        if(pageIndex>=1){
            pageIndexNum = (pageIndex - 1) * pageSize;
        }else{
            pageIndexNum = 0;
        }
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

}
