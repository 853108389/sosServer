//package com.kk.apollo.controller;
//
//import com.kk.apollo.biz.dao.WebDao;
//import com.kk.apollo.biz.model.common.ModelWithMessageVo;
//import com.kk.apollo.biz.model.user.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2018/5/1.
// */
//@Controller
//public class WebController {
//    @Autowired
//    private WebDao webDao;
//    @RequestMapping("/web/users")
//    public @ResponseBody
//    List<User> findUsers(Model model) {
//        List<User> users = webDao.findUsers();
//        model.addAttribute("users",users);
//        return users;
//    }
//}
