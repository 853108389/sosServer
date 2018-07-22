package com.kk.apollo.controller;

import com.kk.apollo.biz.model.common.ModelWithMessageVo;
import com.kk.apollo.biz.model.other.Scolview;
import com.kk.apollo.biz.service.other.MainScrollService;
import com.kk.apollo.utils.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16.
 */
@Controller
public class OtherController {
    @Autowired
    public MainScrollService mainScrollService;

    /**
     * 查询主页所有轮播图
     * @return
     */
    @RequestMapping("/scroll/mainScroll")
    public @ResponseBody
    ModelWithMessageVo fetchMainScrol() {
        List<Scolview> scolviews = mainScrollService.selectAll();
        return DataParser.setMessageVoProps(true,"成功",scolviews,scolviews.size());
    }
}
