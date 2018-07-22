package com.kk.apollo.biz.model.common;

/**
 * Created by Administrator on 2017/10/20.
 */
public class ModelWithMessageVo<T> {
    private T data;
    private boolean success;
    private String message;
    protected int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    @Override
    public String toString() {
        return "ModelWithMessageVo{" +
                "data=" + data +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", total=" + total +
                '}';
    }
}
