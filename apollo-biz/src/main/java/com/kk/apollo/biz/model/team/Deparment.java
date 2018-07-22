package com.kk.apollo.biz.model.team;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21.
 */
public class Deparment {
    private String deparmentName;//部门名字
    private List<TeamUserVo> deparmentUser;//部门用户列表
    private int deparmentNum;//部门人数

    public List<TeamUserVo> getDeparmentUser() {
        return deparmentUser;
    }

    public void setDeparmentUser(List<TeamUserVo> deparmentUser) {
        this.deparmentUser = deparmentUser;
    }

    public String getDeparmentName() {
        return deparmentName;
    }

    public void setDeparmentName(String deparmentName) {
        this.deparmentName = deparmentName;
    }


    public int getDeparmentNum() {
        return deparmentNum;
    }

    public void setDeparmentNum(int deparmentNum) {
        this.deparmentNum = deparmentNum;
    }
}
