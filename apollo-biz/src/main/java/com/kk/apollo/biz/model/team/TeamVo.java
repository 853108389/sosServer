package com.kk.apollo.biz.model.team;

/**
 * Created by Administrator on 2017/10/24.
 */
public class TeamVo {
    private Integer teamId;

    private String teamName;

    private String teamType;

    private String teamStatus;

    private String teamTitle;

    private String teamBackimg;

    public Integer getTeamuserNum() {
        return teamuserNum;
    }

    public void setTeamuserNum(Integer teamuserNum) {
        this.teamuserNum = teamuserNum;
    }

    private Integer teamuserNum;

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
        this.teamName = teamName;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(String teamStatus) {
        this.teamStatus = teamStatus;
    }

    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle;
    }

    public String getTeamBackimg() {
        return teamBackimg;
    }

    public void setTeamBackimg(String teamBackimg) {
        this.teamBackimg = teamBackimg;
    }
}
