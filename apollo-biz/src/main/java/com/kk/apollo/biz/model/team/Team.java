package com.kk.apollo.biz.model.team;

public class Team {
    private Integer teamId;

    private String teamName;

    private String teamType;

    private String teamIntroduction;

    private String teamActivityplace;

    private String teamAvatar;

    private String teamBackimg;

    private String teamEmail;

    private String teamTel;

    private String teamGroup;

    private String teamRequire;

    private String teamSwipimg;

    private String teamStatus;

    private String teamFoundtime;

    private String teamTitle;

    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle == null ? null : teamTitle.trim();
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType == null ? null : teamType.trim();
    }

    public String getTeamIntroduction() {
        return teamIntroduction;
    }

    public void setTeamIntroduction(String teamIntroduction) {
        this.teamIntroduction = teamIntroduction == null ? null : teamIntroduction.trim();
    }

    public String getTeamActivityplace() {
        return teamActivityplace;
    }

    public void setTeamActivityplace(String teamActivityplace) {
        this.teamActivityplace = teamActivityplace == null ? null : teamActivityplace.trim();
    }

    public String getTeamAvatar() {
        return teamAvatar;
    }

    public void setTeamAvatar(String teamAvatar) {
        this.teamAvatar = teamAvatar == null ? null : teamAvatar.trim();
    }

    public String getTeamBackimg() {
        return teamBackimg;
    }

    public void setTeamBackimg(String teamBackimg) {
        this.teamBackimg = teamBackimg == null ? null : teamBackimg.trim();
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail == null ? null : teamEmail.trim();
    }

    public String getTeamTel() {
        return teamTel;
    }

    public void setTeamTel(String teamTel) {
        this.teamTel = teamTel == null ? null : teamTel.trim();
    }

    public String getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(String teamGroup) {
        this.teamGroup = teamGroup;
    }

    public String getTeamRequire() {
        return teamRequire;
    }

    public void setTeamRequire(String teamRequire) {
        this.teamRequire = teamRequire == null ? null : teamRequire.trim();
    }

    public String getTeamSwipimg() {
        return teamSwipimg;
    }

    public void setTeamSwipimg(String teamSwipimg) {
        this.teamSwipimg = teamSwipimg == null ? null : teamSwipimg.trim();
    }

    public String getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(String teamStatus) {
        this.teamStatus = teamStatus == null ? null : teamStatus.trim();
    }

    public String getTeamFoundtime() {
        return teamFoundtime;
    }

    public void setTeamFoundtime(String teamFoundtime) {
        this.teamFoundtime = teamFoundtime;
    }
}