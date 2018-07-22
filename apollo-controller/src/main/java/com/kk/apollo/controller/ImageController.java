package com.kk.apollo.controller;

import com.kk.apollo.biz.model.activity.ActivityVo;
import com.kk.apollo.biz.model.common.CommonVo;
import com.kk.apollo.biz.model.common.ModelWithMessageVo;
import com.kk.apollo.biz.service.activity.ActivityService;
import com.kk.apollo.common.Constant;
import com.kk.apollo.utils.DataParser;
import com.kk.apollo.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于处理图片
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ActivityService activityService;

    /**
     * 获取mac和图片名字开头
     * * TODO:安全验证
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value="/{type}/{id}/mac",method=RequestMethod.POST)
    public @ResponseBody
    ModelWithMessageVo mac(@PathVariable("id") Integer id, @PathVariable("type") String type){
        if(id==null){
            return MessageUtils.fail();
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
        String imageBaseName = "";
        CommonVo commonVo3 = new CommonVo();
        switch(type){
            case "act": //act teamID
                int size = 0;
                List<ActivityVo> activityByTeamId = activityService.findActivityByTeamId(id);
                if (activityByTeamId != null) {
                    size = activityByTeamId.size();
                }
                imageBaseName= "act_" +id + "_" + (size + 1);
                commonVo3.setDataKey("imageBaseName");
                commonVo3.setDataValue(imageBaseName);
                commonVoList.add(commonVo3);
                break;
            case "user"://user userID
                imageBaseName = "user_" + id;
                commonVo3.setDataKey("imageBaseName");
                commonVo3.setDataValue(imageBaseName);
                commonVoList.add(commonVo3);
                break;
            case "team"://
                imageBaseName = "team_" + id;
                commonVo3.setDataKey("imageBaseName");
                commonVo3.setDataValue(imageBaseName);
                commonVoList.add(commonVo3);
                break;
        }
        return DataParser.setMessageVoProps(true, "成功", commonVoList, commonVoList.size());
    }

}
