package com.kk.apollo.biz.service.user.impl;

import com.kk.apollo.biz.dao.user.UserActivityMapper;
import com.kk.apollo.biz.model.user.UserActivity;
import com.kk.apollo.biz.service.user.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/1/24.
 */
@Service
public class UserActivityServiceImpl implements UserActivityService{
    @Autowired
    private UserActivityMapper userActivityMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public UserActivity selectByUserIdAndAcId(Integer userId, Integer activityId) {
        return userActivityMapper.selectByUserIdAndAcId(userId,activityId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer countActivityLike(Integer activityId) {
        return userActivityMapper.countActivityLike(activityId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer up(UserActivity userActivity) {
        UserActivity userActivity1 = userActivityMapper.selectByUserIdAndAcId(userActivity.getUserId(), userActivity.getActivityId());
        Integer integer = null;
        if(userActivity1==null){
            //插入
            integer = userActivityMapper.insertSelective(userActivity);
        }else{
            //存在
            userActivity.setId(userActivity1.getId());
            integer = userActivityMapper.updateByPrimaryKeySelective(userActivity);
        }
        return integer;
    }
}
