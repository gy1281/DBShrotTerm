package com.guoyue.kitchenhelper.domain.menu;

public class MenuInfo {
    public static MenuInfo currentMenu=null;
    public static final String[] tblMenutitle={"菜谱名称","菜谱标签","贡献用户","收藏数量", "浏览次数", "综合评分"};
    public static final String[] tblMenutitle2={"菜谱名称","菜谱标签","贡献用户","收藏数量", "浏览次数", "综合评分", "菜谱状态"};
    private Integer menuId;
    private String menuName;
    private String userName;
    private int userId;
    private String menuDesc;
    private Float comScore;
    private Integer collNumber;
    private Integer browNumber;
    private String menuState;

    public String getCell(int col){
        if(col == 0) return menuName;
        else if(col == 1) return menuDesc;
        else if(col == 2) return userName;
        else if(col == 3) return String.valueOf(collNumber);
        else if(col == 4) return String.valueOf(browNumber);
        else if(col == 5) return String.valueOf(comScore);
        else return "";
    }


    public String getCell2(int col){
        if(col == 0) return menuName;
        else if(col == 1) return menuDesc;
        else if(col == 2) return userName;
        else if(col == 3) return String.valueOf(collNumber);
        else if(col == 4) return String.valueOf(browNumber);
        else if(col == 5) return String.valueOf(comScore);
        else if(col == 6) return menuState;
        else return "";
    }


    public Integer getMenuId() {
        return menuId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public Float getComScore() {
        return comScore;
    }

    public void setComScore(Float comScore) {
        this.comScore = comScore;
    }

    public Integer getCollNumber() {
        return collNumber;
    }

    public void setCollNumber(Integer collNumber) {
        this.collNumber = collNumber;
    }

    public Integer getBrowNumber() {
        return browNumber;
    }

    public void setBrowNumber(Integer brewNumber) {
        this.browNumber = brewNumber;
    }

    @Override
    public String toString() {
        return "MenuInfo{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", userId='" + userName + '\'' +
                ", menuDesc='" + menuDesc + '\'' +
                ", comScore=" + comScore +
                ", collNumber=" + collNumber +
                ", brewNumber=" + browNumber +
                '}';
    }
}
