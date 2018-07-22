package com.kk.apollo.biz.dao.team;

import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.team.Team;
import com.kk.apollo.biz.model.team.TeamVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamMapper {
    int deleteByPrimaryKey(Integer teamId);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Integer teamId);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);

    int getDepNum(int teamId);

    List<String> getDepName (int teamId);

    List<Team> findAll();

    List<TeamVo> findAllVo();

    List<TeamVo> findAllOrderByDesc(@Param(value="orderBy")String orderBy);

    List<TeamVo> findAllOrderByAsc(@Param(value="orderBy")String orderBy);

    List<TeamVo> findAllByKey(@Param(value="key")String key, @Param(value="value")String value);

    List<TeamVo> findByConditions(FindConditions<Team>  conditions);

    Integer countAll(FindConditions<Team> conditions);
}