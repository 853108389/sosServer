package com.kk.apollo.biz.model.activity;

/**
 * Created by Administrator on 2017/10/24.
 */
public class ActivityVo {

    public Integer activityId;

    private String activityName;

    private String activityPushtime;

    private Integer activityLovers;

    private String activityTitle;

    private String activityBackimg;

    private String teamAvatar;

    private Integer commentNum;

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getTeamAvatar() {
        return teamAvatar;
    }

    public void setTeamAvatar(String teamAvatar) {
        this.teamAvatar = teamAvatar;
    }

    public String getActivityBackimg() {
        return activityBackimg;
    }

    public void setActivityBackimg(String activityBackimg) {
        this.activityBackimg = activityBackimg;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPushtime() {
        return activityPushtime;
    }

    public void setActivityPushtime(String activityPushtime) {
        this.activityPushtime = activityPushtime;
    }

    public Integer getActivityLovers() {
        return activityLovers;
    }

    public void setActivityLovers(Integer activityLovers) {
        this.activityLovers = activityLovers;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
}
