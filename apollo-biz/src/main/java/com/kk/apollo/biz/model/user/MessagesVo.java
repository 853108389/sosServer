package com.kk.apollo.biz.model.user;

/**
 * Created by Administrator on 2017/12/17.
 */
public class MessagesVo {
    private Integer messagesId;
    private String messagesFromtype;//0为用户1为社团
    private String messagesFromid;//具体的用户或者社团id
    private String messagesFromAvatar;//头像
    private String messagesContent;//消息内容
    private String messagesDate; //消息时间
    private String messagesTitle; //发布者
    private Integer messagesType;//消息类型
    private Integer messagesNum;//消息类型
    private Integer messagesTotype; //消息接受者的类型
    private Integer messagesToid;//消息接受者id

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

    public Integer getMessagesNum() {
        return messagesNum;
    }

    public void setMessagesNum(Integer messagesNum) {
        this.messagesNum = messagesNum;
    }

    public Integer getMessagesType() {
        return messagesType;
    }

    public void setMessagesType(Integer messagesType) {
        this.messagesType = messagesType;
    }

    public String getMessagesTitle() {
        return messagesTitle;
    }

    public void setMessagesTitle(String messagesTitle) {
        this.messagesTitle = messagesTitle;
    }

    public Integer getMessagesId() {
        return messagesId;
    }

    public void setMessagesId(Integer messagesId) {
        this.messagesId = messagesId;
    }

    public String getMessagesFromtype() {
        return messagesFromtype;
    }

    public void setMessagesFromtype(String messagesFromtype) {
        this.messagesFromtype = messagesFromtype;
    }

    public String getMessagesFromid() {
        return messagesFromid;
    }

    public void setMessagesFromid(String messagesFromid) {
        this.messagesFromid = messagesFromid;
    }

    public String getMessagesFromAvatar() {
        return messagesFromAvatar;
    }

    public void setMessagesFromAvatar(String messagesFromAvatar) {
        this.messagesFromAvatar = messagesFromAvatar;
    }

    public String getMessagesContent() {
        return messagesContent;
    }

    public void setMessagesContent(String messagesContent) {
        this.messagesContent = messagesContent;
    }

    public String getMessagesDate() {
        return messagesDate;
    }

    public void setMessagesDate(String messagesDate) {
        this.messagesDate = messagesDate;
    }
}
