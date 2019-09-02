package com.guoyue.kitchenhelper.control;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.admin.AdminInfo;
import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.food.FoodType;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.itf.IAdminManager;
import com.guoyue.kitchenhelper.util.BaseException;
import com.guoyue.kitchenhelper.util.BusinessException;
import com.guoyue.kitchenhelper.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.TypeInfo;
import sun.awt.FontDescriptor;

import java.util.Date;
import java.util.List;

public class AdminManager implements IAdminManager {

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

    public AdminInfo login(int adminId, String pwd, String code, String vcode) throws BaseException {
        if(!code.equals(vcode)){
            throw new BusinessException("验证码错误");
        }
        this.init();
        String hql = "select password from AdminInfo where empId = :emp_id";
        Query query = session.createQuery(hql);
        query.setParameter("emp_id", adminId);
        List<String> passwords = query.list();
        if(passwords.size() == 0){
            throw new BusinessException("用户名或密码错误");
        }
        else{
            if(!passwords.get(0).equals(pwd)){
                throw new BusinessException("用户名或密码错误");
            }
        }
        hql = "from AdminInfo where empId =:emp_id";
        query = session.createQuery(hql);
        query.setParameter("emp_id", adminId);
        List<AdminInfo> admin = query.list();
        AdminInfo result = admin.get(0);
        this.destory();
        System.out.println("管理员登录程序完成");
        return result;
    }


    public void addFood(String foodName, float foodPrice, int foodNumber, String foodDesc, int typeId){
        this.init();

        FoodInfo foodInfo = new FoodInfo();

        foodInfo.setFoodName(foodName);
        foodInfo.setFoodPrice(foodPrice);
        foodInfo.setFoodNumber(foodNumber);
        foodInfo.setFoodDesc(foodDesc);
        foodInfo.setTypeId(typeId);

        session.save(foodInfo);

        this.destory();
    }

    public void deleteFood(FoodInfo foodInfo){
        this.init();

        session.delete(foodInfo);

        this.destory();
    }

    public void addFoodType(String typeName, String typeDesc){
        this.init();

        FoodType foodType = new FoodType();

        foodType.setTypeName(typeName);
        foodType.setTypeDesc(typeDesc);

        session.save(foodType);

        this.destory();
    }

    public void deleteFoodFype(FoodType foodType) throws BaseException{

        List<FoodInfo> foodInfos = KitchenHelperUtil.foodManager.findFoodByTypeId(foodType.getTypeId());

        if(foodInfos.size() > 0){
            throw new BusinessException("该食材类别下存在食材，无法删除");
        }
        this.init();

        session.delete(foodType);

        this.destory();
    }

    public List<FoodInfo> findFoodByName(String foodName){

        this.init();

        String hql = "from FoodInfo where foodName =:foodname";
        Query query = session.createQuery(hql);
        query.setParameter("foodname", foodName);
        List<FoodInfo> foodInfos = query.list();

        this.destory();

        return foodInfos;
    }

    public List<FoodType> findFoodTypeByName(String name){
        this.init();

        String hql = "from FoodType where typeName =:typename";
        Query query = session.createQuery(hql);
        query.setParameter("typename", name);
        List<FoodType> foodTypes = query.list();
        System.out.println("食材种类：" + foodTypes);
        this.destory();
        return foodTypes;
    }

    public List<UserInfo> findAllUser(){

        this.init();

        String hql = "from UserInfo";
        Query query = session.createQuery(hql);
        List<UserInfo> userInfos = query.list();
        this.destory();

        return userInfos;
    }


    public List<UserInfo> findUserByName(String userName){
        this.init();

        String hql = "from UserInfo where userName =:username";
        Query query = session.createQuery(hql);
        query.setParameter("username", userName);
        List<UserInfo> userInfos = query.list();

        this.destory();
        return userInfos;
    }

    public void addUser(String userName){

        this.init();

        UserInfo userInfo = new UserInfo();

        userInfo.setUserName(userName);
        userInfo.setPassword(userName);
        userInfo.setCity("");
        userInfo.setEmail("");
        userInfo.setPhoneNumber("");
        userInfo.setSex("");
        userInfo.setRegTime(new Date());

        session.save(userInfo);

        this.destory();
    }

    public void deleteUser(UserInfo userInfo){
        this.init();

        session.delete(userInfo);

        this.destory();
    }

    public void resetPas(UserInfo userInfo){
        this.init();

        userInfo.setPassword(userInfo.getUserName());

        session.update(userInfo);

        this.destory();
    }
}
