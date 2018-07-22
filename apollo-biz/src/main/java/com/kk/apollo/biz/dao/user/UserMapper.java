package com.kk.apollo.biz.dao.user;

import com.kk.apollo.biz.model.team.TeamUserVo;
import com.kk.apollo.biz.model.user.User;
import com.kk.apollo.biz.model.user.UserTeamVo;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByUnPw(String stuNo,String password);

    List<User> selectUsersByType(String userType);

    List<UserTeamVo> findTeamByUserId(int userId);

    public List<TeamUserVo> findUserByTeamId(int teamId);

    public List<TeamUserVo> findAllUserDepByTeamId(int teamId , String depName);

    User fetchUserByIdVo(int userId);

    Integer getLeaderId(int teamId);

    User findUserByStuNo(String stuNo);

    List<Map<String,String>> getUserTeamAndType (Integer userId);

    User selectByUnAndPw(User user);
}