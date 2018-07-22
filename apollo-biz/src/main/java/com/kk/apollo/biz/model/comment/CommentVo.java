package com.kk.apollo.biz.model.comment;

/**
 * Created by Administrator on 2017/10/29.
 */
public class CommentVo {
    private String commentContent;
    private String commentTime;
    private Integer commentLike;
    private Integer commentDislike;
    private Integer commentId;
    private Integer userId;
    private String userNickname;
    private String userAvatar;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
