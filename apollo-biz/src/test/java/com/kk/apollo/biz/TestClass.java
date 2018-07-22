package com.kk.apollo.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/spring/local/spring-mybatis.xml"})
public class TestClass extends AbstractJUnit4SpringContextTests{


//    @Resource(name = "TicketMapper")
//    public void setUserDao(TicketMapper ticketMapper) {
//        this.userDao = userDao;
//    }

    @Test
    public void testDoInsert() throws Exception {




    }
}