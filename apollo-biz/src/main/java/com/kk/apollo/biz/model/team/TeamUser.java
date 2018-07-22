package com.kk.apollo.biz.model.team;

import com.kk.apollo.biz.model.user.Messages;

/**
 * 对应 user_team
 */
public class TeamUser extends Messages{

    private Integer departmentId;

    private Integer userId;

    private Integer teamId;

    private String departmentName;

    private String userTeamType;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public String getUserTeamType() {
        return userTeamType;
    }

    public void setUserTeamType(String userTeamType) {
        this.userTeamType = userTeamType == null ? null : userTeamType.trim();
    }
}