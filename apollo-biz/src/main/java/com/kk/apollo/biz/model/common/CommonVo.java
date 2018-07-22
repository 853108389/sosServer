package com.kk.apollo.biz.model.common;

/**
 * {
 * id:xx,
 * lable:xx,
 * dataKey:xx,
 * dataValue:xx,
 * }
 */
public class CommonVo {
    public int id;
    public String lable;
    public String dataKey;
    public String dataValue;

    public CommonVo() {

    }

    public CommonVo(int id, String lable, String dataKey, String dataValue) {
        this.id = id;
        this.lable = lable;
        this.dataKey = dataKey;
        this.dataValue = dataValue;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    @Override
    public String toString() {
        return "CommonVo{" +
                "id=" + id +
                ", lable='" + lable + '\'' +
                ", dataKey='" + dataKey + '\'' +
                ", dataValue='" + dataValue + '\'' +
                '}';
    }

}
