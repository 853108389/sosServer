package com.kk.apollo.biz.model.test;

public class Test {
    private Integer id;

    private String testvalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestvalue() {
        return testvalue;
    }

    public void setTestvalue(String testvalue) {
        this.testvalue = testvalue == null ? null : testvalue.trim();
    }
}