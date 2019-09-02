package com.guoyue.kitchenhelper.itf;

import com.guoyue.kitchenhelper.domain.admin.AdminInfo;
import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.food.FoodType;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.util.BaseException;

import java.util.List;

public interface IAdminManager  {

    /**
     * 管理员登录
     * @param adminId
     * @param pwd
     * @param code
     * @param vcode
     * @return
     * @throws BaseException
     */
    AdminInfo login(int adminId, String pwd, String code, String vcode) throws BaseException;

    void addFood(String foodName, float foodPrice, int foodNumber, String foodDesc, int typeId);

    void deleteFood(FoodInfo foodInfo);

    void addFoodType(String typeName, String typeDesc);

    void deleteFoodFype(FoodType foodType) throws BaseException;

    List<FoodInfo> findFoodByName(String foodName);

    List<FoodType> findFoodTypeByName(String name);

    List<UserInfo> findAllUser();

    List<UserInfo> findUserByName(String userName);

    void deleteUser(UserInfo userInfo);

    void addUser(String userName);

    void resetPas(UserInfo userInfo);
}
