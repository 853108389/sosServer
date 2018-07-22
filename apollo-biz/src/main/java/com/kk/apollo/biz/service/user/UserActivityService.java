package com.kk.apollo.biz.service.user;

import com.kk.apollo.biz.model.user.UserActivity;

/**
 * Created by Administrator on 2018/1/24.
 */
public interface UserActivityService {
    /**
     * 根据用户及社团id查找
     * @param userId
     * @param activityId
     * @return
     */
    UserActivity selectByUserIdAndAcId(Integer userId, Integer activityId);

    /**
     * 查找所有点赞该活动的人数
     * @param activityId
     * @return
     */
    Integer countActivityLike(Integer activityId);

    /**
     * 点赞
     * @param userActivity
     * @return
     */
    Integer up(UserActivity userActivity);
}
