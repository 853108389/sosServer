package com.kk.apollo.biz.service.feedback;

import com.kk.apollo.biz.model.feedback.Feedback;

/**
 * Created by Administrator on 2018/1/3.
 */
public interface FeedBackService {
    int deleteByPrimaryKey(Integer feedbackId);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer feedbackId);

    int updateByPrimaryKeySelective(Feedback record);
}
