package com.guoyue.kitchenhelper.domain.admin;

public class FoodBuy {

    private Integer foodBuyId;
    private Integer foodId;
    private Integer buyNumber;
    private String buyState;

    public Integer getFoodBuyId() {
        return foodBuyId;
    }

    public void setFoodBuyId(Integer foodBuyId) {
        this.foodBuyId = foodBuyId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public String getBuyState() {
        return buyState;
    }

    public void setBuyState(String buyState) {
        this.buyState = buyState;
    }

    @Override
    public String toString() {
        return "FoodBuy{" +
                "foodById=" + foodBuyId +
                ", foodId=" + foodId +
                ", buyNumber=" + buyNumber +
                ", buyState='" + buyState + '\'' +
                '}';
    }
}
