package com.example.danishlaptop.test;

/**
 * Created by DANISH.LAPTOP on 3/24/2018.
 */

public class UserInfo {

    private String Userid;
    private String UserName;
    private String UserEmail;
    private String Image;


    public UserInfo() {
    }


    public UserInfo(String userid, String userName, String userEmail) {
        Userid = userid;
        UserName = userName;
        UserEmail = userEmail;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }
}
