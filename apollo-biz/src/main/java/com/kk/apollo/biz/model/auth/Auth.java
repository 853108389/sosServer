package com.kk.apollo.biz.model.auth;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/3.
 */
public class Auth {
    private Integer userId; //用户id
    private String userType; //用户类型
    private Map<String,String> teamTypeMap; //teamId => userTeamType 根据社团获得其级别
    private Map<String,List<String>> revteamTypeMap; //userTeamType  => teamId 根据社团级别获取社团名字集合

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Map<String, List<String>> getRevteamTypeMap() {
        return revteamTypeMap;
    }

    public void setRevteamTypeMap(Map<String, List<String>> revteamTypeMap) {
        this.revteamTypeMap = revteamTypeMap;
    }

    public Map<String, String> getTeamTypeMap() {
        return teamTypeMap;
    }

    public void setTeamTypeMap(Map<String, String> teamTypeMap) {
        this.teamTypeMap = teamTypeMap;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
