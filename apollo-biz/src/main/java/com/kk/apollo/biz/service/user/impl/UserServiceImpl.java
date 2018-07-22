package com.kk.apollo.biz.service.user.impl;

import com.kk.apollo.biz.dao.user.UserMapper;
import com.kk.apollo.biz.model.common.ModelWithMessageVo;
import com.kk.apollo.biz.model.team.TeamUserVo;
import com.kk.apollo.biz.model.user.User;
import com.kk.apollo.biz.model.user.UserTeamVo;
import com.kk.apollo.biz.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/10/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer getLeaderId(int teamId) {
        return userMapper.getLeaderId(teamId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ModelWithMessageVo<User> login(String stuNo, String password) {
        User user = userMapper.getUserByUnPw(stuNo, password);
        ModelWithMessageVo<User> modelWithMessageVo = new ModelWithMessageVo<>();
        if (user == null) {
            modelWithMessageVo.setSuccess(false);
            modelWithMessageVo.setMessage("登入失败,用户名或密码不正确");
            return modelWithMessageVo;
        }
        if (user.getUserAccesstoken() != null && user.getUserAccesstoken() != "") {
            //TODO 该用户已经登入过
            modelWithMessageVo.setSuccess(true);
            modelWithMessageVo.setData(user);
//            modelWithMessageVo.setMessage("注意:该用户已经登入过"); TODO:accessToken是否启用
            modelWithMessageVo.setMessage("登入成功,正在为您跳转...");
            return modelWithMessageVo;

        } else {
            user.setUserAccesstoken(UUID.randomUUID().toString().replaceAll("-", ""));
            modelWithMessageVo.setSuccess(true);
            modelWithMessageVo.setMessage("登入成功,正在为您跳转...");
            modelWithMessageVo.setData(user);
            userMapper.updateByPrimaryKey(user);
        }
        return modelWithMessageVo;
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User logout(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            if (user.getUserAccesstoken() != "") {
                user.setUserAccesstoken("");
                return user;
            } else {
                //TODO 还没登入过
            }
        } else {
            //TODO 用户不存在
        }
        return new User();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<UserTeamVo> findTeamByUserId(int userId) {
        return userMapper.findTeamByUserId(userId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<TeamUserVo> findUserByTeamId(int teamId) {
        return userMapper.findUserByTeamId(teamId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<TeamUserVo> findAllUserDepByTeamId(int teamId, String depName) {
        return userMapper.findAllUserDepByTeamId(teamId, depName);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User fetchUserByIdVo(int userId) {
        return this.userMapper.fetchUserByIdVo(userId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Integer updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User findUserByStuNo(String stuNo) {
        return userMapper.findUserByStuNo(stuNo);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public User confirmPw(User user) {
        return userMapper.selectByUnAndPw(user);
    }
}
