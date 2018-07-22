package com.kk.apollo.biz.service.user;

import com.kk.apollo.biz.model.common.ModelWithMessageVo;
import com.kk.apollo.biz.model.team.TeamUserVo;
import com.kk.apollo.biz.model.user.User;
import com.kk.apollo.biz.model.user.UserTeamVo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
public interface UserService {
    public Integer getLeaderId(int teamId);

    public User getUserById(int id);

    public ModelWithMessageVo login(String stuNo , String password);

    public User logout(int id);

    public List<UserTeamVo> findTeamByUserId(int userId);

    public List<TeamUserVo> findUserByTeamId(int teamId);

    public List<TeamUserVo> findAllUserDepByTeamId(int teamId, String depName);

    User fetchUserByIdVo(int userId);

    Integer updateByPrimaryKeySelective(User user);

    User findUserByStuNo(String stuNo);

    User confirmPw(User user);
}
