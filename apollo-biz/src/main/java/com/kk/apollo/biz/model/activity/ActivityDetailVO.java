package com.kk.apollo.biz.model.activity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/29.
 */
public class ActivityDetailVO {
    private List TopInfo;
    private List activityInfo;
    private List activityWay;
    private List activityRequire;
    private List connectWay;
    private List memo;
    private String[] activityImg;
    private Map<String, String> info;

    public List getTopInfo() {
        return TopInfo;
    }

    public void setTopInfo(List topInfo) {
        TopInfo = topInfo;
    }

    public List getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(List activityInfo) {
        this.activityInfo = activityInfo;
    }

    public List getActivityWay() {
        return activityWay;
    }

    public void setActivityWay(List activityWay) {
        this.activityWay = activityWay;
    }

    public List getActivityRequire() {
        return activityRequire;
    }

    public void setActivityRequire(List activityRequire) {
        this.activityRequire = activityRequire;
    }

    public List getConnectWay() {
        return connectWay;
    }

    public void setConnectWay(List connectWay) {
        this.connectWay = connectWay;
    }

    public List getMemo() {
        return memo;
    }

    public void setMemo(List memo) {
        this.memo = memo;
    }

    public String[] getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String[] activityImg) {
        this.activityImg = activityImg;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }
}
