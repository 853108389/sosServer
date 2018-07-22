package com.kk.apollo.biz.service.test.impl;

import com.kk.apollo.biz.dao.test.TestMapper;
import com.kk.apollo.biz.model.test.Test;
import com.kk.apollo.biz.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/11.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    public TestMapper testMapper;
    @Override
    public Test getTest(int id) {
        Test test = testMapper.selectByPrimaryKey(id);
        return test;
    }
}
