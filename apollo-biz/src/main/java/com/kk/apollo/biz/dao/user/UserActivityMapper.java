package com.kk.apollo.biz.dao.user;

import com.kk.apollo.biz.model.user.UserActivity;

public interface UserActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserActivity record);

    int insertSelective(UserActivity record);

    UserActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserActivity record);

    int updateByPrimaryKey(UserActivity record);

    /**
     * 根据用户及社团id查找
     * @param userId
     * @param activityId
     * @return
     */
    UserActivity selectByUserIdAndAcId(Integer userId,Integer activityId);

    /**
     * 查找所有点赞该活动的人数
     * @param activityId
     * @return
     */
    Integer countActivityLike(Integer activityId);
}