package com.kk.apollo.biz.model.team;

import com.kk.apollo.biz.model.common.CommonVo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/28.
 */
public class TeamDetailVo {
    //只能客户端向服务端提交请求时使用
    private Integer teamId;
    //用户的权限
    private String userType; //用户类型

    private List<CommonVo> teamInfo;

    private List<CommonVo>  connectWay;

    private List<CommonVo>  teamRequire;

    private String[] teamSwipimg;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public List<CommonVo> getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(List<CommonVo> teamInfo) {
        this.teamInfo = teamInfo;
    }

    public List<CommonVo> getConnectWay() {
        return connectWay;
    }

    public void setConnectWay(List<CommonVo> connectWay) {
        this.connectWay = connectWay;
    }

    public List<CommonVo> getTeamRequire() {
        return teamRequire;
    }

    public void setTeamRequire(List<CommonVo> teamRequire) {
        this.teamRequire = teamRequire;
    }

    public String[] getTeamSwipimg() {
        return teamSwipimg;
    }

    public void setTeamSwipimg(String[] teamSwipimg) {
        this.teamSwipimg = teamSwipimg;
    }
}
