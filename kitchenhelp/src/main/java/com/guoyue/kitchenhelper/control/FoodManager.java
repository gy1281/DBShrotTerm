package com.guoyue.kitchenhelper.control;

import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.food.FoodType;
import com.guoyue.kitchenhelper.itf.IFoodManager;
import com.guoyue.kitchenhelper.util.BaseException;
import com.guoyue.kitchenhelper.util.BusinessException;
import com.guoyue.kitchenhelper.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FoodManager implements IFoodManager {

    private Session session;
    private Transaction transaction;


    public void init(){
        //1、得到session对象
        session = HibernateUtil.getSession();
        //2、获取事务对象
        transaction = session.getTransaction();
        //3、开启事务
        transaction.begin();
    }

    public void destory(){
        //4、提交事务
        transaction.commit();
        //5、关闭session
        session.close();
    }

    /**
     * 获取所有食材种类
     * @return
     */
    public List<FoodType> findAllFoodType() throws BaseException {
        this.init();
        String hql = "from FoodType";
        Query query = session.createQuery(hql);
        List<FoodType> foodTypes = query.list();
        this.destory();
        System.out.println(foodTypes);
        return foodTypes;
    }

    /**
     * 获取所有食材
     * @return
     */
    public List<FoodInfo> findFoodByTypeId(Integer typeId) throws BaseException{
        this.init();
        String hql = "from FoodInfo where typeId =:typeid";
        Query query = session.createQuery(hql);
        query.setParameter("typeid", typeId);
        List<FoodInfo> foodInfos = query.list();
        this.destory();
        System.out.println(foodInfos);
        return foodInfos;
    }

    public FoodInfo findFoodById(Integer foodid){
        this.init();
        String hql = "from FoodInfo where  foodId =:foodid";
        Query query = session.createQuery(hql);
        query.setParameter("foodid", foodid);
        List<FoodInfo> foodInfos = query.list();
        this.destory();
        return foodInfos.get(0);
    }

    public FoodInfo findFoodByFoodName(String foodName) throws BaseException {
        this.init();

        String hql = "from FoodInfo where foodName =:foodname";
        Query query = session.createQuery(hql);
        query.setParameter("foodname", foodName);
        List<FoodInfo> foodInfos = query.list();
        this.destory();

        if(foodInfos.size() <= 0){
            throw new BusinessException("仓库中无该食材");
        }
        return foodInfos.get(0);
    }

    public void updateFoodInfoByFoodId(int foodId, int reduceNumber){

        FoodInfo foodInfo = this.findFoodById(foodId);

        this.init();

        foodInfo.setFoodNumber(foodInfo.getFoodNumber() - reduceNumber);

        session.update(foodInfo);

        this.destory();
    }
}
