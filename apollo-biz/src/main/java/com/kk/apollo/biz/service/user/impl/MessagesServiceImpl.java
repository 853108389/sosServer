package com.kk.apollo.biz.service.user.impl;

import com.kk.apollo.biz.dao.user.MessagesMapper;
import com.kk.apollo.biz.model.auth.Auth;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.team.Team;
import com.kk.apollo.biz.model.team.TeamUser;
import com.kk.apollo.biz.model.user.Messages;
import com.kk.apollo.biz.model.user.MessagesVo;
import com.kk.apollo.biz.service.auth.AuthService;
import com.kk.apollo.biz.service.team.TeamService;
import com.kk.apollo.biz.service.user.MessagesService;
import com.kk.apollo.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sun.misc.MessageUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/17.
 */
@Service
public class MessagesServiceImpl implements MessagesService {
    @Autowired
    private MessagesMapper messagesMapper;
    @Autowired
    private TeamService teamService;
    @Autowired
    private AuthService authService;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<MessagesVo> findByConditions(FindConditions<Auth> conditions) {
        Auth userInfo = authService.getUserInfo(conditions.getData().getUserId());
        conditions.setData(userInfo);
        return messagesMapper.findByConditions(conditions);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer countAll(FindConditions<Auth> conditions) {
        return messagesMapper.countAll(conditions);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer updateByPrimaryKeySelective(Messages messages) {
        return messagesMapper.updateByPrimaryKeySelective(messages);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer hasNewMs(Integer id) {
        Auth userInfo = authService.getUserInfo(id);
        return messagesMapper.hasNewMs(userInfo);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer insertSelective(Messages messages) {
        return messagesMapper.insertSelective(messages);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Messages findById(Integer messagesId) {
        return messagesMapper.selectByPrimaryKey(messagesId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer deleteById(Integer messagesId) {
        return messagesMapper.deleteByPrimaryKey(messagesId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer delMs(Messages messages) {
        if (StringUtils.isEmpty(messages.getMessagesId())) {
            return -1;
        } else {
            Messages messages1 = this.findById(messages.getMessagesId());
            if(messages1==null){
                return 1;
            }
            switch (messages1.getMessagesType()) {
                //根据消息类型决定是逻辑删除还是物理删除 TODO 没写逻辑删除  这里有问题 一概物理删除
                case 100:
                    TeamUser teamUser = new TeamUser();
                    teamUser.setUserId(messages1.getMessagesToid());
                    teamUser.setTeamId(messages1.getMessagesFromid());
                    TeamUser teamUserById = teamService.findTeamUserById(messages1.getMessagesFromid(), messages1.getMessagesToid());
                    if (teamUserById != null && teamUserById.getUserTeamType().equals("-1")) {
                        Integer delete = teamService.delete(teamUser);
                        if (delete == null && delete <= 0) {
                            return delete;
                        }
                    }
                    break;
                case 200:
                    //TODO:
                    TeamUser teamUser2 = new TeamUser();
                    teamUser2.setUserId(messages1.getMessagesFromid());
                    teamUser2.setTeamId(messages1.getMessagesToid());
                    TeamUser teamUserById2 = teamService.findTeamUserById(messages1.getMessagesToid(),messages1.getMessagesFromid());
                    if (teamUserById2 != null && teamUserById2.getUserTeamType().equals("-1")) {
                        Integer delete = teamService.delete(teamUser2);
                        if (delete == null && delete <= 0) {
                            return delete;
                        }
                    }
                    break;
                default:
                    break;
            }
            Integer integer = this.deleteById(messages.getMessagesId());
            return integer;
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer addMs(Messages messages) {
        if (messages.getMessagesType() == null) {
            return -1;
        }
        if (messages.getMessagesDate() == null || messages.getMessagesDate() == "") {
            messages.setMessagesDate(TimeUtils.timeFormat("yyyy-MM-dd HH:mm:ss"));
        }
        if (StringUtils.isEmpty(messages.getMessagesNum())) {
            messages.setMessagesNum(1);
        }
        switch (messages.getMessagesType()) {
            case 100: //100用户被邀请加入社团
                Integer teamId = messages.getMessagesFromid();
                Integer userId=messages.getMessagesToid();
                Map<String, String> messagesParams = messages.getMessagesParams();
                TeamUser findUser = teamService.findTeamUserById(teamId, userId);
                if (findUser != null) {
                    if (findUser.getUserTeamType().equals("-1")) {
                        return -2;
                    } else {
                        return -3;
                    }
                }
                if (messagesParams.containsKey("depName")) {
                    TeamUser teamUser = new TeamUser();
                    teamUser.setUserTeamType("-1");
                    teamUser.setDepartmentName(messagesParams.get("depName"));
                    teamUser.setTeamId(teamId);
                    teamUser.setUserId(userId);
                    teamService.insertSelective(teamUser);
                } else {
                    return -4;
                }
                Team team = teamService.getTeamById(teamId);
                if (StringUtils.isEmpty(messages.getMessagesContent())) {
                    messages.setMessagesContent("快來加入" + team.getTeamName() + "吧");
                }else{
                    messages.setMessagesContent(messages.getMessagesContent());
                }
                break;
            case 101: //用户退出社团
                break;
            case 200: //社团批准用户加入
                Integer teamId2 = messages.getMessagesToid();
                Integer userId2 = messages.getMessagesFromid();
                Map<String, String> messagesParams2 = messages.getMessagesParams();
                TeamUser findUser2 = teamService.findTeamUserById(teamId2,userId2);
                if (findUser2 != null) {
                    if (findUser2.getUserTeamType().equals("-1")) {
                        return -2;
                    } else {
                        return -3;
                    }
                }
                if (messagesParams2.containsKey("depName")) {
                    TeamUser teamUser = new TeamUser();
                    teamUser.setUserTeamType("-1");
                    teamUser.setDepartmentName(messagesParams2.get("depName"));
                    teamUser.setTeamId(teamId2);
                    teamUser.setUserId(userId2);
                    teamService.insertSelective(teamUser);
                } else {
                    return -4;
                }
                Team team2 = teamService.getTeamById(teamId2);
                if (StringUtils.isEmpty(messages.getMessagesContent())) {
                    messages.setMessagesContent("快來加入" + team2.getTeamName() + "吧");
                }else{
                    messages.setMessagesContent(messages.getMessagesContent());
                }
                break;
            case 201://社团踢出用户
                break;
            default:
                break;
        }
        Integer integer = this.insertSelective(messages);
        return integer;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer addBatchMs(List<Messages> messagesList){
        return messagesMapper.addBatchMs(messagesList);
    }
}
