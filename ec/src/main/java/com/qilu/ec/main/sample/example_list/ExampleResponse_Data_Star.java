package com.qilu.ec.main.sample.example_list;

public class ExampleResponse_Data_Star {
    /*
    "ID": 9,
                "Content": "KKK",
                "Images": "",
                "PublishTime": 1593435871,
                "LikeNum": 0,
                "CommentNum": 0,
                "ForwardNum": 0,
                "IsForward": 0,
                "PreviousID": 0
     */
    private String ID;
    private String Content;
    private String Images;
    private String PublishTime;
    private int LikeNum;
    private int CommentNum;
    private int ForwardNum;
    private int IsForward;
    private int PreviousID;

    public ExampleResponse_Data_Star(String ID,
                                     String content,
                                     String images,
                                     String publishTime,
                                     int likeNum,
                                     int commentNum,
                                     int forwardNum,
                                     int isForward,
                                     int previousID) {
        this.ID = ID;
        Content = content;
        Images = images;
        PublishTime = publishTime;
        LikeNum = likeNum;
        CommentNum = commentNum;
        ForwardNum = forwardNum;
        IsForward = isForward;
        PreviousID = previousID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
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
