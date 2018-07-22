package com.kk.apollo.biz.model.team;

import com.kk.apollo.biz.model.common.CommonListHeaderVo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */
public class AllUserDepVo {
    private int depNum;
    private boolean success;
    private String message;
    private int total;
    private List<CommonListHeaderVo<Deparment>> data;

    public List<CommonListHeaderVo<Deparment>> getData() {
        return data;
    }

    public void setData(List<CommonListHeaderVo<Deparment>> data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDepNum() {
        return depNum;
    }

    public void setDepNum(int depNum) {
        this.depNum = depNum;
    }
}
