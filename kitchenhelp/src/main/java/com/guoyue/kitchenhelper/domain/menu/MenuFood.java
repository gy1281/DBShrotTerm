package com.guoyue.kitchenhelper.domain.menu;

public class MenuFood {

    public static final String[] tblFoodtitle={"食材名称","食材数量"};
    public static final String[] tbltitle={"食材名称","食材数量","食材单价"};
    private Integer tableId;
    private Integer menuId;
    private Integer foodId;
    private Integer foodNumber;
    private String unit;
    private String foodName;
    private float foodPrice;

    public String getCell(int col){
        if(col == 0) return foodName;
        else if(col == 1) return String.valueOf(foodNumber);
        else return "";
    }

    public String getCell2(int col){
        if(col == 0) return foodName;
        else if(col == 1) return String.valueOf(foodNumber);
        else if(col == 2) return String.valueOf(foodPrice);
        else return "";
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(Integer foodNumber) {
        this.foodNumber = foodNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "MenuFood{" +
                "tableId=" + tableId +
                ", menuId=" + menuId +
                ", foodId=" + foodId +
                ", foodNumber=" + foodNumber +
                ", unit='" + unit + '\'' +
                '}';
    }
}
