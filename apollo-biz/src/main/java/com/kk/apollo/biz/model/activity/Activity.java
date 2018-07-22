package com.kk.apollo.biz.model.activity;

public class Activity {
    @Deprecated
    private String activityHoldingtime;
    @Deprecated
    private String activityEndtime;

    private Integer teamId;

    private Integer activityId;

    private String activityName;

    private String activityPushtime;

    private Integer activityLovers;

    private String activityTitle;

    private String activityIntroduction;

    private String activityDuringtime;

    private String activityPlace;

    private String activityWay;

    private String activityMemo;

    private String activityRequire;

    private String activityConnectway;

    private String activityImage;

    private String activityBackimg;

    public String getActivityBackimg() {
        return activityBackimg;
    }

    public void setActivityBackimg(String activityBackimg) {
        this.activityBackimg = activityBackimg;
    }

    public String getActivityPushtime() {
        return activityPushtime;
    }

    public void setActivityPushtime(String activityPushtime) {
        this.activityPushtime = activityPushtime;
    }

    public String getActivityHoldingtime() {
        return activityHoldingtime;
    }

    public void setActivityHoldingtime(String activityHoldingtime) {
        this.activityHoldingtime = activityHoldingtime;
    }

    public String getActivityEndtime() {
        return activityEndtime;
    }

    public void setActivityEndtime(String activityEndtime) {
        this.activityEndtime = activityEndtime;
    }


    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
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
        this.activityTitle = activityTitle == null ? null : activityTitle.trim();
    }

    public String getActivityIntroduction() {
        return activityIntroduction;
    }

    public void setActivityIntroduction(String activityIntroduction) {
        this.activityIntroduction = activityIntroduction == null ? null : activityIntroduction.trim();
    }

    public String getActivityDuringtime() {
        return activityDuringtime;
    }

    public void setActivityDuringtime(String activityDuringtime) {
        this.activityDuringtime = activityDuringtime == null ? null : activityDuringtime.trim();
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace == null ? null : activityPlace.trim();
    }

    public String getActivityWay() {
        return activityWay;
    }

    public void setActivityWay(String activityWay) {
        this.activityWay = activityWay == null ? null : activityWay.trim();
    }

    public String getActivityMemo() {
        return activityMemo;
    }

    public void setActivityMemo(String activityMemo) {
        this.activityMemo = activityMemo == null ? null : activityMemo.trim();
    }

    public String getActivityRequire() {
        return activityRequire;
    }

    public void setActivityRequire(String activityRequire) {
        this.activityRequire = activityRequire == null ? null : activityRequire.trim();
    }

    public String getActivityConnectway() {
        return activityConnectway;
    }

    public void setActivityConnectway(String activityConnectway) {
        this.activityConnectway = activityConnectway == null ? null : activityConnectway.trim();
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage == null ? null : activityImage.trim();
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Activity{" +
                " teamId=" + teamId +
                ", activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", activityPushtime='" + activityPushtime + '\'' +
                ", activityLovers=" + activityLovers +
                ", activityTitle='" + activityTitle + '\'' +
                ", activityIntroduction='" + activityIntroduction + '\'' +
                ", activityDuringtime='" + activityDuringtime + '\'' +
                ", activityPlace='" + activityPlace + '\'' +
                ", activityWay='" + activityWay + '\'' +
                ", activityMemo='" + activityMemo + '\'' +
                ", activityRequire='" + activityRequire + '\'' +
                ", activityConnectway='" + activityConnectway + '\'' +
                ", activityImage='" + activityImage + '\'' +
                ", activityBackimg='" + activityBackimg + '\'' +
                '}';
    }
}