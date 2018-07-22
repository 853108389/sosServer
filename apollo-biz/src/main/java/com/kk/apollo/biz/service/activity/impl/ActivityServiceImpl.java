package com.kk.apollo.biz.service.activity.impl;

import com.kk.apollo.biz.dao.activity.ActivityMapper;
import com.kk.apollo.biz.dao.team.TeamMapper;
import com.kk.apollo.biz.model.activity.Activity;
import com.kk.apollo.biz.model.activity.ActivityDetail;
import com.kk.apollo.biz.model.activity.ActivityVo;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.team.Team;
import com.kk.apollo.biz.model.team.TeamUser;
import com.kk.apollo.biz.model.team.TeamUserVo;
import com.kk.apollo.biz.model.user.Messages;
import com.kk.apollo.biz.service.activity.ActivityService;
import com.kk.apollo.biz.service.user.MessagesService;
import com.kk.apollo.biz.service.user.UserService;
import com.kk.apollo.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Activity findLatestActivity(Integer teamId, Integer size) {
        return activityMapper.findLatestActivity(teamId, size);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<ActivityVo> findActivityByTeamId(int teamId) {
        return activityMapper.findActivityByTeamId(teamId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<ActivityVo> findAllActivitys() {
        return activityMapper.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ActivityDetail findActvityById(int id) {
        return activityMapper.findActvityById(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<ActivityVo> findByConditions(FindConditions<Activity> conditions) {
        return activityMapper.findByConditions(conditions);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer countAll(FindConditions<Activity> conditions) {
        return activityMapper.countAll(conditions);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer insertActivity(Activity activity) {
        List<TeamUserVo> userByTeamId = userService.findUserByTeamId(activity.getTeamId());//所有用户id
        List<Messages> messagesList = new ArrayList<>();
        for(TeamUserVo user: userByTeamId){
            //添加消息通知 对所有改社团成员发送消息
            Messages messages = new Messages();
            if (messages.getMessagesDate() == null || messages.getMessagesDate() == "") {
                messages.setMessagesDate(TimeUtils.timeFormat("yyyy-MM-dd HH:mm:ss"));
            }
            if (StringUtils.isEmpty(messages.getMessagesNum())) {
                messages.setMessagesNum(1);
            }
            messages.setMessagesType(202);//社团发布活动
            Team team = teamMapper.selectByPrimaryKey(activity.getTeamId());//获取社团
            messages.setMessagesContent("[ " +team.getTeamName() +" ]" +"刚刚发布了[ " +activity.getActivityName() +" ]活动,快来看看吧!" );
            messages.setMessagesFromtype(0);//社团
            messages.setMessagesFromid(activity.getTeamId());
            messages.setMessagesTotype(1);//用户
            messages.setMessagesToid(user.getUserId());
            messagesList.add(messages);
        }
        Integer integer = messagesService.addBatchMs(messagesList);
        if(integer < 0){
            return -2;
        }
        return this.activityMapper.insertSelective(activity);
    }
}
