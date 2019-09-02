package com.guoyue.kitchenhelper.domain.menu;

public class MenuEval {

    private Integer tableId;
    private Integer menuId;
    private Integer userId;
    private String evalCont;
    private Integer browFlag;
    private Integer collFlag;
    private Float score;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEvalCont() {
        return evalCont;
    }

    public void setEvalCont(String evalCont) {
        this.evalCont = evalCont;
    }

    public Integer getBrowFlag() {
        return browFlag;
    }

    public void setBrowFlag(Integer browFlag) {
        this.browFlag = browFlag;
    }

    public Integer getCollFlag() {
        return collFlag;
    }

    public void setCollFlag(Integer collFlag) {
        this.collFlag = collFlag;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MenuEval{" +
                "tableId=" + tableId +
                ", menuId=" + menuId +
                ", userId=" + userId +
                ", evalCont='" + evalCont + '\'' +
                ", browFlag=" + browFlag +
                ", collFlag=" + collFlag +
                ", score=" + score +
                '}';
    }
}
