package com.kk.apollo.utils;

import com.kk.apollo.biz.model.activity.Activity;
import com.kk.apollo.biz.model.activity.ActivityAddVo;
import com.kk.apollo.biz.model.common.CommonVo;
import com.kk.apollo.biz.model.common.ImageObj;
import com.kk.apollo.biz.model.team.Team;
import com.kk.apollo.biz.model.team.TeamDetailVo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5.
 */
public class ObjectDataTypeParser {
    /**
     * 添加Activity
     *
     * @param activityAddVo
     * @return
     */
    public static Activity parseActivityAddVo(ActivityAddVo activityAddVo) {
        Activity activity = new Activity();
        List<CommonVo> activityInfo = activityAddVo.getActivityInfo();
        List<CommonVo> activityRequire = activityAddVo.getActivityRequire();
        List<CommonVo> connectWay = activityAddVo.getConnectWay();
        List<CommonVo> memo = activityAddVo.getMemo();
        List<ImageObj> activityImg = activityAddVo.getActivityImg();
        List<CommonVo> activityWay = activityAddVo.getActivityWay();
        ImageObj activityBackimg = activityAddVo.getActivityBackimg();
        activity.setActivityConnectway(parseCvListToString(connectWay));
        activity.setActivityRequire(parseCvListToString(activityRequire));
        activity.setActivityMemo(parseCvListToString(memo));
        activity.setActivityWay(parseCvListToString(activityWay));
        activity.setActivityImage(parseImgListToString(activityImg));
        activity.setActivityBackimg(activityBackimg.getUri());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatTime = sdf.format(date);
        activity.setActivityPushtime(formatTime);
        for (CommonVo commonVo : activityInfo) {
            String value = commonVo.getDataValue();
            switch (commonVo.getDataKey()) {
                case "introduction"://介绍
                    activity.setActivityIntroduction(value);
                    break;
                case "teamId":
                    activity.setTeamId(Integer.parseInt(value));
                    break;
                case "activityTitle": //简介
                    activity.setActivityTitle(value);
                    break;
                case "activityTime":
                    activity.setActivityDuringtime(value);
                    break;
                case "activityPlace":
                    activity.setActivityPlace(value);
                    break;
                case "activityName":
                    activity.setActivityName(value);
                    break;
                default:
                    break;
            }
        }
        return activity;
    }

    /**
     * 添加Team
     * teamDetailVo => Team
     * @param teamDetailVo
     * @return
     */
    public static Team parserteamDetailVo2Team(TeamDetailVo teamDetailVo) {
        Team team = new Team();
        team.setTeamId(teamDetailVo.getTeamId());
        List<CommonVo> infoList = teamDetailVo.getTeamInfo();
        List<CommonVo> connectWayList = teamDetailVo.getConnectWay();
        List<CommonVo> teamRequire = teamDetailVo.getTeamRequire();
        team.setTeamRequire(parseCvListToString(teamRequire));
        for (CommonVo commonVo : connectWayList) {
            switch (commonVo.getDataKey()) {
                case "teamEmail":
                    team.setTeamEmail(commonVo.getDataValue());
                    break;
                case "teamTel":
                    team.setTeamTel(commonVo.getDataValue());
                case "teamGroup":
                    team.setTeamGroup(commonVo.getDataValue());
            }
        }
        for (CommonVo commonVo : infoList) {
            switch (commonVo.getDataKey()) {
                //社团名字不可以修改
//                case "teamName":
//                    break;
                case "teamActivityplace":
                    team.setTeamActivityplace(commonVo.getDataValue());
                    break;
                //图片逻辑不在这里处理
//                case "teamAvatar":
//                    break;
                //状态暂不可以修改
//                case "teamStatus":
//                    break;
                //成立时间不可以由用户更新
//                case "teamFoundtime":
//                    break;
                case "teamIntroduction":
                    team.setTeamIntroduction(commonVo.getDataValue());
                    break;
                case "teamTitle":
                    team.setTeamTitle(commonVo.getDataValue());
                    break;
                default:
                    break;
            }
        }
        return team;
    }


    /**
     * 拼接LIST里所有数据, 以<#>分隔返回拼接好的字符串
     */
    public static String parseCvListToString(List<CommonVo> list) {
        if (isEmptyList(list)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (CommonVo commonVo : list) {
            sb.append("<#>");
            sb.append(commonVo.getDataValue());
        }
        return sb.toString();
    }

    public static String parseListToString(List<String> list) {
        if (isEmptyList(list)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (String value : list) {
            sb.append("<#>");
            sb.append(value);
        }
        return sb.toString();
    }

    public static String parseImgListToString(List<ImageObj> list) {
        if (isEmptyList(list)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (ImageObj imageObj : list) {
            sb.append("<#>");
            sb.append(imageObj.getUri());
        }
        return sb.toString();
    }

    public static boolean isEmptyList(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

}
