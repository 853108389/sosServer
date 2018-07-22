package com.kk.apollo.biz.model.team;

/**
 * Created by Administrator on 2017/10/21.
 */
public class TeamUserVo {

    private Integer userId;
    private String userAvatar;
    private String departmentName;
    private String userTeamType;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
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

}
