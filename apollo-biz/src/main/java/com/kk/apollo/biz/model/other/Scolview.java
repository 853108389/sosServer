package com.kk.apollo.biz.model.other;

public class Scolview {
    private Integer scolviewId;

    private String scolviewImgurl;

    private String scolviewWeburl;

    private String scolviewIndex;

    private String scolviewKey;

    public Integer getScolviewId() {
        return scolviewId;
    }

    public void setScolviewId(Integer scolviewId) {
        this.scolviewId = scolviewId;
    }

    public String getScolviewImgurl() {
        return scolviewImgurl;
    }

    public void setScolviewImgurl(String scolviewImgurl) {
        this.scolviewImgurl = scolviewImgurl == null ? null : scolviewImgurl.trim();
    }

    public String getScolviewWeburl() {
        return scolviewWeburl;
    }

    public void setScolviewWeburl(String scolviewWeburl) {
        this.scolviewWeburl = scolviewWeburl == null ? null : scolviewWeburl.trim();
    }

    public String getScolviewIndex() {
        return scolviewIndex;
    }

    public void setScolviewIndex(String scolviewIndex) {
        this.scolviewIndex = scolviewIndex == null ? null : scolviewIndex.trim();
    }

    public String getScolviewKey() {
        return scolviewKey;
    }

    public void setScolviewKey(String scolviewKey) {
        this.scolviewKey = scolviewKey == null ? null : scolviewKey.trim();
    }
}