package com.qilu.ec.main.sample.example_create;

public class ExampleCreate_Data_Star {
    /*
    "ID": 0,
            "Content": "第一行动态",
            "Images": "",
            "PublishTime": 1593419264,
            "LikeNum": 0,
            "CommentNum": 0,
            "ForwardNum": 0,
            "IsForward": 0,
            "PreviousID": 0
     */
    private int ID;
    private String Content;
    private String PublishTime;
    private int LikeNum;
    private int CommentNum;
    private int ForwardNum;
    private int IsForward;
    private int PreviousID;

    public ExampleCreate_Data_Star(int ID, String content, String publishTime, int likeNum, int commentNum, int forwardNum, int isForward, int previousID) {
        this.ID = ID;
        Content = content;
        PublishTime = publishTime;
        LikeNum = likeNum;
        CommentNum = commentNum;
        ForwardNum = forwardNum;
        IsForward = isForward;
        PreviousID = previousID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    public int getLikeNum() {
        return LikeNum;
    }

    public void setLikeNum(int likeNum) {
        LikeNum = likeNum;
    }

    public int getCommentNum() {
        return CommentNum;
    }

    public void setCommentNum(int commentNum) {
        CommentNum = commentNum;
    }

    public int getForwardNum() {
        return ForwardNum;
    }

    public void setForwardNum(int forwardNum) {
        ForwardNum = forwardNum;
    }

    public int getIsForward() {
        return IsForward;
    }

    public void setIsForward(int isForward) {
        IsForward = isForward;
    }

    public int getPreviousID() {
        return PreviousID;
    }

    public void setPreviousID(int previousID) {
        PreviousID = previousID;
    }
}
