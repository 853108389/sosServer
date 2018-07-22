package com.kk.apollo.biz.service.user;

import com.kk.apollo.biz.model.auth.Auth;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.user.Messages;
import com.kk.apollo.biz.model.user.MessagesVo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/17.
 */
public interface MessagesService {

    /**
     * 条件查询
     * @param conditions
     * @return
     */
    List<MessagesVo> findByConditions(FindConditions<Auth> conditions);

    /**
     * 条件查询获得数量
     * @param conditions
     * @return
     */
    Integer countAll(FindConditions<Auth> conditions);

    Integer updateByPrimaryKeySelective(Messages messages);

    Integer hasNewMs(Integer id);

    /**
     * 添加一条消息
     * @param messages
     * @return
     */
    Integer insertSelective(Messages messages);

    /**
     * 主鍵查詢消息
     * @return
     */
    Messages findById(Integer messagesId);
    /**
     * 主键删除消息
     */
    Integer deleteById(Integer messagesId);

    /**
     * 删除消息逻辑整合
     */
    Integer delMs(Messages messages);

    /**
     * 添加消息逻辑整合
     * @param messages
     * @return
     */
    Integer addMs(Messages messages);

    /**
     * 批量增加消息
     * @param messagesList
     * @return
     */
    Integer addBatchMs(List<Messages> messagesList);


}
