//package com.kk.apollo.biz.dao;
//
//import com.kk.apollo.biz.model.team.Team;
//import com.kk.apollo.biz.model.user.User;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2018/5/1.
// */
//@Mapper
//public interface WebDao {
//    @Select("select  user_id, user_stuNo, user_name," +
//            " user_birthday, user_nickname,user_password, user_type, " +
//            "user_email,user_isHidden, user_gender from user")
//    public List<User> findUsers(); //查找所有users
//
//    @Select("select team_id, team_name, team_type,  team_activityPlace,team_email," +
//            " team_tel, team_group, team_require, team_status,team_foundTime, team_title from team")
//    public List<Team> findTeams();//查找所有teams
//
//    @Select("select activity_id, activity_name, DATE_FORMAT(activity_pushTime,\"%Y-%m-%d\") as activity_pushTime," +
//            " activity_title, activity_duringTime, activity_holdingTime, activity_endTime,team_id," +
//            "(select count(id) from user_activity where activity_id = activity.activity_id and islove =1) " +
//            "as activity_lovers from activity")
//    public List<Team> findActivities();//查找所有活动
//}
