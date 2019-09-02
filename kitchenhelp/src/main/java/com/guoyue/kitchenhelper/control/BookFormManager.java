package com.guoyue.kitchenhelper.control;

import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.user.BookformInfo;
import com.guoyue.kitchenhelper.domain.user.FoodBookform;
import com.guoyue.kitchenhelper.domain.user.UserFood;
import com.guoyue.kitchenhelper.itf.IBookFormManager;
import com.guoyue.kitchenhelper.util.BaseException;
import com.guoyue.kitchenhelper.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.List;

public class BookFormManager implements IBookFormManager {

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

    public FoodBookform saveBookForm(int userId, String address, String delTime, String phone) throws Exception{

        this.init();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        FoodBookform foodBookform = new FoodBookform();

        foodBookform.setUserId(userId);
        foodBookform.setDesAddress(address);
        foodBookform.setDelTime(simpleDateFormat.parse(delTime));
        foodBookform.setPhoneNumber(phone);
        foodBookform.setBookformState("下单");

        session.save(foodBookform);

        this.destory();

        return foodBookform;
    }

    public List<FoodBookform> findFoodBookFormByUserId(int userId) throws BaseException {
        this.init();

        String hql = "from FoodBookform where userId = :userid";
        Query query = session.createQuery(hql);
        query.setParameter("userid", userId);

        List<FoodBookform> foodBookforms = query.list();

        this.destory();

        return foodBookforms;
    }

    public List<BookformInfo> findBookFormInfoByBfId(int bfId) throws BaseException {
        this.init();

        String hql = "from BookformInfo where bfId = :bfid";
        Query query = session.createQuery(hql);
        query.setParameter("bfid", bfId);

        List<BookformInfo> bookformInfos = query.list();

        this.destory();

        return bookformInfos;
    }

    public void saveBookFormInfo(List<UserFood> userFoods, int bfId){
        this.init();

        for(UserFood userFood : userFoods){
            BookformInfo bookformInfo = new BookformInfo();

            bookformInfo.setBfId(bfId);
            bookformInfo.setBfNumber(userFood.getFoodNumber());
            bookformInfo.setFoodId(userFood.getFoodId());
            bookformInfo.setPrice(userFood.getFoodPrice());
            bookformInfo.setFoodName(userFood.getFoodName());

            session.save(bookformInfo);
        }

        this.destory();
    }


    public void saveBookFormInfoFromMenuFood(List<MenuFood> menuFoods, int bfId){

        this.init();

        for(MenuFood menuFood : menuFoods){
            BookformInfo bookformInfo = new BookformInfo();

            bookformInfo.setBfId(bfId);
            bookformInfo.setBfNumber(menuFood.getFoodNumber());
            bookformInfo.setFoodId(menuFood.getFoodId());
            bookformInfo.setPrice(menuFood.getFoodPrice());
            bookformInfo.setFoodName(menuFood.getFoodName());

            session.save(bookformInfo);
        }

        this.destory();
    }

    public void deleteBookFormById(FoodBookform foodBookform){
        this.init();

        session.delete(foodBookform);

        this.destory();
    }
}
