package com.kk.apollo.biz.model.comment;
public class Comment {
    private Integer commentId;

    private String commentContent;

    private Integer userId;

    private Integer activityId;

    private String commentTime;

    private Integer commentLike;

    private Integer commentDislike;

    public Integer getCommentId() {
        return commentId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }


    public Integer getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(Integer commentLike) {
        this.commentLike = commentLike;
    }

    public Integer getCommentDislike() {
        return commentDislike;
    }

    public void setCommentDislike(Integer commentDislike) {
        this.commentDislike = commentDislike;
    }
}