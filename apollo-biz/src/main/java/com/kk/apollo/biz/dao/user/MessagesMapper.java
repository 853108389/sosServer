package com.kk.apollo.biz.dao.user;

import com.kk.apollo.biz.model.auth.Auth;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.user.Messages;
import com.kk.apollo.biz.model.user.MessagesVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessagesMapper {
    int deleteByPrimaryKey(Integer messagesId);

    int insert(Messages record);

    int insertSelective(Messages record);

    Messages selectByPrimaryKey(Integer messagesId);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKey(Messages record);

    List<MessagesVo> findByConditions(FindConditions<Auth> conditions);

    Integer countAll(FindConditions<Auth> conditions);

    Integer hasNewMs(Auth auth);

    Integer addBatchMs(@Param("list")List<Messages> messagesList);
}