package com.kk.apollo.controller;

import com.kk.apollo.biz.model.auth.Auth;
import com.kk.apollo.biz.model.common.CommonVo;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.common.ModelWithMessageVo;
import com.kk.apollo.biz.model.feedback.Feedback;
import com.kk.apollo.biz.model.user.*;
import com.kk.apollo.biz.service.feedback.FeedBackService;
import com.kk.apollo.biz.service.team.TeamService;
import com.kk.apollo.biz.service.user.MessagesService;
import com.kk.apollo.biz.service.user.UserService;
import com.kk.apollo.utils.DataParser;
import com.kk.apollo.utils.MessageUtils;
import com.kk.apollo.utils.VoDataTypeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;
    @Autowired
    public MessagesService messagesService;
    @Autowired
    public TeamService teamService;
    @Autowired
    public FeedBackService feedBackService;

    /**
     * 根据用户的id查找用户
     *
     * @param map
     * @return
     */
    @RequestMapping("detail")
    public @ResponseBody
    User fetchUserById(@RequestBody Map<String, String> map) {
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        return userService.getUserById(Integer.parseInt(id));
    }


//    @RequestMapping("detailVoo")
//    public @ResponseBody
//    ModelWithMessageVo fetchUserByIdVo(@RequestBody Map<String, String> map) {
//        String id = "";
//        if (map.containsKey("id")) {
//            id = map.get("id");
//        }
//        return userService.fetchUserByIdVo(Integer.parseInt(id));
//    }

    /**
     * 确认密码
     *
     * @return
     */
    @RequestMapping("confirmPw")
    public @ResponseBody
    ModelWithMessageVo confirmPw(@RequestBody User user) {
        if (user.getUserId() == null || user.getUserPassword() == "") {
            return MessageUtils.fail("用戶id或者密码为null");
        }
        User user2 = userService.confirmPw(user);
        if (user2 != null) {
            return DataParser.setMessageVoProps(true,"成功",null,0);
        }else{
            return MessageUtils.fail("用户名密码不匹配");
        }
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping("editPw")
    public @ResponseBody
    ModelWithMessageVo editPw(@RequestBody Map<String, String> map) {
        String oldPw = "";
        String newPw = "";
        String userId = "";
        if (map.containsKey("oldPw") && map.containsKey("newPw") && map.containsKey("userId")) {
            oldPw = map.get("oldPw");
            newPw = map.get("newPw");
            userId = map.get("userId");
        }
        User user = new User();
        user.setUserId(Integer.parseInt(userId));
        user.setUserPassword(oldPw);
        User user3 = userService.confirmPw(user);
        if (user3 == null) {
            return MessageUtils.fail("用户名密码不匹配");
        }else {
            //设置新的密码
            User user2 = new User();
            user2.setUserId(Integer.parseInt(userId));
            user2.setUserPassword(newPw);
            Integer integer1 = userService.updateByPrimaryKeySelective(user2);
            return MessageUtils.resultTotalCheck(integer1);
        }
    }


    /**
     * 用户登入
     *
     * @param map
     * @return
     */
    @RequestMapping("login")
    public @ResponseBody
    ModelWithMessageVo login(@RequestBody Map<String, String> map) {
        String stuNo = "";
        String password = "";
        if (map.containsKey("stuNo")) {
            stuNo = map.get("stuNo");
        }
        if (map.containsKey("password")) {
            password = map.get("password");
        }
        ModelWithMessageVo modelWithMessageVo = userService.login(stuNo, password);
        return modelWithMessageVo;
    }

    /**
     * 用户登出
     *
     * @param map
     * @return
     */
    @RequestMapping("logout")
    public @ResponseBody
    User logout(@RequestBody Map<String, String> map) {
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        User user = userService.logout(Integer.parseInt(id));
        return user;
    }

    /**
     * 查询一条用户
     * TODO 权限 管理员应该可以查看所有用户  也就意味着,每次的请求都应该有当前用户的id ,来判断是否为管理员
     *
     * @param map
     * @return ModelWithMessageVo 包装 COMMONVO的形式
     */
    @RequestMapping("userVo")
    public @ResponseBody
    ModelWithMessageVo fetchUserByIdVo(@RequestBody Map<String, String> map) {
        String url = "userLable.properties";
        String id = "";
        String message = "成功";
        UserDetailVo userDetailVo = null;
        if (map.containsKey("id")) {
            id = map.get("id");
        }

        User user = userService.getUserById(Integer.parseInt(id));
        if (map.containsKey("type") && map.get("type").equals("0") && user.getUserIshidden().equals("1")) {
            userDetailVo = DataParser.parseUser2VoWithHidden(user);
            message = "该用户已经隐藏信息";//和app写死了 写的一样
        } else {
            userDetailVo = DataParser.parseUser2Vo(user);
        }
        List<CommonVo> commonVoList = new VoDataTypeParser<UserDetailVo>(url).toType(userDetailVo);
        return DataParser.setMessageVoProps(true, message, commonVoList, commonVoList.size());
    }

    /**
     * 查询 用户所在的所有社团
     *
     * @param map
     * @return ModelWithMessageVo包装对象形式
     */
    @RequestMapping("userTeams")
    public @ResponseBody
    ModelWithMessageVo fetchUserTeams(@RequestBody Map<String, String> map) {
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        List<UserTeamVo> userTeamVoList = userService.findTeamByUserId(Integer.parseInt(id));
        if (userTeamVoList == null && userTeamVoList.isEmpty()) {
            return DataParser.setMessageVoProps(true, "当前用户未加入社团", null, 0);
        }
        userTeamVoList = DataParser.parseUserTeamVo(userTeamVoList);
        return DataParser.setMessageVoProps(true, "成功", userTeamVoList, userTeamVoList.size());
    }

    /**
     * 添加消息
     *
     * @return
     */
    @RequestMapping("addMs")
    public @ResponseBody
    ModelWithMessageVo addMs(@RequestBody Messages messages) {
        if (messages.getMessagesFromid() == null && messages.getMessagesToid() == null) {
            return MessageUtils.fail("id不能为null");
        }
        Integer integer = messagesService.addMs(messages);
        String message = "";
        switch (integer) {
            case -1:
                message = "消息类型不能为空";
                break;
            case -2:
                message = "邀请已经发出,请耐心等候";
                break;
            case -3:
                message = "您已经在社团内";
                break;
            case -4:
                message = "沒有選擇部門名字";
                break;
        }
        return MessageUtils.resultTotalCheck(integer, "操作成功", message);
    }

    /**
     * 查询所有消息
     *
     * @param conditions
     * @return
     */
    @RequestMapping("allMs")
    public @ResponseBody
    ModelWithMessageVo fetchUserMessage(@RequestBody FindConditions<Auth> conditions) {
        if (conditions.getData() == null || conditions.getData().getUserId() == null) {
            return MessageUtils.fail("没有用户的id");
        }
        Integer index = conditions.getPageIndex();
        List<MessagesVo> messagesVoList = messagesService.findByConditions(conditions);
        Integer size = messagesService.countAll(conditions);
        messagesVoList = DataParser.parseMessageVoList(messagesVoList);
        return DataParser.setPageVoProps(true, "成功", messagesVoList, size, index);
    }

    /**
     * 刪除消息
     *
     * @return
     */
    @RequestMapping("delMs")
    public @ResponseBody
    ModelWithMessageVo fetchUserMessage(@RequestBody Messages messages) {
        Integer integer = messagesService.delMs(messages);
        return MessageUtils.resultTotalCheck(integer);
    }

    /**
     * 将消息设置为已读
     *
     * @return
     */
    @RequestMapping("readMs")
    public @ResponseBody
    ModelWithMessageVo readMs(@RequestBody Messages messages) {
        Integer total = messagesService.updateByPrimaryKeySelective(messages);
        if (total != 0) {
            return DataParser.setMessageVoProps(true, "成功", null, 1);
        } else {
            return DataParser.setMessageVoProps(false, "失败", null, 0);
        }
    }

    /**
     * 是否有新消息
     *
     * @return
     */
    @RequestMapping("hasNewMs")
    public @ResponseBody
    ModelWithMessageVo hasNewMs(@RequestBody Map<String, String> map) {
        String id = map.get("id");
        if (!map.containsKey("id") && id == null) {
            return DataParser.setMessageVoProps(false, "用户不存在", null, 0);
        }
        Integer total = messagesService.hasNewMs(Integer.parseInt(id));
        if (total != null && total != 0) {
            //TODO新消息数目
            return DataParser.setMessageVoProps(true, "有新消息", null, 1);
        } else {
            return DataParser.setMessageVoProps(true, "没有新消息", null, 0);
        }
    }

    /**
     * 设置用户隐藏个人信息
     *
     * @return
     */
    @RequestMapping("hideInfo")
    public @ResponseBody
    ModelWithMessageVo hideInfo(@RequestBody User user) {
        Integer total = userService.updateByPrimaryKeySelective(user);
        if (total != 0) {
            return DataParser.setMessageVoProps(true, "成功", null, 1);
        } else {
            return DataParser.setMessageVoProps(false, "失败", null, 0);
        }
    }

    /**
     * 根据学号查找
     */
    @RequestMapping("findByStuNo")
    public @ResponseBody
    ModelWithMessageVo fetchPage(@RequestBody Map<String, String> map) {
        String stuNo = "";
        if (map.containsKey("stuNo")) {
            stuNo = map.get("stuNo");
        }
        User user = userService.findUserByStuNo(stuNo);
        return DataParser.setMessageVoProps(true, "成功", user, 0);
    }

    /**
     * TODO 用户注册
     */
    @RequestMapping("signUp")
    public @ResponseBody
    ModelWithMessageVo addUser(@RequestBody Map<String, String> map) {
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        ModelWithMessageVo<?> ModelWithMessageVo = new ModelWithMessageVo<>();
        return ModelWithMessageVo;
    }

    /**
     * 更新用戶 1
     *
     * @param commonVoList
     * @return
     */
    @RequestMapping("update")
    public @ResponseBody
    ModelWithMessageVo updateUser(@RequestBody List<CommonVo> commonVoList) {
        try {
            User user = (User) new VoDataTypeParser<User>().vo2Obj(User.class.newInstance(), commonVoList);
            if (StringUtils.isEmpty(user.getUserId())) {
                return MessageUtils.fail();
            }
            Integer integer = userService.updateByPrimaryKeySelective(user);
            User retUser = userService.getUserById(user.getUserId());
            if (integer != null && integer > 0) {
                return DataParser.setMessageVoProps(true, "成功", retUser, 1);
            } else {
                return MessageUtils.fail();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return MessageUtils.fail();
    }

    /**
     * 更新用戶2
     *
     * @param user
     * @return
     */
    @RequestMapping("update2")
    public @ResponseBody
    ModelWithMessageVo updateUser2(@RequestBody User user) {
        if (user.getUserId() == null) {
            return MessageUtils.fail();
        } else {
            Integer integer = userService.updateByPrimaryKeySelective(DataParser.revparseUser(user));
            User retUser = userService.getUserById(user.getUserId());
            return DataParser.setMessageVoProps(true, "成功", retUser, 1);
        }
    }

    /**
     * 添加反馈
     *
     * @param feedback
     * @return
     */
    @RequestMapping("addFb")
    public @ResponseBody
    ModelWithMessageVo addFb(@RequestBody Feedback feedback) {
        if (feedback.getUserId() == null) {
            return MessageUtils.fail();
        }
        return MessageUtils.resultTotalCheck(feedBackService.insertSelective(feedback));
    }


}
