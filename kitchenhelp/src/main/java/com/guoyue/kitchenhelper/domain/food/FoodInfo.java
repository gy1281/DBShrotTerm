package com.guoyue.kitchenhelper.domain.food;

public class FoodInfo {
    public static final String[] tblFoodTitle={"食材名称","价格","数量","描述"};
    private Integer foodId;
    private String foodName;
    private Float foodPrice;
    private Integer foodNumber;
    private String foodDesc;
    private Integer typeId;

    public String getCell(int col){
        if(col == 0) return foodName;
        else if(col == 1) return String.valueOf(foodPrice);
        else if(col == 2) return String.valueOf(foodNumber);
        else if(col == 3) return foodDesc;
        else if(col == 4) return "";
        else return "";
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

    public Float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(Integer foodNumber) {
        this.foodNumber = foodNumber;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "FoodInfo{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                ", foodNumber='" + foodNumber + '\'' +
                ", foodDesc='" + foodDesc + '\'' +
                '}';
    }
}
