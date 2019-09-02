package com.guoyue.kitchenhelper.itf;

import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.food.FoodType;
import com.guoyue.kitchenhelper.util.BaseException;

import java.util.List;

public interface IFoodManager {

    List<FoodType> findAllFoodType() throws BaseException;

    List<FoodInfo> findFoodByTypeId(Integer typeId) throws BaseException;

    FoodInfo findFoodById(Integer foodid);

    FoodInfo findFoodByFoodName(String foodName) throws BaseException;

    void updateFoodInfoByFoodId(int foodId, int reduceNumber);
}
