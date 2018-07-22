package com.kk.apollo.biz.model.user;

/**
 * Created by Administrator on 2017/10/20.
 */
public class UserTeamVo {
    private String teamId;
    private String teamName;
    private String teamBackImg;
    private String teamType;
    private String departmentName;
    private String userTeamType;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamBackImg() {
        return teamBackImg;
    }

    public void setTeamBackImg(String teamBackImg) {
        this.teamBackImg = teamBackImg;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUserTeamType() {
        return userTeamType;
    }

    public void setUserTeamType(String userTeamType) {
        this.userTeamType = userTeamType;
    }

    @Override
    public String toString() {
        return "UserTeamVo{" +
                "teamName='" + teamName + '\'' +
                ", teamBackImg='" + teamBackImg + '\'' +
                ", teamType='" + teamType + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", userTeamType='" + userTeamType + '\'' +
                '}';
    }
}
