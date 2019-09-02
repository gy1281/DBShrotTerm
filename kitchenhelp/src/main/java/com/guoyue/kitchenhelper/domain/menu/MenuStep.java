package com.guoyue.kitchenhelper.domain.menu;

public class MenuStep {
    public static final String[] tblSteptitle={"步骤数","具体操作"};
    private Integer tableId;
    private Integer menuId;
    private Integer stepOrder;
    private String stepDesc;


    public String getCell(int col){
        if(col == 0) return String.valueOf(stepOrder);
        else if(col == 1) return stepDesc;
        else return "";
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }

    @Override
    public String toString() {
        return "MenuStep{" +
                "tableId=" + tableId +
                ", menuId=" + menuId +
                ", stepOrder=" + stepOrder +
                ", stepDesc='" + stepDesc + '\'' +
                '}';
    }
}
