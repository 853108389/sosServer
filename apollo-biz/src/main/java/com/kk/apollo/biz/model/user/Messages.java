package com.kk.apollo.biz.model.user;

import java.util.Map;

public class Messages {
    private Map<String,String> messagesParams; //上传消息时,附带的参数

    private Integer messagesId;

    private Integer messagesTotype;

    private Integer messagesToid;

    private Integer messagesFromtype;

    private Integer messagesFromid;

    private String messagesDate;

    private String messagesContent;

    private Integer messagesTodelete;

    private Integer messagesFromdelete;

    private Integer messagesType;

    private Integer messagesNum;

    public Map<String, String> getMessagesParams() {
        return messagesParams;
    }

    public void setMessagesParams(Map<String, String> messagesParams) {
        this.messagesParams = messagesParams;
    }

    public Integer getMessagesId() {
        return messagesId;
    }

    public void setMessagesId(Integer messagesId) {
        this.messagesId = messagesId;
    }

    public Integer getMessagesTotype() {
        return messagesTotype;
    }

    public void setMessagesTotype(Integer messagesTotype) {
        this.messagesTotype = messagesTotype;
    }

    public Integer getMessagesToid() {
        return messagesToid;
    }

    public void setMessagesToid(Integer messagesToid) {
        this.messagesToid = messagesToid;
    }

    public Integer getMessagesFromtype() {
        return messagesFromtype;
    }

    public void setMessagesFromtype(Integer messagesFromtype) {
        this.messagesFromtype = messagesFromtype;
    }

    public Integer getMessagesFromid() {
        return messagesFromid;
    }

    public void setMessagesFromid(Integer messagesFromid) {
        this.messagesFromid = messagesFromid;
    }

    public String getMessagesDate() {
        return messagesDate;
    }

    public void setMessagesDate(String messagesDate) {
        this.messagesDate = messagesDate;
    }

    public String getMessagesContent() {
        return messagesContent;
    }

    public void setMessagesContent(String messagesContent) {
        this.messagesContent = messagesContent == null ? null : messagesContent.trim();
    }

    public Integer getMessagesTodelete() {
        return messagesTodelete;
    }

    public void setMessagesTodelete(Integer messagesTodelete) {
        this.messagesTodelete = messagesTodelete;
    }

    public Integer getMessagesFromdelete() {
        return messagesFromdelete;
    }

    public void setMessagesFromdelete(Integer messagesFromdelete) {
        this.messagesFromdelete = messagesFromdelete;
    }

    public Integer getMessagesType() {
        return messagesType;
    }

    public void setMessagesType(Integer messagesType) {
        this.messagesType = messagesType;
    }

    public Integer getMessagesNum() {
        return messagesNum;
    }

    public void setMessagesNum(Integer messagesNum) {
        this.messagesNum = messagesNum;
    }
}