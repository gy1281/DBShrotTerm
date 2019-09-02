package com.guoyue.kitchenhelper.itf;

import com.guoyue.kitchenhelper.domain.menu.MenuEval;
import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.menu.MenuInfo;
import com.guoyue.kitchenhelper.domain.menu.MenuStep;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.util.BaseException;

import java.util.BitSet;
import java.util.List;

public interface IMenuManager {

    List<MenuInfo> findAllMenu();

    List<MenuInfo> findMenuByUserId(int userid);

    List<MenuInfo> findMenuByName(String name);

    List<MenuInfo> findMenuByDes(String des);

    List<MenuStep> findMenuStepByMenuId(int menuId);

    List<MenuFood> findMenuFoodByMenuId(int menuId);

    void saveMenu(String menuName, String menuDesc, UserInfo curUser);

    void saveStep(int menuid, String stepDesc);

    void saveMenuFood(String foodName, int menuid, int foodNUmber) throws BaseException;

    List<MenuEval> findMenuEvalByMenuId(int menuId);

    void saveMenuEval(int menuid, int userid, String content, float score) throws BaseException;

    void updateMenuComScore(MenuInfo menuInfo);

    void updateMenuBrowNumber(MenuInfo menuInfo);

    List<MenuEval> findMenuEvalByTwoId(int menuid, int userid);

    void updateMenuEvalColl(int menuid, int userid);

    Boolean isMenuEvalColled(int menuid, int userid);

    void updateMenuCollNumber(MenuInfo menuInfo);

    int NumberColl(MenuInfo menuInfo);

    List<MenuEval> findAllMenuEval();

    void deleteMenu(MenuInfo menuInfo) throws BaseException;

    void deleteFood(MenuFood menuFood);

    void deleteStep(MenuStep menuStep);

    void updateMenuStepOrder(MenuStep menuStep, int index);

    List<MenuInfo> findHotestMenu();
}
