package com.kk.apollo.biz.service.feedback.impl;

import com.kk.apollo.biz.dao.feedback.FeedbackMapper;
import com.kk.apollo.biz.model.feedback.Feedback;
import com.kk.apollo.biz.service.feedback.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018/1/3.
 */
@Service
public class FeedBackServiceImpl  implements FeedBackService{
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteByPrimaryKey(Integer feedbackId) {
        return feedbackMapper.deleteByPrimaryKey(feedbackId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int insertSelective(Feedback record) {
        return feedbackMapper.insertSelective(record);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Feedback selectByPrimaryKey(Integer feedbackId) {
        return feedbackMapper.selectByPrimaryKey(feedbackId);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateByPrimaryKeySelective(Feedback record) {
        return feedbackMapper.updateByPrimaryKeySelective(record);
    }

}
