package com.kk.apollo.controller;

import com.kk.apollo.biz.model.activity.ActivityVo;
import com.kk.apollo.biz.model.common.*;
import com.kk.apollo.biz.model.team.*;
import com.kk.apollo.biz.service.activity.ActivityService;
import com.kk.apollo.biz.service.team.TeamService;
import com.kk.apollo.biz.service.user.UserService;
import com.kk.apollo.utils.DataParser;
import com.kk.apollo.utils.MessageUtils;
import com.kk.apollo.utils.ObjectDataTypeParser;
import com.kk.apollo.utils.VoDataTypeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/11.
 */
@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    public TeamService teamService;
    @Autowired
    public UserService userService;
    @Autowired
    public ActivityService activityService;

    /**
     * 根据社团的id查找社团
     *
     * @param map
     * @return
     */
    @RequestMapping("detail")
    public @ResponseBody
    ModelWithMessageVo fetchTeamById(@RequestBody Map<String, String> map) {
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        Team teamById = teamService.getTeamById(Integer.parseInt(id));
        return DataParser.setMessageVoProps(true, "成功", teamById, 1);
    }

    /**
     * 根据id查询社团详细
     *
     * @param map
     * @return 用COMMONVO的形式
     */
    @RequestMapping("detailVo")
    public @ResponseBody
    ModelWithMessageVo fetchTeamByIdVo(@RequestBody Map<String, String> map) {
        String url = "teamLable.properties";
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        Team team = teamService.getTeamById(Integer.parseInt(id));
        team = DataParser.parserTeam(team);
        List<CommonVo> commonVoList = new VoDataTypeParser<Team>(url).toType(team);
        TeamDetailVo teamDetailVo = DataParser.parserTeamDetailVo(commonVoList);
        teamDetailVo.setTeamId(Integer.parseInt(id));
        if (map.containsKey("userId") && map.get("userId") != "" && map.get("userId") != null) {
            //查询权限
            TeamUser teamUser = teamService.findTeamUserById(Integer.parseInt(id), Integer.parseInt(map.get("userId")));
            if (teamUser.getUserTeamType() != null && teamUser.getUserTeamType() != "") {
                teamDetailVo.setUserType(teamUser.getUserTeamType());
//                if(Integer.parseInt(teamUser.getUserTeamType()) < 3){
//                    // 1主席 2副主席 3部长 4副部长 5部员
//                }
            } else {
                //不属于这个部门
                teamDetailVo.setUserType("-1");
            }
        }
        ModelWithMessageVo modelWithMessageVo = DataParser.setMessageVoProps(true, "成功", teamDetailVo, 1);
        return modelWithMessageVo;
    }


    /**
     * 根据用戶id 社团id查询用户权限
     *
     * @param map
     * @return 用COMMONVO的形式
     */
    @RequestMapping("teamUserType")
    public @ResponseBody
    ModelWithMessageVo fetchTeamUserType(@RequestBody Map<String, String> map) {
        String id = "";
        TeamUser teamUser = null;
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        if (map.containsKey("userId") && map.get("userId") != "" && map.get("userId") != null) {
            //查询权限
            teamUser = teamService.findTeamUserById(Integer.parseInt(id), Integer.parseInt(map.get("userId")));
        }
        ModelWithMessageVo modelWithMessageVo = DataParser.setMessageVoProps(true, "成功", teamUser, 1);
        return modelWithMessageVo;
    }

    /**
     * 根据id更新社团信息
     *
     * @return
     */
    @RequestMapping("update")
    public @ResponseBody
    ModelWithMessageVo fetchTeamByIdVo(@RequestBody TeamDetailVo teamDetailVo) {
        Team team = ObjectDataTypeParser.parserteamDetailVo2Team(teamDetailVo);
        Integer integer = teamService.updateTeamSelective(team);
        if (integer >= 0) {
            return DataParser.setMessageVoProps(true, "成功", null, 0);
        } else {
            return DataParser.setMessageVoProps(false, "失败", null, 0);
        }
    }


    /**
     * 根据id更新社团信息
     *
     * @return
     */
    @RequestMapping("update2")
    public @ResponseBody
    ModelWithMessageVo update2(@RequestBody Team team) {
        Integer integer = teamService.updateTeamSelective(team);
        if (integer >= 0) {
            return DataParser.setMessageVoProps(true, "成功", null, 0);
        } else {
            return DataParser.setMessageVoProps(false, "失败", null, 0);
        }
    }

    /**
     * 查询社团所有的部门及其每个部门对应的用户
     *
     * @param map
     * @return TeamUserWithMessageVo包装对象形式
     */
    @RequestMapping("teamUsers")
    public @ResponseBody
    AllUserDepVo fetchTeamUsers(@RequestBody Map<String, String> map) {
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        List<String> depName = teamService.getDepName(Integer.parseInt(id));
        AllUserDepVo allUserDepVo = new AllUserDepVo();
        List<Deparment> deparmentList = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < depName.size(); i++) {
            List<TeamUserVo> teamUserVos = userService.findAllUserDepByTeamId(Integer.parseInt(id), depName.get(i));
            Deparment deparment = new Deparment();
            if (teamUserVos == null || teamUserVos.isEmpty()) {
                deparment.setDeparmentNum(0);
                deparment.setDeparmentUser(null);
            } else {
                teamUserVos = DataParser.parseTeamUsersVoList(teamUserVos);
                total += teamUserVos.size();
                deparment.setDeparmentNum(teamUserVos.size());
                deparment.setDeparmentUser(teamUserVos);
            }
            deparment.setDeparmentName(depName.get(i));
            deparmentList.add(deparment);
        }
        List<CommonListHeaderVo<Deparment>> commonListHeaderVos = DataParser.parserDepList2HeaderList(deparmentList);
        allUserDepVo.setData(commonListHeaderVos);
        allUserDepVo.setDepNum(deparmentList.size());
        allUserDepVo.setMessage("成功");
        allUserDepVo.setSuccess(true);
        allUserDepVo.setTotal(total);
        return allUserDepVo;
    }


    /**
     * 查询社团某个部门所有用户 TODO 没测试这个方法
     *
     * @param map
     * @return TeamUserWithMessageVo包装对象形式
     */
    @RequestMapping("teamDepUsers")
    public @ResponseBody
    AllUserDepVo fetchTeamDepUsers(@RequestBody Map<String, String> map) {
        String id = "";
        String depName = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        if (map.containsKey("depName")) {
            depName = map.get("depName");
        }
        AllUserDepVo allUserDepVo = new AllUserDepVo();
        List<Deparment> deparmentList = new ArrayList<>();
        int total = 0;
        List<TeamUserVo> teamUserVos = userService.findAllUserDepByTeamId(Integer.parseInt(id), depName);
        teamUserVos = DataParser.parseTeamUsersVoList(teamUserVos);
        total += teamUserVos.size();
        Deparment deparment = new Deparment();
        deparment.setDeparmentName(depName);
        deparment.setDeparmentNum(teamUserVos.size());
        deparment.setDeparmentUser(teamUserVos);
        deparmentList.add(deparment);
        List<CommonListHeaderVo<Deparment>> commonListHeaderVos = DataParser.parserDepList2HeaderList(deparmentList);
        allUserDepVo.setData(commonListHeaderVos);
        allUserDepVo.setDepNum(deparmentList.size());
        allUserDepVo.setMessage("成功");
        allUserDepVo.setSuccess(true);
        allUserDepVo.setTotal(total);
        return allUserDepVo;
    }

    /**
     * 查询某社团所有部门
     *
     * @param map
     * @return
     */
    @RequestMapping("teamDeps")
    public @ResponseBody
    ModelWithMessageVo fetchTeamDeps(@RequestBody Map<String, String> map) {
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        } else {
            return DataParser.setMessageVoProps(false, "失败", null, 0);
        }
        List<String> depName = teamService.getDepName(Integer.parseInt(id));
        return DataParser.setMessageVoProps(true, "成功", depName, depName.size());
    }


    /**
     * 根据id查询每个社团对应的活动
     *
     * @param map
     * @return
     */
    @RequestMapping("activity")
    public @ResponseBody
    ModelWithMessageVo fetchActivityById(@RequestBody Map<String, String> map) {
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        List<ActivityVo> activityVoList = activityService.findActivityByTeamId(Integer.parseInt(id));
        ModelWithMessageVo<List<ActivityVo>> modelWithMessageVo = new ModelWithMessageVo();
        modelWithMessageVo.setData(activityVoList);
        modelWithMessageVo.setSuccess(true);
        modelWithMessageVo.setTotal(activityVoList.size());
        modelWithMessageVo.setMessage("成功");
        return modelWithMessageVo;
    }

    /**
     * 查询所有社团
     *
     * @return
     */
    @RequestMapping("all")
    public @ResponseBody
    ModelWithMessageVo fetchAll() {
        List<Team> teamList = teamService.findAll();
        ModelWithMessageVo<List<Team>> modelWithMessageVo = new ModelWithMessageVo<>();
        modelWithMessageVo.setSuccess(true);
        modelWithMessageVo.setMessage("成功");
        modelWithMessageVo.setData(teamList);
        modelWithMessageVo.setTotal(teamList.size());
        return modelWithMessageVo;
    }

    /**
     * 查询所有社团Vo
     *
     * @return
     */
    @RequestMapping("allVo")
    public @ResponseBody
    ModelWithMessageVo fetchAllVo() {
        List<TeamVo> teamVos = teamService.findAllVo();
        teamVos = DataParser.parseTeamVoList(teamVos);
        ModelWithMessageVo modelWithMessageVo = DataParser.setMessageVoProps(true, "成功", teamVos, teamVos.size());
        return modelWithMessageVo;
    }

    /**
     * 根据关键字查找社团
     */
    @RequestMapping("key")
    public @ResponseBody
    ModelWithMessageVo fetchTeamByKey(@RequestBody Map<String, String> map) {
        String key = "";
        String value = "";
        if (map.containsKey("key")) {
            key = map.get("key");
        }
        if (map.containsKey("value")) {
            value = map.get("value");
        }
        List<TeamVo> teamVos = teamService.findAllByKey(key, value);
        teamVos = DataParser.parseTeamVoList(teamVos);
        ModelWithMessageVo modelWithMessageVo = DataParser.setMessageVoProps(true, "成功", teamVos, teamVos.size());
        return modelWithMessageVo;
    }


    /**
     * 查询所有社团Vo并且排序
     */
    @RequestMapping("allOrderBy")
    public @ResponseBody
    ModelWithMessageVo fetchAllTeamOrderBy(@RequestBody Map<String, String> map) {
        String orderBy = "";
        String desc = "";
        List<TeamVo> teamVos;
        if (map.containsKey("orderBy")) {
            orderBy = map.get("orderBy");
        }
        if (map.containsKey("desc") && map.get("desc").equals("true")) {
            teamVos = teamService.findAllOrderBy(orderBy, true);
        } else {
            teamVos = teamService.findAllOrderBy(orderBy, false);
        }
        teamVos = DataParser.parseTeamVoList(teamVos);
        ModelWithMessageVo modelWithMessageVo = DataParser.setMessageVoProps(true, "成功", teamVos, teamVos.size());
        return modelWithMessageVo;
    }

    /**
     * 条件查找
     */
    @RequestMapping("conditions")
    public @ResponseBody
    PageWithMessageVo fetchPage(@RequestBody FindConditions<Team> conditions) {
        Integer index = conditions.getPageIndex();
        if (index == null) {
            index = -1;
        }
        List<TeamVo> teamVoList = teamService.findByConditions(conditions);
        teamVoList = DataParser.parseTeamVoList(teamVoList);
        Integer total = teamService.countAll(conditions);
        return DataParser.setPageVoProps(true, "成功", teamVoList, total, index);
    }

    /**
     * 更新社团用户的级别或部门
     *
     * @param teamUser
     * @return
     */
    @RequestMapping("updateTeamUser")
    public @ResponseBody
    ModelWithMessageVo updateTeamUser(@RequestBody TeamUser teamUser) {
        if (teamUser.getUserId() == null) {
            return DataParser.setMessageVoProps(false, "失败 没有用户id", null, 0);
        }
        if (teamUser.getDepartmentName() == null) {
            return DataParser.setMessageVoProps(false, "失败 没有选择部门", null, 0);
        }
        Integer integer = teamService.updateTeamUserSelective(teamUser);
        if (integer != null && integer > 0) {
            return DataParser.setMessageVoProps(true, "成功", null, integer);
        } else {
            return DataParser.setMessageVoProps(false, "失败", null, 0);
        }
    }

    /**
     * 更新部门社团名称
     *
     * @param map
     * @return
     */
    @RequestMapping("updateDepName")
    public @ResponseBody
    ModelWithMessageVo updateDepName(@RequestBody Map<String, String> map) {
        String teamId = "";
        String olddepartmentName = "";
        String departmentName = "";
        if (map.containsKey("teamId") && map.containsKey("userId") && map.containsKey("olddepartmentName") && map.containsKey("departmentName")) {
            teamId = map.get("teamId");
            olddepartmentName = map.get("olddepartmentName");
            departmentName = map.get("departmentName");
        } else {
            return DataParser.setMessageVoProps(false, "失败 参数不全", null, 0);
        }
        Integer integer = teamService.updateDepName(teamId, olddepartmentName, departmentName);
        if (integer != null && integer > 0) {
            return DataParser.setMessageVoProps(true, "成功", null, integer);
        } else {
            return DataParser.setMessageVoProps(false, "更新失败", null, 0);
        }
    }


    /**
     * 添加部门等
     *
     * @return
     */
    @RequestMapping("addTeamUser")
    public @ResponseBody
    ModelWithMessageVo addTeamUser(@RequestBody TeamUser teamUser) {
        if (teamUser.getTeamId() == null) {
            return DataParser.setMessageVoProps(false, "社团id为null", null, 0);
        } else if (teamUser.getDepartmentName() == null || teamUser.getDepartmentName().length() == 0) {
            return DataParser.setMessageVoProps(false, "部门名称为null", null, 0);
        }
        Integer integer = teamService.insertSelective(teamUser);
        if (integer != null && integer > 0) {
            return DataParser.setMessageVoProps(true, "成功", null, integer);
        } else {
            return DataParser.setMessageVoProps(false, "添加失败", null, 0);
        }
    }

    /**
     * 逻辑添加社团用户 用户类型将-1置为5
     *
     * @return
     */
    @RequestMapping("addTeamUser2")
    public @ResponseBody
    ModelWithMessageVo addTeamUser2(@RequestBody TeamUser teamUser) {
        TeamUser teamUserById = teamService.findTeamUserById(teamUser.getTeamId(), teamUser.getUserId());
        if (teamUserById == null) {
            return DataParser.setMessageVoProps(true, "改用戶并未申请该社团", null, 0);
        }
        if (!teamUserById.getUserTeamType().equals("-1")) {
            return DataParser.setMessageVoProps(true, "您已经是该社团的用户了", null, 0);
        }
        teamUserById.setUserTeamType("5");
        teamService.updateTeamUserSelective(teamUserById);
        return DataParser.setMessageVoProps(true, "成功", null, 0);
    }

    /**
     * 删除用户等
     *
     * @return
     */
    @RequestMapping("delTeamUser")
    public @ResponseBody
    ModelWithMessageVo delTeamUser(@RequestBody TeamUser teamUser) {
        if (teamUser.getTeamId() == null) {
            return DataParser.setMessageVoProps(false, "社团id为null", null, 0);
        } else if (teamUser.getUserId() == null) {
            return DataParser.setMessageVoProps(false, "用户id为null", null, 0);
        }
        Integer integer = teamService.delete(teamUser);
        if (integer != null && integer > 0) {
            return DataParser.setMessageVoProps(true, "成功", null, integer);
        } else {
            return DataParser.setMessageVoProps(false, "添加失败", null, 0);
        }
    }

    /**
     * 添加社团
     * @param map
     * @return
     */
    @RequestMapping("add")
    public @ResponseBody
    ModelWithMessageVo addTeam(@RequestBody Map<String, String> map) {
        String userId = "";
        String teamName = "";
        String teamType = "";
        String reason = "";
        String teamTitle = "";
        if (map.containsKey("userId") && map.get("userId") != null && map.containsKey("teamName") && map.containsKey("teamType") && map.containsKey("reason") && map.containsKey("teamTitle")) {
            userId = map.get("userId");
            teamName = map.get("teamName");
            teamType = map.get("teamType");
            reason = map.get("reason");
            teamTitle = map.get("teamTitle");
        }else{
            return MessageUtils.fail("参数不全");
        }
        Team team = new Team();
        team.setTeamTitle(teamTitle);
        team.setTeamType(teamType);
        team.setTeamName(teamName);
        team.setTeamStatus("-1");//设置社团状态
        Integer insert = teamService.addTeam(team, userId, reason);
        String message = "";
        switch (insert){
            case -1://没有管理员
                break;
            case -2:
                message = "您的申请正在处理中...请耐心等候";
                return MessageUtils.fail(message);
            default:
                return MessageUtils.resultTotalCheck(insert);
        }
        return MessageUtils.resultTotalCheck(insert);
    }

    /**
     * 逻辑添加社团 逻辑置1
     * @param map
     * @return
     */
    @RequestMapping("add2")
    public @ResponseBody
    ModelWithMessageVo addTeam2(@RequestBody Map<String, String> map) {
        String userId = "";
        if (map.containsKey("userId") && map.get("userId") != null ) {
            userId = map.get("userId");
            Integer integer = teamService.addTeam2(userId);
            switch (integer){
                case -2:
                    return MessageUtils.fail("已经批准过了");
                case -1:
                    return MessageUtils.fail("操作失败");
            }
        }else{
            return MessageUtils.fail();
        }
        return DataParser.setMessageVoProps(true,"成功",null,0);
    }
}
