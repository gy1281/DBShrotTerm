package com.guoyue.kitchenhelper.domain.user;


import java.io.Serializable;

public class UserFood implements Serializable {

    public static final String[] tblTitle={"食材名称","数量"};
    private Integer userId;
    private Integer foodId;
    private String foodName;
    private Integer foodNumber;
    private Float foodPrice;

    public String getCell(Integer col){
            if(col == 0) return foodName;
            else if(col == 1) return String.valueOf(foodNumber);
            else if(col == 2) return "";
            else return "";
    }


    public Float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(Integer foodNumber) {
        this.foodNumber = foodNumber;
    }

    @Override
    public String toString() {
        return "UserFood{" +
                ", userId=" + userId +
                ", foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                '}';
    }
}
