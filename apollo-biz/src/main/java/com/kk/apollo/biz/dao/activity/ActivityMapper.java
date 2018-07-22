package com.kk.apollo.biz.dao.activity;

import com.kk.apollo.biz.model.activity.Activity;
import com.kk.apollo.biz.model.activity.ActivityDetail;
import com.kk.apollo.biz.model.activity.ActivityVo;
import com.kk.apollo.biz.model.common.FindConditions;

import java.util.List;

public interface ActivityMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<ActivityVo> findActivityByTeamId(int teamId);

    List<ActivityVo> findAll();

    ActivityDetail findActvityById(int id);

    List<ActivityVo> findByConditions(FindConditions<Activity> conditions);

    Integer countAll(FindConditions<Activity> conditions);

    Activity findLatestActivity(Integer teamId,Integer size);
}