package com.guoyue.kitchenhelper.control;

import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.user.UserFood;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.itf.IUserManager;
import com.guoyue.kitchenhelper.util.BaseException;
import com.guoyue.kitchenhelper.util.BusinessException;
import com.guoyue.kitchenhelper.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class UserManager implements IUserManager {

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

    public UserInfo reg(String username, String pwd, String sex, String phone, String email, String city) throws BaseException{
        if(username == null || pwd == null){
            throw new BusinessException("请填带*的信息");
        }
        this.init();

        String hql = "select userId from UserInfo where userName = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        List<UserInfo> users = query.list();
        System.out.println(users);
        if(!users.isEmpty()){
            throw new BusinessException("用户名已存在");
        }

        UserInfo user = new UserInfo();
        user.setUserName(username);
        user.setPassword(pwd);
        user.setSex(sex);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        user.setCity(city);
        user.setRegTime(new Date());

        session.save(user);

        this.destory();
        return null;
    }

    public UserInfo login(String username, String pwd, String code, String vcode) throws BaseException {
        if(!code.equals(vcode)){
            throw new BusinessException("验证码错误");
        }
        this.init();
        String hql = "select password from UserInfo where userName = :user_name";
        Query query = session.createQuery(hql);
        query.setString("user_name", username);
        List<String> passwords = query.list();
        if(passwords.size() == 0){
            throw new BusinessException("用户名或密码错误");
        }
        else{
            if(!passwords.get(0).equals(pwd)){
                throw new BusinessException("用户名或密码错误");
            }
        }
        hql = "from UserInfo u where u.userName =:user_name";
        query = session.createQuery(hql);
        query.setString("user_name", username);
        List<UserInfo> user = query.list();
        UserInfo result = user.get(0);
        this.destory();
        System.out.println("登录程序完成");
        return result;
    }

    public UserInfo findUserById(int userId){

        this.init();

        UserInfo userInfo = (UserInfo)session.get(UserInfo.class, userId);
        this.destory();

        return userInfo;
    }


    public List<UserFood> findAllUserFoodByUserId(Integer userId) throws BaseException {
        this.init();
        String hql = "from UserFood where userId = :userid";
        Query query = session.createQuery(hql);
        query.setParameter("userid", userId);
        List<UserFood> userFoods = query.list();
        this.destory();
        System.out.println(userFoods);
        return userFoods;
    }

    public List<UserFood> findAllUserFoodByFoodIdAndUserId(Integer foodId,Integer userId){
        this.init();
        String hql = "from UserFood where foodId = :foodid and userId =:userid";
        Query query = session.createQuery(hql);
        query.setParameter("foodid", foodId);
        query.setParameter("userid", userId);
        List<UserFood> userFoods = query.list();
        this.destory();
        System.out.println(userFoods);
        return userFoods;
    }

    public void saveUserFood(FoodInfo food, Integer userId) {
        this.init();

        UserFood userFood = new UserFood();

        userFood.setUserId(userId);
        userFood.setFoodId(food.getFoodId());
        userFood.setFoodName(food.getFoodName());
        userFood.setFoodNumber(1);
        userFood.setFoodPrice(food.getFoodPrice());
        session.save(userFood);

        this.destory();
    }

    public void updataUserFoodPlus(UserFood food){
        this.init();

        food.setFoodNumber(food.getFoodNumber() + 1);

        session.update(food);

        this.destory();
    }

    public void updataUserFoodReduce(UserFood food){
        this.init();

        food.setFoodNumber(food.getFoodNumber() - 1);

        session.update(food);

        this.destory();
    }

    public void deleteUserFood(UserFood userFood){
        this.init();;

        session.delete(userFood);
        this.destory();
    }


    public void changepwd(UserInfo user, String oldPwd, String newPwd1, String newPwd2) throws BaseException{

        if(!newPwd1.equals(newPwd2)){
            throw new BusinessException("两次输入的密码不匹配");
        }
        this.init();
        user.setPassword(newPwd1);
        session.update(user);
        this.destory();
    }

    public void changeusername(UserInfo user, String newUsername){
        this.init();
        user.setUserName(newUsername);
        session.update(user);
        this.destory();
    }


}
