package com.guoyue.test;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.control.BookFormManager;
import com.guoyue.kitchenhelper.control.FoodManager;
import com.guoyue.kitchenhelper.control.MenuManager;
import com.guoyue.kitchenhelper.control.UserManager;
import com.guoyue.kitchenhelper.domain.admin.AdminInfo;
import com.guoyue.kitchenhelper.domain.food.FoodInfo;
import com.guoyue.kitchenhelper.domain.food.FoodType;
import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.menu.MenuInfo;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.ui.FrmFoodSel;
import com.guoyue.kitchenhelper.util.BaseException;
import com.guoyue.kitchenhelper.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

public class AdminTest {

    @Test
    public void saveAdminTest(){

        AdminInfo admin = new AdminInfo();
        admin.setEmpName("郭岳");
        admin.setPassword("123456");

        //1、得到session对象
        Session session = HibernateUtil.getSession();
        //2、获取事务对象
        Transaction transaction = session.getTransaction();
        //3、开启事务
        transaction.begin();
        //4、把对象添加到数据库
        session.save(admin);
        //5、提交事务
        transaction.commit();
        //6、关闭session
        session.close();
    }

    @Test
    public void listUserTest(){
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        String hql = "from UserInfo";
        List<UserInfo> users = session.createQuery(hql).list();
        for(UserInfo user : users){
            System.out.println(user);
        }
    }

    @Test
    public void userTest() throws Exception{
        UserManager um = new UserManager();
        um.reg("guoqs", "123456", "男", "13588888888","123@126.com", "hangzhou");
    }

    @Test
    public void footTest() throws Exception{
        FoodManager fm = new FoodManager();
        List<FoodType> types = fm.findAllFoodType();
        for(FoodType type : types){
            System.out.println(type);
        }
    }

    @Test
    public void foodSelTest(){
        FrmFoodSel foodSel = new FrmFoodSel();
    }


    @Test
    public void updateUserFoodTest(){
        FoodInfo foodInfo = new FoodInfo();

        foodInfo.setFoodId(2);
        foodInfo.setFoodNumber(1);
        foodInfo.setFoodName("菠菜");

        UserManager um = new UserManager();
        um.saveUserFood(foodInfo, 1);
    }

    @Test
    public void savaBookFormTest() throws Exception{

        BookFormManager bf = new BookFormManager();

        bf.saveBookForm(1,"1","1998-11-22","1");
    }

    @Test
    public void menuInfoTest(){
        MenuManager mm = new MenuManager();
        List<MenuInfo> menuInfos = mm.findAllMenu();
        System.out.println(menuInfos);
    }

    @Test
    public void updateMenuEvalCollTest(){
        MenuManager mm = new MenuManager();

        mm.updateMenuEvalColl(1,1);
    }

    @Test
    public void deleteMenuTest() throws BaseException {

        MenuInfo menuInfo = new MenuInfo();

        menuInfo.setMenuId(35);

        KitchenHelperUtil.menuManager.deleteMenu(menuInfo);
    }

    @Test
    public void adminLoginTest() throws BaseException{
        KitchenHelperUtil.adminManager.login(4,"4","1","1");
    }

    @Test
    public void deleteFoodTest(){
        FoodInfo foodInfo = KitchenHelperUtil.foodManager.findFoodById(37);
        KitchenHelperUtil.adminManager.deleteFood(foodInfo);
    }

    @Test
    public void find(){
        List<MenuFood> menuFoods = KitchenHelperUtil.menuManager.findMenuFoodByMenuId(1);
        System.out.println(menuFoods);
    }

    @Test
    public void updateMenuEvalColl(){
        KitchenHelperUtil.menuManager.updateMenuEvalColl(1,1);
    }

    @Test
    public void asd(){
        List<MenuInfo> menuInfos=KitchenHelperUtil.menuManager.findHotestMenu();
        System.out.println(menuInfos);
    }
}
