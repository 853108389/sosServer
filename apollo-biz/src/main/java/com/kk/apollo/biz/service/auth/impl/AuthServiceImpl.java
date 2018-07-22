package com.kk.apollo.biz.service.auth.impl;

import com.kk.apollo.biz.dao.user.UserMapper;
import com.kk.apollo.biz.model.auth.Auth;
import com.kk.apollo.biz.model.user.User;
import com.kk.apollo.biz.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2018/1/3.
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserMapper userMapper;
    @Override

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Auth  getUserInfo(Integer userId) {
        Auth auth = new Auth();
        auth.setUserId(userId);
        List<Map<String, String>> userTeamAndTypeList = userMapper.getUserTeamAndType(userId);
        Map<String,String> temp = new HashMap<>();
        Map<String,List<String>> temp2 = new HashMap<>();
        List<String> tempList1 = new ArrayList<>();
        List<String> tempList2 = new ArrayList<>();
        List<String> tempList3 = new ArrayList<>();
        List<String> tempList4 = new ArrayList<>();
        List<String> tempList5 = new ArrayList<>();
        for(Map<String,String> map : userTeamAndTypeList){
            String teamId=null;
            if(map.containsKey("teamId")){
                teamId = map.get("teamId");
            }
            String userTeamType = map.get("userTeamType");
            temp.put(teamId,userTeamType);
            switch (userTeamType){// 1主席 2副主席 3部长 4副部长 5部员
                case "1" :
                    tempList1.add(teamId);
                    break;
                case "2" :
                    tempList2.add(teamId);
                    break;
                case "3":
                    tempList3.add(teamId);
                    break;
                case "4" :
                    tempList4.add(teamId);
                    break;
                case "5" :
                    tempList5.add(teamId);
                    break;
            }
        }
        temp2.put("t1",tempList1);
        temp2.put("t2",tempList2);
        temp2.put("t3",tempList3);
        temp2.put("t4",tempList4);
        temp2.put("t5",tempList5);
        //设置两个map
        auth.setTeamTypeMap(temp);
        auth.setRevteamTypeMap(temp2);
        //设置用户类型
        User user = userMapper.selectByPrimaryKey(userId);
        if(user ==null){
            return null;
        }
        auth.setUserType(user.getUserType());
        return auth;
    }
}
