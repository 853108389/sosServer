package com.kk.apollo.controller;

import com.kk.apollo.biz.model.test.Test;
import com.kk.apollo.biz.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    public TestService testService;


    @RequestMapping("test")
    public String toTest() {
        System.out.println("aaa");
        return "test";
    }

    @RequestMapping("testJson")
    public @ResponseBody
    List<Test> fetchPassDetail() {
        Test test1 = testService.getTest(1);
        Test test2 = testService.getTest(2);
        ArrayList arrayList = new ArrayList<Test>();
        arrayList.add(test1);
        arrayList.add(test2);
        return arrayList;
    }
}

//http://localhost:8080/sos/test/test.do