package com.kk.apollo.biz.service.activity;

import com.kk.apollo.biz.model.activity.Activity;
import com.kk.apollo.biz.model.activity.ActivityDetail;
import com.kk.apollo.biz.model.activity.ActivityVo;
import com.kk.apollo.biz.model.common.FindConditions;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public interface ActivityService {
    /**
     * 根据社团id查询最新的几条活动
     * @param teamId
     * @return
     */
    Activity findLatestActivity(Integer teamId,Integer size);
    /**
     * 根据社团id查询所有活动
     * @param teamId
     * @return
     */
    List<ActivityVo> findActivityByTeamId(int teamId);
    /**
     * 查询所有活动
     * @return
     */
    List<ActivityVo> findAllActivitys();

    /**
     * 根据活动Id查询某个活动
     * @param id
     * @return
     */
    ActivityDetail findActvityById(int id);

    /**
     * 条件查询
     * @param conditions
     * @return
     */
    List<ActivityVo> findByConditions(FindConditions<Activity> conditions);

    /**
     * 条件查询获得数量
     * @param conditions
     * @return
     */
    Integer countAll(FindConditions<Activity> conditions);

    /**
     * 添加活动
     * @param activity
     * @return
     */
    Integer insertActivity(Activity activity);
}
