package com.kk.apollo.biz.model.common;

import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */
public class CommonListHeaderVo<T> {
    private String title; //标题
    private Integer size; //此标题下所有数据大小
    private List<T> data; //数据

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
