package com.kk.apollo.biz.model.user;

public class User {
    private Integer userId;

    private String userStuno;

    private String userName;

    private String userBirthday;

    private String userAccesstoken;

    private String userNickname;

    private String userPassword;

    private String userAvatar;

    private String userBackimg;

    private String userPhonenumber;

    private String userType;

    private String userSignature;

    private String userEmail;

    private String userIshidden;

    private String userGender;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserStuno() {
        return userStuno;
    }

    public void setUserStuno(String userStuno) {
        this.userStuno = userStuno == null ? null : userStuno.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserAccesstoken() {
        return userAccesstoken;
    }

    public void setUserAccesstoken(String userAccesstoken) {
        this.userAccesstoken = userAccesstoken == null ? null : userAccesstoken.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public String getUserBackimg() {
        return userBackimg;
    }

    public void setUserBackimg(String userBackimg) {
        this.userBackimg = userBackimg == null ? null : userBackimg.trim();
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber == null ? null : userPhonenumber.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature == null ? null : userSignature.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserIshidden() {
        return userIshidden;
    }

    public void setUserIshidden(String userIshidden) {
        this.userIshidden = userIshidden == null ? null : userIshidden.trim();
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender == null ? null : userGender.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userStuno='" + userStuno + '\'' +
                ", userName='" + userName + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", userAccesstoken='" + userAccesstoken + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", userBackimg='" + userBackimg + '\'' +
                ", userPhonenumber='" + userPhonenumber + '\'' +
                ", userType='" + userType + '\'' +
                ", userSignature='" + userSignature + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userIshidden='" + userIshidden + '\'' +
                ", userGender='" + userGender + '\'' +
                '}';
    }
}