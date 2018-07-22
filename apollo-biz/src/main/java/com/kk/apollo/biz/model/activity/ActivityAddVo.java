package com.kk.apollo.biz.model.activity;

import com.kk.apollo.biz.model.common.CommonVo;
import com.kk.apollo.biz.model.common.ImageObj;
import com.kk.apollo.biz.model.user.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加活动
 */
public class ActivityAddVo extends Messages{

    private List<CommonVo> activityInfo;
    private List<CommonVo> activityRequire;
    private List<CommonVo> connectWay;
    private List<CommonVo> memo;
    private List<ImageObj> activityImg;
    private List<CommonVo> activityWay;
    private ImageObj  activityBackimg;

    public ImageObj getActivityBackimg() {
        return activityBackimg;
    }

    public void setActivityBackimg(ImageObj activityBackimg) {
        this.activityBackimg = activityBackimg;
    }

    public ActivityAddVo() {
        activityInfo = new ArrayList<>();
        connectWay = new ArrayList<>();
        memo = new ArrayList<>();
        activityImg = new ArrayList<>();
        activityRequire = new ArrayList<>();
        activityWay = new ArrayList<>();
    }

    public List<ImageObj> getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(List<ImageObj> activityImg) {
        this.activityImg = activityImg;
    }

    public List<CommonVo> getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(List<CommonVo> activityInfo) {
        this.activityInfo = activityInfo;
    }

    public List<CommonVo> getActivityRequire() {
        return activityRequire;
    }

    public void setActivityRequire(List<CommonVo> activityRequire) {
        this.activityRequire = activityRequire;
    }

    public List<CommonVo> getConnectWay() {
        return connectWay;
    }

    public void setConnectWay(List<CommonVo> connectWay) {
        this.connectWay = connectWay;
    }

    public List<CommonVo> getMemo() {
        return memo;
    }

    public void setMemo(List<CommonVo> memo) {
        this.memo = memo;
    }

    public List<CommonVo> getActivityWay() {
        return activityWay;
    }

    public void setActivityWay(List<CommonVo> activityWay) {
        this.activityWay = activityWay;
    }


}

