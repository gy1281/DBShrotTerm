package com.guoyue.kitchenhelper.domain.user;


public class BookformInfo {

    public static final String[] tblInfoTitle={"食材名称","食材数量","食材价格"};

    private Integer tableId;
    private Integer bfId;
    private Integer foodId;
    private Integer bfNumber;
    private Float price;
    private Float discount;
    private String foodName;

    public String getCell(int col){
        if(col == 0) return this.getFoodName();
        else if(col == 1) return String.valueOf(this.getBfNumber());
        else if(col == 2) return String.valueOf(this.getPrice());
        else return "";
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getBfId() {
        return bfId;
    }

    public void setBfId(Integer bfId) {
        this.bfId = bfId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getBfNumber() {
        return bfNumber;
    }

    public void setBfNumber(Integer bfNumber) {
        this.bfNumber = bfNumber;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "BookformInfo{" +
                "tableId=" + tableId +
                ", bfId=" + bfId +
                ", foodId=" + foodId +
                ", bfNumber=" + bfNumber +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
