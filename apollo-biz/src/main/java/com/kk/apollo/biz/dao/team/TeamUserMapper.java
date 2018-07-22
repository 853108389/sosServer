package com.kk.apollo.biz.dao.team;

import com.kk.apollo.biz.model.team.TeamUser;

import java.util.List;

/**
 * Created by Administrator on 2017/12/23.
 */
public interface TeamUserMapper {

    int deleteByPrimaryKey(Integer departmentId);

    int insert(TeamUser record);

    int insertSelective(TeamUser record);

    TeamUser selectByPrimaryKey(Integer departmentId);

    int updateByPrimaryKeySelective(TeamUser record);

    int updateByPrimaryKey(TeamUser record);


    /**
     * 查询某一社团某一部门某一职务上的人
     *
     * @param teamId
     * @param depName
     * @param type
     * @return
     */
    List<TeamUser> findUserByType(Integer teamId, String depName, String type);

    /**
     * 根据社团id和用户id确定一个人在社团内信息
     * @param teamId
     * @param userId
     * @return
     */
    TeamUser findUserByTeamIdUserId(Integer teamId, Integer userId);

    /**
     * 根据社团id 和用户id
     *
     * @param record
     * @return
     */
    Integer updateByTeamIdAndUserIdSelective(TeamUser record);

    Integer updateDepName(String teamId, String olddepartmentName, String departmentName);

    /**
     * 判断用户是否正在申请社团
     * @return
     */
    Integer isTeamApply(Integer userId);

    /**
     *查找正在申请社团的用户
     */
    TeamUser findUserApply(Integer userId);
}
