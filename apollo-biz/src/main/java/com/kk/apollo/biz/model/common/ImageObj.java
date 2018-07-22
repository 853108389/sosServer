package com.kk.apollo.biz.model.common;

/**
 * Created by Administrator on 2017/12/12.
 */
public class ImageObj {
    private String uri;
    private Integer width;
    private Integer height;
    private String mime;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }
}
