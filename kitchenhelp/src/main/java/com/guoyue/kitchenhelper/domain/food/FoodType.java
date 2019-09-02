package com.guoyue.kitchenhelper.domain.food;

public class FoodType {
    public static final String[] tblTypeTitle={"食材种类","描述"};
    private Integer typeId;
    private String typeName;
    private String typeDesc;

    public String getCell(Integer col){
        if(col == 0) return typeName;
        else if(col == 1) return typeDesc;
        else return "";
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                '}';
    }
}
