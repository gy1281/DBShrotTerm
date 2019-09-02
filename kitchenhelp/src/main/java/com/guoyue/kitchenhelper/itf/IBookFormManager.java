package com.guoyue.kitchenhelper.itf;

import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.user.BookformInfo;
import com.guoyue.kitchenhelper.domain.user.FoodBookform;
import com.guoyue.kitchenhelper.domain.user.UserFood;
import com.guoyue.kitchenhelper.util.BaseException;

import java.util.List;

public interface IBookFormManager {

    FoodBookform saveBookForm(int userId, String address, String delTime, String phone) throws Exception;

    List<FoodBookform> findFoodBookFormByUserId(int userId) throws BaseException;

    List<BookformInfo> findBookFormInfoByBfId(int bfId) throws BaseException;

    void saveBookFormInfo(List<UserFood> userFoods, int bfId);

    void saveBookFormInfoFromMenuFood(List<MenuFood> menuFoods, int bfId);

    void deleteBookFormById(FoodBookform foodBookform);
}
