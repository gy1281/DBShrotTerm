package com.guoyue.kitchenhelper.domain.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfo {
    public static UserInfo currentLoginUser=null;
    public static final String[] tblTitle={"用户名称","性别","密码", "电话号码","邮箱","城市","注册时间"};
    private Integer userId;
    private String userName;
    private String sex;
    private String password;
    private String phoneNumber;
    private String email;
    private String city;
    private Date regTime;


    public String getCell(Integer col){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        if(col == 0) return userName;
        else if(col == 1) return sex;
        else if(col == 2) return password;
        else if(col == 3) return phoneNumber;
        else if(col == 4) return email;
        else if(col == 5) return city;
        else if(col == 6) return simpleDateFormat.format(regTime);
        else return "";
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
