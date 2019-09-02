package com.guoyue.kitchenhelper.domain.user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodBookform {

    public static final String[] tblFormTitle={"订单编号","配送地址","联系电话","送达时间","订单状态"};
    private Integer bfId;
    private Integer userId;
    private Date delTime;
    private String desAddress;
    private String phoneNumber;
    private String bookformState;

    public String getCell(int col){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        if(col == 0) return String.valueOf(bfId);
        else if(col == 1) return desAddress;
        else if(col == 2) return phoneNumber;
        else if(col == 3) return simpleDateFormat.format(delTime);
        else if(col == 4) return bookformState;
        else return "";
    }

    public Integer getBfId() {
        return bfId;
    }

    public void setBfId(Integer bfId) {
        this.bfId = bfId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getDesAddress() {
        return desAddress;
    }

    public void setDesAddress(String desAddress) {
        this.desAddress = desAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBookformState() {
        return bookformState;
    }

    public void setBookformState(String bookformState) {
        this.bookformState = bookformState;
    }

    @Override
    public String toString() {
        return "FoodBookform{" +
                "bfId=" + bfId +
                ", userId=" + userId +
                ", delTime=" + delTime +
                ", desAddress='" + desAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bookformState='" + bookformState + '\'' +
                '}';
    }
}
