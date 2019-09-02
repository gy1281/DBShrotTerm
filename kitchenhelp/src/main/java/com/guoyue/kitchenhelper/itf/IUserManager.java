package com.guoyue.kitchenhelper.itf;

import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.user.UserFood;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.util.BaseException;

import java.util.List;

public interface IUserManager {


    /**
     * 用户注册
     * @param username
     * @param pwd1
     * @param sex
     * @param phone
     * @param email
     * @param city
     * @return
     * @throws BaseException
     */
    UserInfo reg(String username, String pwd1, String sex, String phone, String email,String city) throws BaseException;

    /**
     * 用户登录
     * @param username
     * @param pwd
     * @return
     */
    UserInfo login(String username, String pwd, String code, String vcode) throws BaseException;

    List<UserFood> findAllUserFoodByUserId(Integer userId) throws BaseException;

    /**
     * 新增用户食材
     * @param food
     * @param userId
     */
    void saveUserFood(FoodInfo food, Integer userId);


    /**
     * 更新用户食材
     * @param food
     */
    void updataUserFoodPlus(UserFood food);

    List<UserFood> findAllUserFoodByFoodIdAndUserId(Integer foodId, Integer userId);

    void deleteUserFood(UserFood userFood);

    void updataUserFoodReduce(UserFood food);

    void changepwd(UserInfo user, String oldPwd, String newPwd1, String newPwd2) throws BaseException;

    void changeusername(UserInfo user, String newUsername);

    UserInfo findUserById(int userId);
}
