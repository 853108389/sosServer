package com.kk.apollo.controller;

import com.kk.apollo.biz.model.activity.*;
import com.kk.apollo.biz.model.common.CommonVo;
import com.kk.apollo.biz.model.common.FindConditions;
import com.kk.apollo.biz.model.common.ModelWithMessageVo;
import com.kk.apollo.biz.model.common.PageWithMessageVo;
import com.kk.apollo.biz.model.user.UserActivity;
import com.kk.apollo.biz.service.activity.ActivityService;
import com.kk.apollo.biz.service.comment.CommentService;
import com.kk.apollo.biz.service.team.TeamService;
import com.kk.apollo.biz.service.user.UserActivityService;
import com.kk.apollo.common.Constant;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24.
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserActivityService userActivityService;

    @RequestMapping("/all")
    public @ResponseBody
    ModelWithMessageVo fetchAll() {
        ModelWithMessageVo<List<ActivityVo>> modelWithMessageVo = new ModelWithMessageVo<>();
        List<ActivityVo> activityVoList = activityService.findAllActivitys();
        modelWithMessageVo.setMessage("成功");
        modelWithMessageVo.setTotal(activityVoList.size());
        modelWithMessageVo.setData(activityVoList);
        modelWithMessageVo.setSuccess(true);
        return modelWithMessageVo;
    }

    /**
     * 活动点赞
     *
     * @return
     */
    @RequestMapping("/up")
    public @ResponseBody
    ModelWithMessageVo up(@RequestBody  UserActivity userActivity) {
        if (userActivity.getActivityId() == null || userActivity.getUserId() == null) {
            return MessageUtils.fail("参数不全");
        }
        Integer integer = userActivityService.up(userActivity);
        return MessageUtils.resultTotalCheck(integer);
    }

    /**
     * 是否点过赞
     *
     * @param userActivity
     * @return
     */
    @RequestMapping("/isup")
    public @ResponseBody
    ModelWithMessageVo isup(@RequestBody  UserActivity userActivity) {
        if (userActivity.getActivityId() == null || userActivity.getUserId() == null) {
            return MessageUtils.fail("参数不全");
        }
        UserActivity userActivity1 = userActivityService.selectByUserIdAndAcId(userActivity.getUserId(), userActivity.getActivityId());
        Boolean flag = false;
        if (userActivity1 == null) {
            //没点过赞
        } else {
            Integer islove = userActivity1.getIslove();
            if (islove.equals(1)) {
                //点过
                flag = true;
            } else {
                //没点过
            }
        }
        Map<String, Boolean> hashMap = new HashMap<>();
        hashMap.put("up", flag);
        return DataParser.setMessageVoProps(true, "成功", hashMap, 1);
    }


    @RequestMapping("detailVo")
    public @ResponseBody
    ModelWithMessageVo fetchActivityByIdVo(@RequestBody Map<String, String> map) {
        String url = "actLable.properties";
        String id = "";
        if (map.containsKey("id")) {
            id = map.get("id");
        }
        ActivityDetail activityDetail = activityService.findActvityById(Integer.parseInt(id));
        List<CommonVo> commonVoList = new VoDataTypeParser<ActivityDetail>(url).toType(activityDetail);
        ActivityDetailVO activityDetailVo = DataParser.parserActivityDetailVO(commonVoList);
        ModelWithMessageVo modelWithMessageVo = DataParser.setMessageVoProps(true, "成功", activityDetailVo, 1);
        return modelWithMessageVo;
    }


    /**
     * 条件查找
     */
    @RequestMapping("conditions")
    public @ResponseBody
    PageWithMessageVo fetchPage(@RequestBody FindConditions<Activity> conditions) {
        Integer index = conditions.getPageIndex();
        if (index == null) {
            index = -1;
        }
        List<ActivityVo> activityVoList = activityService.findByConditions(conditions);
        Integer total = activityService.countAll(conditions);
        return DataParser.setPageVoProps(true, "成功", activityVoList, total, index);
    }

    /**
     * 增加活动
     */
    @RequestMapping("add")
    public @ResponseBody
    ModelWithMessageVo addActivity(@RequestBody ActivityAddVo activityAddVo) {
        Activity activity = ObjectDataTypeParser.parseActivityAddVo(activityAddVo);
        Integer id = activityService.insertActivity(activity);
        ModelWithMessageVo<?> modelWithMessageVo = new ModelWithMessageVo<>();
        modelWithMessageVo.setSuccess(true);
        modelWithMessageVo.setMessage("success");
        return modelWithMessageVo;
    }

    /**
     * 返回秘钥密匙
     * -----現在用imageController里的mac方法
     *
     * @return
     */
    @Deprecated
    @RequestMapping("mac")
    public @ResponseBody
    ModelWithMessageVo addActivity(@RequestBody Map<String, String> map) {
        Integer teamId = null;
        if (map.containsKey("teamId")) {
            teamId = Integer.valueOf(map.get("teamId"));
        }
        List<CommonVo> commonVoList = new ArrayList<>();
        CommonVo commonVo = new CommonVo();
        commonVo.setDataKey("ACCESS_KEY");
        commonVo.setDataValue(Constant.ACCESS_KEY);
        CommonVo commonVo2 = new CommonVo();
        commonVo2.setDataKey("SECRET_KEY");
        commonVo2.setDataValue(Constant.SECRET_KEY);
        commonVoList.add(commonVo);
        commonVoList.add(commonVo2);
        if (teamId != null) {
            int size = 0;
            List<ActivityVo> activityByTeamId = activityService.findActivityByTeamId(teamId);
            if (activityByTeamId != null) {
                size = activityByTeamId.size();
            }
            String imageBaseName = teamId + "_" + (size + 1);
            CommonVo commonVo3 = new CommonVo();
            commonVo3.setDataKey("imageBaseName");
            commonVo3.setDataValue(imageBaseName);
            commonVoList.add(commonVo3);
        }
        return DataParser.setMessageVoProps(true, "成功", commonVoList, commonVoList.size());
    }


    /**
     * TODO 活动点喜欢
     */
    @RequestMapping("like")
    public @ResponseBody
    ModelWithMessageVo likeActivity(@RequestBody Map<String, String> map) {
        ModelWithMessageVo<?> modelWithMessageVo = new ModelWithMessageVo<>();
        return modelWithMessageVo;
    }


}
