package com.kk.apollo.biz.model.feedback;

public class Feedback {
    private Integer feedbackId;

    private Integer userId;

    private String feedbackType;

    private String feedbackConway;

    private String feedbackContent;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType == null ? null : feedbackType.trim();
    }

    public String getFeedbackConway() {
        return feedbackConway;
    }

    public void setFeedbackConway(String feedbackConway) {
        this.feedbackConway = feedbackConway == null ? null : feedbackConway.trim();
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }
}