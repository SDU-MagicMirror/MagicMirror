package com.qilu.ec.main.sample.user_profile;

public class UserProfile_Data_Data {
    int UserID;
    String UserName;
    String Signature;
    String Phone;
    String Avatar;
    int FollowingNum;
    int FollowersNum;

    public UserProfile_Data_Data(int userID,
                                 String userName,
                                 String signature,
                                 String phone,
                                 String avatar,
                                 int followingNum,
                                 int followersNum) {
        UserID = userID;
        UserName = userName;
        Signature = signature;
        Phone = phone;
        Avatar = avatar;
        FollowingNum = followingNum;
        FollowersNum = followersNum;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public int getFollowingNum() {
        return FollowingNum;
    }

    public void setFollowingNum(int followingNum) {
        FollowingNum = followingNum;
    }

    public int getFollowersNum() {
        return FollowersNum;
    }

    public void setFollowersNum(int followersNum) {
        FollowersNum = followersNum;
    }
}
