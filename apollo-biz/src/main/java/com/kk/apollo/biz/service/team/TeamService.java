package com.kk.apollo.biz.service.team;

import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.team.Team;
import com.kk.apollo.biz.model.team.TeamUser;
import com.kk.apollo.biz.model.team.TeamVo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
public interface TeamService {

    /**
     * 根据id查社团
     * @param id
     * @return
     */
    public Team getTeamById(int id);

    /**
     * 根据id查社团部门数
     * @param teamId
     * @return
     */
    public int depNum(int teamId);

    /**
     * 根据id查社团部门名称
     * @param teamId
     * @return
     */
    public List<String> getDepName(int teamId);

    /**
     * 查询所有社团
     * @return
     */
    List<Team> findAll();
    /**
     * 查询所有社团Vo
     * @return
     */
    List<TeamVo> findAllVo();

    List<TeamVo> findAllOrderBy(String orderBy, boolean desc);

    List<TeamVo> findAllByKey(String key,String value);

    List<TeamVo> findByConditions(FindConditions<Team> conditions);

    Integer countAll(FindConditions<Team> conditions);

    /**
     * 更新用户权限部门表 user_team
     * @param teamUser
     * @return
     */
    Integer updateTeamUserSelective(TeamUser teamUser);

    /**
     * 更新部门名称
     * @return
     */
    Integer updateDepName(String teamId,String olddepartmentName, String departmentName);

    /**
     * 添加部门等
     * @param teamUser
     * @return
     */
    Integer insertSelective(TeamUser teamUser);

    /**
     * 删除部门用户
     * @param teamUser
     * @return
     */
    Integer delete(TeamUser teamUser);

    /**
     * 更新Team
     * @param team
     * @return
     */
    Integer updateTeamSelective(Team team);

    /**
     * 根据id 查找社团中对应用户
     * @param teamId
     * @param userId
     * @return
     */
    public TeamUser findTeamUserById(Integer teamId,Integer userId);

    /**
     * 添加一个新的社团
     * @param team
     * @return
     */
    Integer addTeam(Team team, String userId,String reason);
    /**
     * 添加一个新的社团
     * 逻辑添加
     * @param userId
     * @return
     */
    Integer addTeam2(String userId);
}
