package com.kk.apollo.utils;

import com.kk.apollo.biz.model.activity.ActivityDetailVO;
import com.kk.apollo.biz.model.comment.CommentVo;
import com.kk.apollo.biz.model.common.CommonListHeaderVo;
import com.kk.apollo.biz.model.common.CommonVo;
import com.kk.apollo.biz.model.common.ModelWithMessageVo;
import com.kk.apollo.biz.model.common.PageWithMessageVo;
import com.kk.apollo.biz.model.team.*;
import com.kk.apollo.biz.model.user.MessagesVo;
import com.kk.apollo.biz.model.user.User;
import com.kk.apollo.biz.model.user.UserDetailVo;
import com.kk.apollo.biz.model.user.UserTeamVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/25.
 */
public class DataParser {
    /**
     * 转化成key-value形式
     *
     * @param user
     * @return
     */
    public static UserDetailVo parseUser2Vo(User user) {
        user = parseUser(user);
        if (user != null) {
            UserDetailVo userDetailVo = new UserDetailVo();
            userDetailVo.setUserAvatar(user.getUserAvatar());
            userDetailVo.setUserBackimg(user.getUserBackimg());
            userDetailVo.setUserBirthday(user.getUserBirthday());
            userDetailVo.setUserEmail(user.getUserEmail());
            userDetailVo.setUserGender(user.getUserGender());
            userDetailVo.setUserName(user.getUserName());
            userDetailVo.setUserNickname(user.getUserNickname());
//            userDetailVo.setUserPassword(user.getUserPassword());
            userDetailVo.setUserPhonenumber(user.getUserPhonenumber());
            userDetailVo.setUserSignature(user.getUserSignature());
            userDetailVo.setUserStuno(user.getUserStuno());
            userDetailVo.setUserType(user.getUserType());
            return userDetailVo;
        } else {
            return null;
        }
    }

    /**
     * 当用户隐藏信息时展示的数据
     * 转化成key-value形式
     *
     * @param user
     * @return
     */
    public static UserDetailVo parseUser2VoWithHidden(User user) {
        user = parseUser(user);
        if (user != null) {
            UserDetailVo userDetailVo = new UserDetailVo();
            userDetailVo.setUserAvatar(user.getUserAvatar());
            userDetailVo.setUserBackimg(user.getUserBackimg());
            userDetailVo.setUserNickname(user.getUserNickname());
            userDetailVo.setUserSignature(user.getUserSignature());
            return userDetailVo;
        } else {
            return null;
        }
    }

    /**
     * 对集合处理
     *
     * @param userTeamVoListist
     * @return
     */
    public static List<UserTeamVo> parseUserTeamVo(List<UserTeamVo> userTeamVoListist) {
        if (userTeamVoListist == null || userTeamVoListist.size() == 0) {
            return null;
        }
        List<UserTeamVo> list = new ArrayList<>(userTeamVoListist.size());
        for (int i = 0; i < userTeamVoListist.size(); i++) {
            UserTeamVo userTeamVo = userTeamVoListist.get(i);
            userTeamVo = parseUserTeamVo(userTeamVo);
            list.add(userTeamVo);
        }
        return list;
    }

    /**
     * 对集合处理
     *
     * @param teamVoList
     * @return
     */
    public static List<TeamVo> parseTeamVoList(List<TeamVo> teamVoList) {
        if (teamVoList == null || teamVoList.size() == 0) {
            return null;
        }
        List<TeamVo> list = new ArrayList<>(teamVoList.size());
        for (int i = 0; i < teamVoList.size(); i++) {
            TeamVo teamVo = teamVoList.get(i);
            teamVo = parserTeamVo(teamVo);
            list.add(teamVo);
        }
        return teamVoList;
    }

    /**
     * 转化评论中的时间格式
     *
     * @param commentvolist
     * @return
     */
    public static List<CommentVo> parserCommentList(List<CommentVo> commentvolist) {
        if (commentvolist == null || commentvolist.size() == 0) {
            return null;
        }
        List<CommentVo> list = new ArrayList<>(commentvolist.size());
        for (int i = 0; i < commentvolist.size(); i++) {
            CommentVo commentVo = commentvolist.get(i);
            list.add(parserComment(commentVo));
        }
        return list;
    }

    /**
     * 将数字转化为汉字
     *
     * @param userTeamVo
     * @return
     */
    public static UserTeamVo parseUserTeamVo(UserTeamVo userTeamVo) {
        if (userTeamVo != null) {
            switch (userTeamVo.getTeamType()) {
                case "1":
                    userTeamVo.setTeamType("校级");
                    break;
                case "2":
                    userTeamVo.setTeamType("院级");
                    break;
                case "3":
                    userTeamVo.setTeamType("兴趣");
                    break;
                default:
                    break;
            }
            switch (userTeamVo.getUserTeamType()) {
                case "1":
                    userTeamVo.setUserTeamType("主席");
                    break;
                case "2":
                    userTeamVo.setUserTeamType("副主席");
                    break;
                case "3":
                    userTeamVo.setUserTeamType("部长");
                    break;
                case "4":
                    userTeamVo.setUserTeamType("副部长");
                    break;
                case "5":
                    userTeamVo.setUserTeamType("成员");
                    break;
                default:
                    break;
            }
        }
        return userTeamVo;
    }


    public static List<TeamUserVo> parseTeamUsersVoList(List<TeamUserVo> teamUserVos) {
        if (teamUserVos == null || teamUserVos.size() == 0) {
            return null;
        }
        List<TeamUserVo> list = new ArrayList<>(teamUserVos.size());
        for (int i = 0; i < teamUserVos.size(); i++) {
            TeamUserVo teamUserVo = teamUserVos.get(i);
            teamUserVo = parseTeamUsersVo(teamUserVo);
            list.add(teamUserVo);
        }
        return list;
    }


    /**
     * 将数字转化为汉字
     *
     * @param teamusersVo
     * @return
     */

    public static TeamUserVo parseTeamUsersVo(TeamUserVo teamusersVo) {
        if (teamusersVo != null) {
            switch (teamusersVo.getUserTeamType()) {
                case "1":
                    teamusersVo.setUserTeamType("主席");
                    break;
                case "2":
                    teamusersVo.setUserTeamType("副主席");
                    break;
                case "3":
                    teamusersVo.setUserTeamType("部长");
                    break;
                case "4":
                    teamusersVo.setUserTeamType("副部长");
                    break;
                case "5":
                    teamusersVo.setUserTeamType("成员");
                    break;
                default:
                    break;
            }
        }
        return teamusersVo;
    }

    /**
     * 将数字转化为汉字
     *
     * @param user
     * @return
     */
    public static User parseUser(User user) {
        if (user != null) {
            switch (user.getUserGender()) {
                case "0":
                    user.setUserGender("男");
                    break;
                case "1":
                    user.setUserGender("女");
                    break;
                default:
                    break;
            }
            switch (user.getUserIshidden()) {
                case "0":
                    user.setUserIshidden("false");
                    break;
                case "1":
                    user.setUserIshidden("true");
                    break;
                default:
                    break;
            }
            switch (user.getUserType()) {
                case "0":
                    user.setUserType("超级管理员");
                    break;
                case "1":
                    user.setUserType("普通用户");
                    break;
                case "2":
                    user.setUserType("");
                    break;
                case "3":
                    user.setUserType("");
                    break;
                case "4":
                    user.setUserType("");
                    break;
                case "5":
                    user.setUserType("");
                    break;
                default:
                    break;
            }
        }
        return user;
    }


    /**
     * 将数字转化为汉字
     * @param user
     * @return
     */
    public static User revparseUser(User user) {
        if (user != null) {
            switch (user.getUserGender()) {
                case "男":
                    user.setUserGender("0");
                    break;
                case "女":
                    user.setUserGender("1");
                    break;
                default:
                    user.setUserGender(user.getUserGender());
                    break;
            }
            switch (user.getUserIshidden()) {
                case "false":
                    user.setUserIshidden("0");
                    break;
                case "true" :
                    user.setUserIshidden("1");
                    break;
                default:
                    user.setUserIshidden(user.getUserIshidden());
                    break;
            }
            switch (user.getUserType()) {
                case "超级管理员":
                    user.setUserType("0");
                    break;
                case "普通用户":
                    user.setUserType("1");
                    break;
//                case "2":
//                    user.setUserType("");
//                    break;
//                case "3":
//                    user.setUserType("");
//                    break;
//                case "4":
//                    user.setUserType("");
//                    break;
//                case "5":
//                    user.setUserType("");
//                    break;
                default:
                    user.setUserType(user.getUserType());
                    break;
            }
        }
        return user;
    }
    /**
     * 将数字转化为汉字
     *
     * @param teamVo
     * @return
     */
    public static TeamVo parserTeamVo(TeamVo teamVo) {
        if (teamVo != null) {
            switch (teamVo.getTeamType()) {
                case "1":
                    teamVo.setTeamType("校级");
                    break;
                case "2":
                    teamVo.setTeamType("院级");
                    break;
                case "3":
                    teamVo.setTeamType("兴趣");
                    break;
                default:
                    break;
            }
            switch (teamVo.getTeamStatus()) {
                case "1":
                    teamVo.setTeamStatus("new");
                    break;
                case "2":
                    teamVo.setTeamStatus("招新中...");
                    break;
                case "3":
                    teamVo.setTeamStatus("举办活动中...");
                    break;
                case "4":
                    teamVo.setTeamStatus("hot");
                    break;
                default:
                    break;
            }
        }
        return teamVo;
    }

    /**
     * 将数字转化为汉字
     *
     * @param team
     * @return
     */
    public static Team parserTeam(Team team) {
        if (team != null) {
            switch (team.getTeamType()) {
                case "1":
                    team.setTeamType("校级");
                    break;
                case "2":
                    team.setTeamType("院级");
                    break;
                case "3":
                    team.setTeamType("兴趣");
                    break;
                default:
                    break;
            }
            switch (team.getTeamStatus()) {
                case "1":
                    team.setTeamStatus("new");
                    break;
                case "2":
                    team.setTeamStatus("招新中...");
                    break;
                case "3":
                    team.setTeamStatus("举办活动中...");
                    break;
                default:
                    break;
            }
        }
        return team;
    }

    /**
     * 创建MODELWITHMESSAGE对象并赋值
     *
     * @param success
     * @param message
     * @param data
     * @param size
     * @param <T>
     * @return
     */
    public static <T> ModelWithMessageVo setMessageVoProps(boolean success, String message, T data, int size) {
        ModelWithMessageVo<T> modelWithMessageVo = new ModelWithMessageVo<>();
        modelWithMessageVo.setSuccess(success);
        modelWithMessageVo.setMessage(message);
        modelWithMessageVo.setData(data);
        modelWithMessageVo.setTotal(size);
        return modelWithMessageVo;
    }

    /**
     * 创建PAGEWITHMESSAGE对象并赋值
     */
    public static <T> PageWithMessageVo setPageVoProps(boolean success, String message, T data, int size, int PageIndex) {
        PageWithMessageVo<T> pageWithMessageVo = new PageWithMessageVo<>();
        pageWithMessageVo.setSuccess(success);
        pageWithMessageVo.setMessage(message);
        pageWithMessageVo.setData(data);
        pageWithMessageVo.setPageIndex(PageIndex);
        pageWithMessageVo.setTotal(size);
        return pageWithMessageVo;
    }

    /**
     * 返回社团展示的VO样式
     *
     * @param commonVoList
     * @return
     */
    public static TeamDetailVo parserTeamDetailVo(List<CommonVo> commonVoList) {
        TeamDetailVo teamDetailVo = new TeamDetailVo();
        List teamInfo = new ArrayList();
        List connectWay = new ArrayList();
        List teamRequire = new ArrayList();
        for (int i = 0; i < commonVoList.size(); i++) {
            CommonVo commonVo = commonVoList.get(i);
            String dataKey = commonVo.getDataKey();
            if (dataKey.equals("teamBackimg") || dataKey.equals("teamAvatar") || dataKey.equals("teamTitle") || dataKey.equals("teamType") || dataKey.equals("teamName") || dataKey.equals("teamIntroduction") || dataKey.equals("teamStatus") || dataKey.equals("teamActivityplace") || dataKey.equals("teamFoundtime")) {
                teamInfo.add(commonVo);
            } else if (dataKey.equals("teamGroup") || dataKey.equals("teamTel") || dataKey.equals("teamEmail")) {
                connectWay.add(commonVo);
            } else if (dataKey.equals("teamRequire")) {
                teamRequire = strToCmvoList(commonVo, i);
                teamDetailVo.setTeamRequire(teamRequire);
            } else if (dataKey.equals("teamSwipimg")) {
                String[] strings = strToArr(commonVo);
                teamDetailVo.setTeamSwipimg(strings);
            }
            teamDetailVo.setTeamInfo(teamInfo);
            teamDetailVo.setConnectWay(connectWay);
        }
        return teamDetailVo;
    }

    /**
     * VO - ActivityDetailVO
     *
     * @param commonVoList
     * @return
     */
    public static ActivityDetailVO parserActivityDetailVO(List<CommonVo> commonVoList) {
        ActivityDetailVO activityDetailVO = new ActivityDetailVO();
        List topInfo = new ArrayList();
        List activityInfo = new ArrayList();
        List activityWay = new ArrayList();
        List activityRequire = new ArrayList();
        List connectWay = new ArrayList();
        List memo = new ArrayList();
        Map<String, String> info = new HashMap<>();
        for (int i = 0; i < commonVoList.size(); i++) {
            CommonVo commonVo = commonVoList.get(i);
            String dataKey = commonVo.getDataKey();
            if (dataKey.equals("activityName") || dataKey.equals("activityIntroduction") || dataKey.equals("activityDuringtime") || dataKey.equals("activityPlace")) {
                activityInfo.add(commonVo);
            } else if (dataKey.equals("teamAvatar") || dataKey.equals("teamName") || dataKey.equals("activityPushtime") || dataKey.equals("activityBackimg") || dataKey.equals("teamId")) {
                topInfo.add(commonVo);
            } else if (dataKey.equals("activityLovers")) {
                info.put("loves", commonVo.getDataValue());
            } else if (dataKey.equals("commentNum")) {
                info.put("commons", commonVo.getDataValue());
            } else if (dataKey.equals("activityWay")) {
                activityWay = strToCmvoList(commonVo, i);
            } else if (dataKey.equals("activityMemo")) {
                memo = strToCmvoList(commonVo, i);
            } else if (dataKey.equals("activityRequire")) {
                activityRequire = strToCmvoList(commonVo, i);
            } else if (dataKey.equals("activityConnectway")) {
                connectWay = strToCmvoList(commonVo, i);
            } else if (dataKey.equals("activityImage")) {
                String[] strings = strToArr(commonVo);
                activityDetailVO.setActivityImg(strings);
            }
            activityDetailVO.setActivityInfo(activityInfo);
            activityDetailVO.setInfo(info);
            activityDetailVO.setTopInfo(topInfo);
            activityDetailVO.setActivityWay(activityWay);
            activityDetailVO.setMemo(memo);
            activityDetailVO.setActivityRequire(activityRequire);
            activityDetailVO.setConnectWay(connectWay);
        }
        return activityDetailVO;
    }


    /**
     * 转化消息对象
     *
     * @param messagesVoList
     * @return
     */
    public static List<MessagesVo> parseMessageVoList(List<MessagesVo> messagesVoList) {
        for (MessagesVo messagesVo : messagesVoList) {
            messagesVo.setMessagesDate(TimeUtils.timeParse2str2(messagesVo.getMessagesDate(), "MM月dd日HH时"));
            switch (messagesVo.getMessagesFromtype()) {
                case "0":
                    messagesVo.setMessagesFromtype("社团");
                    break;
                case "1":
                    messagesVo.setMessagesFromtype("用户");
                    break;
                default:
                    break;
            }
//             * 100 用户被社团邀请
//                    * 101 用户退出社团
//                    * 102 用户申请社团
//
//                    * 200 社团邀请用户
//                    * 201 社团踢出用户
//                    * 202 社团发布活动
            switch (messagesVo.getMessagesType()) {
                case 100:
                    break;
                case 101:
                    break;
                case 102:
                    break;
                case 200:
                    break;
                case 201:
                    break;
                case 202:
                    break;
                default:
                    break;
            }
        }
        return messagesVoList;
    }


    private static List<CommonVo> strToCmvoList(CommonVo commonVo, int i) {
        List<CommonVo> list = new ArrayList<>();
        String dataValue = commonVo.getDataValue();
        String[] split = dataValue.split("<#>");
        if (split.length <= 1) {
            list.add(commonVo);
            return list;
        } else {
            String[] ss = new String[split.length - 1];
            System.arraycopy(split, 1, ss, 0, ss.length);
            for (int j = 0; j < ss.length; j++) {
                CommonVo commonVo1 = new CommonVo();
                commonVo1.setId(i * 10 + j);
                commonVo1.setLable(j + 1 + "");
                commonVo1.setDataKey(commonVo.getDataKey() + "" + j);
                commonVo1.setDataValue(ss[j]);
                list.add(commonVo1);
            }
            return list;
        }
    }

    private static String[] strToArr(CommonVo commonVo) {
        String dataValue = commonVo.getDataValue();
        String[] split = dataValue.split("<#>");
        if (split.length <= 1) {
            return split;
        }
        String[] ss = new String[split.length - 1];
        System.arraycopy(split, 1, ss, 0, ss.length);
        return ss;
    }

    /**
     * 转化评论中的时间格式
     *
     * @param commentVo
     * @return
     */
    public static CommentVo parserComment(CommentVo commentVo) {
        if (commentVo == null) {
            return null;
        } else {
            String message = commentVo.getCommentTime();
            message = TimeUtils.timeParse2str2(message, "yyyy-MM-dd HH:mm");
            commentVo.setCommentTime(message);
        }
        return commentVo;
    }

    /**
     * 转换为listViewWithHeader形式
     */
    public static List<CommonListHeaderVo<Deparment>> parserDepList2HeaderList(List<Deparment> deparmentList) {
        List<CommonListHeaderVo<Deparment>> list = new ArrayList<>(deparmentList.size());
        for (int i = 0; i < deparmentList.size(); i++) {
            CommonListHeaderVo<Deparment> obj = parserDep2HeaderList(deparmentList.get(i));
            list.add(obj);
        }
        return list;
    }

    public static CommonListHeaderVo<Deparment> parserDep2HeaderList(Deparment deparmentList) {
        CommonListHeaderVo commonListHeaderVo = new CommonListHeaderVo();
        commonListHeaderVo.setData(deparmentList.getDeparmentUser());
        commonListHeaderVo.setSize(deparmentList.getDeparmentNum());
        commonListHeaderVo.setTitle(deparmentList.getDeparmentName());
        return commonListHeaderVo;
    }

}

