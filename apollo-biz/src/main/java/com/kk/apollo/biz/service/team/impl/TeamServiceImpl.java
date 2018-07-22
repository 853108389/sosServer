package com.kk.apollo.biz.service.team.impl;

import com.kk.apollo.biz.dao.team.TeamMapper;
import com.kk.apollo.biz.dao.team.TeamUserMapper;
import com.kk.apollo.biz.dao.user.UserMapper;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.team.Team;
import com.kk.apollo.biz.model.team.TeamUser;
import com.kk.apollo.biz.model.team.TeamVo;
import com.kk.apollo.biz.model.user.Messages;
import com.kk.apollo.biz.model.user.User;
import com.kk.apollo.biz.service.team.TeamService;
import com.kk.apollo.biz.service.user.MessagesService;
import com.kk.apollo.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    public TeamMapper teamMapper;

    @Autowired
    public TeamUserMapper teamUserMapper;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public MessagesService messagesService;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Team getTeamById(int id) {
        return teamMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int depNum(int teamId) {
        return teamMapper.getDepNum(teamId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<String> getDepName(int teamId) {
        return teamMapper.getDepName(teamId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<Team> findAll() {
        return teamMapper.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<TeamVo> findAllVo() {
        return teamMapper.findAllVo();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<TeamVo> findAllOrderBy(String orderBy, boolean desc) {
        if (desc) {
            return teamMapper.findAllOrderByDesc(orderBy);
        } else {
            return teamMapper.findAllOrderByAsc(orderBy);
        }

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<TeamVo> findAllByKey(String key, String value) {
        return teamMapper.findAllByKey(key, value);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<TeamVo> findByConditions(FindConditions<Team> conditions) {
        return teamMapper.findByConditions(conditions);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer countAll(FindConditions<Team> conditions) {
        return teamMapper.countAll(conditions);
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer updateTeamUserSelective(TeamUser teamUser) {
        //更新权限
        if (teamUser.getUserTeamType() != null && teamUser.getDepartmentName() != null) {
            return this.updateTeamUserSelectiveType(teamUser);
        } else {
            return teamUserMapper.updateByTeamIdAndUserIdSelective(teamUser);
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer updateDepName(String teamId, String olddepartmentName, String departmentName) {
        return teamUserMapper.updateDepName(teamId, olddepartmentName, departmentName);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer insertSelective(TeamUser teamUser) {
        return teamUserMapper.insertSelective(teamUser);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer delete(TeamUser teamUser) {
        if (teamUser.getMessagesType() != null) {
            String content = (teamUser.getMessagesContent() == null ? "" : teamUser.getMessagesContent());
            Messages messages = new Messages();
            messages.setMessagesType(teamUser.getMessagesType());
            messages.setMessagesContent(content);
            switch (teamUser.getMessagesType()) {
                case 101: //用户退出社团
                    messages.setMessagesFromid(teamUser.getUserId()); //来自用户
                    messages.setMessagesFromtype(1);
                    messages.setMessagesToid(teamUser.getTeamId()); //发送给社团
                    messages.setMessagesTotype(0);
                    break;
                case 201://社团踢出用户
                    messages.setMessagesFromid(teamUser.getTeamId()); //来自社团
                    messages.setMessagesFromtype(0);
                    messages.setMessagesToid(teamUser.getUserId()); //发送给用户
                    messages.setMessagesTotype(1);
                    break;
                default:
                    break;
            }
            Integer integer = messagesService.addMs(messages);
            if (integer <= 0) {
                return integer;
            }
        }
        TeamUser tempUser = teamUserMapper.findUserByTeamIdUserId(teamUser.getTeamId(), teamUser.getUserId());
        Integer id = tempUser.getDepartmentId();
        if (id != null) {
            return teamUserMapper.deleteByPrimaryKey(id);
        } else {
            return -1;
        }
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer updateTeamSelective(Team team) {
        return teamMapper.updateByPrimaryKeySelective(team);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public TeamUser findTeamUserById(Integer teamId, Integer userId) {
        return teamUserMapper.findUserByTeamIdUserId(teamId, userId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer addTeam(Team team, String userId, String reason) {
        Integer integer1 = teamUserMapper.isTeamApply(Integer.parseInt(userId));  //判断是否正在申请,如果正在申请就return
        if (integer1 != null && integer1 > 0) {
            return -2;//已经正在申请了
        }
        Integer integer = teamMapper.insertSelective(team);           //  插入社团
        if (integer <= 0) {
            return integer;//插入社团失败
        }
        TeamUser teamUser = new TeamUser();
        teamUser.setTeamId(team.getTeamId());
        teamUser.setUserId(Integer.parseInt(userId));
        teamUser.setUserTeamType("-1"); //-1占位置  最后要改成1
        teamUser.setDepartmentName("主席团");
        Integer integer2 = teamUserMapper.insertSelective(teamUser);  //插入用户社团表
        if (integer2 <= 0) {
            return integer;//插入用户与社团关系失败
        }
        List<Messages> messagesList = new ArrayList<>();
        //TODO 查询管理员  并发送给管理员
        List<User> users = userMapper.selectUsersByType("0");
        if (users == null || users.isEmpty()) {
            return -1;//没有管理员
        }
        for (User user : users) {
            Messages messages = new Messages();//插入消息
            if (messages.getMessagesDate() == null || messages.getMessagesDate() == "") {
                messages.setMessagesDate(TimeUtils.timeFormat("yyyy-MM-dd HH:mm:ss"));
            }
            if (StringUtils.isEmpty(messages.getMessagesNum())) {
                messages.setMessagesNum(1);
            }
            messages.setMessagesType(102);//用户申请社团
            messages.setMessagesFromid(Integer.parseInt(userId));
            messages.setMessagesToid(user.getUserId());
            messages.setMessagesFromtype(1);//用户
            messages.setMessagesTotype(1);//用户
            messages.setMessagesContent("创团申请:" + reason);
            messagesList.add(messages);
        }
        Integer integer3 = messagesService.addBatchMs(messagesList);
        if (integer3 <= 0) {
            return integer3; //插入消息失败
        }
        return 1;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer addTeam2(String userId) {
        TeamUser userApply = teamUserMapper.findUserApply(Integer.parseInt(userId));
        if (userApply == null || userApply.getTeamId() == null) {
            return -2;
        }
        Team team = teamMapper.selectByPrimaryKey(userApply.getTeamId());
        team.setTeamStatus("1");//TODO 我也不知道这个属性改设置成多少
        Integer integer = teamMapper.updateByPrimaryKeySelective(team);
        userApply.setUserTeamType("1");
        Integer integer2 = teamUserMapper.updateByPrimaryKeySelective(userApply);
        if (integer < 0 || integer2 < 0) {
            return -1;
        }
        return 1;
    }


    //更新权限
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer updateTeamUserSelectiveType(TeamUser teamUser) {
        String type = teamUser.getUserTeamType();
        Integer id = 0;
        switch (type) {
            case "1":
                try {
                    //1.去掉原有主席,
                    Integer leaderId = userMapper.getLeaderId(teamUser.getTeamId());
                    TeamUser leader = teamUserMapper.findUserByTeamIdUserId(teamUser.getTeamId(), leaderId);
                    leader.setUserTeamType("2");
                    Integer result = teamUserMapper.updateByTeamIdAndUserIdSelective(leader);
                    if (result <= 0) {
                        //回滚事务
                        throw new RuntimeException();
                    }
                    //2.更新新的主席
                    id = teamUserMapper.updateByTeamIdAndUserIdSelective(teamUser);
                    if (id <= 0) {
                        //回滚事务
                        throw new RuntimeException();
                    }
                } catch (Exception e) {
                    return -1;
                }
                break;
            case "2":
                //副主席
                id = teamUserMapper.updateByTeamIdAndUserIdSelective(teamUser);
                if (id <= 0) {
                    //回滚事务
                    throw new RuntimeException();
                }
                break;
            case "3":
                try {
                    //1.去掉原有部长,
                    List<TeamUser> teamUserList = teamUserMapper.findUserByType(teamUser.getTeamId(), teamUser.getDepartmentName(), "3");
                    if (teamUserList == null && teamUserList.isEmpty()) {
                        //没有原来的部长  应该不会出现这种情况
                    } else {
                        TeamUser smallLeader = teamUserList.get(0);
                        smallLeader.setUserTeamType("5");
                        teamUserMapper.updateByTeamIdAndUserIdSelective(smallLeader);
                    }
                    //2.更新新的部长
                    id = teamUserMapper.updateByTeamIdAndUserIdSelective(teamUser);
                    if (id <= 0) {
                        //回滚事务
                        throw new RuntimeException();
                    }
                } catch (Exception e) {
                    return -1;
                }
                break;
            case "4":
                //副部长
                id = teamUserMapper.updateByTeamIdAndUserIdSelective(teamUser);
                if (id <= 0) {
                    //回滚事务
                    throw new RuntimeException();
                }
                break;
            case "5":
                //部员
                id = teamUserMapper.updateByTeamIdAndUserIdSelective(teamUser);
                if (id <= 0) {
                    //回滚事务
                    throw new RuntimeException();
                }
                break;
            default:
                break;
        }
        return id;
    }


}
