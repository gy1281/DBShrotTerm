package com.guoyue.kitchenhelper.control;

import com.guoyue.kitchenhelper.KitchenHelperUtil;
import com.guoyue.kitchenhelper.domain.menu.MenuEval;
import com.guoyue.kitchenhelper.domain.menu.MenuFood;
import com.guoyue.kitchenhelper.domain.menu.MenuInfo;
import com.guoyue.kitchenhelper.domain.menu.MenuStep;
import com.guoyue.kitchenhelper.domain.user.UserInfo;
import com.guoyue.kitchenhelper.itf.IMenuManager;
import com.guoyue.kitchenhelper.util.BaseException;
import com.guoyue.kitchenhelper.util.BusinessException;
import com.guoyue.kitchenhelper.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuManager implements IMenuManager {

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
    public List<MenuInfo> findAllMenu(){

        this.init();

        String hql = "from MenuInfo";
        Query query = session.createQuery(hql);
        List<MenuInfo> menuInfos = query.list();

        this.destory();

        return menuInfos;
    }

    public List<MenuInfo> findMenuByUserId(int userid) {

        this.init();

        String hql = "from MenuInfo where userId =:userid";
        Query query = session.createQuery(hql);
        query.setParameter("userid", userid);
        List<MenuInfo> menuInfos = query.list();
        this.destory();
        return menuInfos;
    }

    public List<MenuInfo> findMenuByName(String name){
        this.init();

        String hql = "from MenuInfo where menuName like :menuname";
        Query query = session.createQuery(hql);
        query.setParameter("menuname", "%"+name+"%");
        List<MenuInfo> menuInfos = query.list();

        this.destory();

        return menuInfos;
    }

    public List<MenuInfo> findMenuByDes(String des){
        this.init();

        String hql = "from MenuInfo where menuDesc like :menudes";
        Query query = session.createQuery(hql);
        query.setParameter("menudes", "%"+des+"%");
        List<MenuInfo> menuInfos = query.list();

        this.destory();

        return menuInfos;
    }

    /**
     * 根据菜谱id获得菜谱步骤
     * @param menuId
     * @return
     */
    public List<MenuStep> findMenuStepByMenuId(int menuId){
        this.init();

        String hql = "from MenuStep where menuId =:menuid";
        Query query = session.createQuery(hql);
        query.setParameter("menuid", menuId);
        List<MenuStep> menuSteps = query.list();

        this.destory();

        return menuSteps;
    }

    public List<MenuFood> findMenuFoodByMenuId(int menuId){
        this.init();

        String hql = "from MenuFood where menuId =:menuid";
        Query query = session.createQuery(hql);
        query.setParameter("menuid", menuId);
        List<MenuFood> menuFoods = query.list();

        this.destory();

        return menuFoods;
    }

    public List<MenuEval> findMenuEvalByMenuId(int menuId){
        this.init();

        String hql = "from MenuEval where menuId =:menuid";
        Query query = session.createQuery(hql);
        query.setParameter("menuid", menuId);
        List<MenuEval> menuEvals = query.list();

        this.destory();

        return menuEvals;
    }


    public void saveMenu(String menuName, String menuDesc, UserInfo curUser){

        this.init();

        MenuInfo menuInfo = new MenuInfo();

        menuInfo.setMenuName(menuName);
        menuInfo.setMenuDesc(menuDesc);

        menuInfo.setUserName(curUser.getUserName());
        menuInfo.setUserId(curUser.getUserId());

        menuInfo.setBrowNumber(0);
        menuInfo.setCollNumber(0);
        menuInfo.setComScore(0F);
        menuInfo.setMenuState("未发布");

        session.save(menuInfo);

        this.destory();
    }

    public void saveStep(int menuid, String stepDesc){
        this.init();

        MenuStep menuStep = new MenuStep();

        menuStep.setMenuId(menuid);
        menuStep.setStepDesc(stepDesc);
        menuStep.setStepOrder(this.findMenuStepByMenuId(menuid).size() + 1);

        session.save(menuStep);

        this.destory();
    }


    public void saveMenuFood(String foodName, int menuid, int foodNumber) throws BaseException {
        this.init();

        MenuFood menuFood = new MenuFood();

        menuFood.setFoodId(KitchenHelperUtil.foodManager.findFoodByFoodName(foodName).getFoodId());
        menuFood.setFoodName(foodName);
        menuFood.setMenuId(menuid);
        menuFood.setFoodNumber(foodNumber);
        menuFood.setFoodPrice(KitchenHelperUtil.foodManager.findFoodByFoodName(foodName).getFoodPrice());

        session.save(menuFood);

        this.destory();
    }

    public void saveMenuEval(int menuid, int userid, String content, float score) throws BaseException {
        this.init();

        if(score > 100){
            throw new BusinessException("请输入100以内的分数");
        }
        MenuEval menuEval = new MenuEval();

        menuEval.setBrowFlag(1);
        menuEval.setCollFlag(0);
        menuEval.setEvalCont(content);
        menuEval.setScore(score);
        menuEval.setUserId(userid);
        menuEval.setMenuId(menuid);

        session.save(menuEval);

        this.destory();
    }

    public void updateMenuBrowNumber(MenuInfo menuInfo){
        this.init();

        menuInfo.setBrowNumber(menuInfo.getBrowNumber() + 1);

        session.update(menuInfo);

        this.destory();
    }


    public void updateMenuComScore(MenuInfo menuInfo) {
        List<MenuEval> menuEvals = this.findMenuEvalByMenuId(menuInfo.getMenuId());
        this.init();
        float sum = 0;
        for(MenuEval menuEval : menuEvals){
            sum = sum + menuEval.getScore();
        }
        if(menuEvals.size() == 0)
            menuInfo.setComScore(0F);
        else{
            menuInfo.setComScore(sum / menuEvals.size());
            session.update(menuInfo);
        }
        this.destory();
    }

    public void updateMenuCollNumber(MenuInfo menuInfo){

        int collNumber = this.NumberColl(menuInfo);

        this.init();

        menuInfo.setCollNumber(collNumber);
        session.update(menuInfo);

        this.destory();
    }

    public void updateMenuEvalColl(int menuid, int userid){
        List<MenuEval> menuEvals = this.findMenuEvalByTwoId(menuid, userid);
        this.init();
        System.out.println(menuEvals.size());
        if(menuEvals.size() > 0){
            System.out.println("size > 0");
            for(MenuEval menuEval : menuEvals){
                System.out.println(1);
                if(menuEval.getCollFlag() == 1){
                    System.out.println(2);
                    menuEval.setCollFlag(0);
                    session.update(menuEval);
                    this.destory();
                    return;
                }
            }
            menuEvals.get(0).setCollFlag(1);
        }

        session.update(menuEvals.get(0));
        this.destory();
    }

    public Boolean isMenuEvalColled(int menuid, int userid){
        List<MenuEval> menuEvals = this.findMenuEvalByTwoId(menuid, userid);

        for(MenuEval menuEval : menuEvals){

            if(menuEval.getCollFlag() == 1){
                return false;
            }
        }

        return true;
    }


    public List<MenuEval> findMenuEvalByTwoId(int menuid, int userid){
        this.init();

        String hql = "from MenuEval where menuId =:menuid and userId =:userid";

        Query query = session.createQuery(hql);
        query.setParameter("menuid", menuid);
        query.setParameter("userid", userid);

        List<MenuEval> menuEvals =  query.list();

        this.destory();

        return menuEvals;
    }

    public List<MenuEval> findAllMenuEval(){
        this.init();

        String hql = "from MenuEval ";
        Query query = session.createQuery(hql);
        List<MenuEval> menuEvals = query.list();

        this.destory();

        return menuEvals;
    }

    public int NumberColl(MenuInfo menuInfo){
        List<MenuEval> menuEvals = this.findAllMenuEval();

        this.init();

        int sum = 0;
        for(MenuEval menuEval : menuEvals){
            if(menuEval.getCollFlag() == 1)
                sum = sum + 1;
        }

        this.destory();
        return sum;
    }

    public void deleteMenu(MenuInfo menuInfo) throws BaseException{
        List<MenuEval> menuEvals = KitchenHelperUtil.menuManager.findMenuEvalByMenuId(menuInfo.getMenuId());
        List<MenuFood> menuFoods = KitchenHelperUtil.menuManager.findMenuFoodByMenuId(menuInfo.getMenuId());
        List<MenuStep> menuSteps = KitchenHelperUtil.menuManager.findMenuStepByMenuId(menuInfo.getMenuId());

        this.init();

        if(menuEvals.size() != 0 || menuFoods.size() != 0 || menuSteps.size() != 0){
            throw new BusinessException("该菜谱已添加了食材步骤");
        }

        session.delete(menuInfo.getMenuId());
        this.destory();
    }

    public void deleteFood(MenuFood menuFood){

        this.init();

        session.delete(menuFood);

        this.destory();
    }

    public void deleteStep(MenuStep menuStep) {
        this.init();
        int index = 1;
        session.delete(menuStep);
        this.destory();
        List<MenuStep> menuSteps = KitchenHelperUtil.menuManager.findMenuStepByMenuId(menuStep.getMenuId());
        for(MenuStep menuStep1 : menuSteps){
            menuStep.setStepOrder(index);
            KitchenHelperUtil.menuManager.updateMenuStepOrder(menuStep1, index);
        }

    }

    public void updateMenuStepOrder(MenuStep menuStep, int index){

        this.init();

        menuStep.setStepOrder(index);

        session.update(menuStep);

        this.destory();
    }

    public List<MenuInfo> findHotestMenu(){
        List<MenuInfo> menuInfos = this.findAllMenu();
        System.out.println(menuInfos);
        MenuInfo max1;
        MenuInfo max2;
        if(menuInfos.get(0).getBrowNumber() >= menuInfos.get(1).getBrowNumber()){
            max1 = menuInfos.get(0);
            max2 = menuInfos.get(1);
        }
        else{
            max1 = menuInfos.get(1);
            max2 = menuInfos.get(2);
        }
        System.out.println("max1"+max1);
        System.out.println("max2"+max2);
        this.init();

        for(int i = 2; i < menuInfos.size(); i++){
            System.out.println(menuInfos.get(2));
            if(menuInfos.get(i).getBrowNumber() > max1.getBrowNumber()){
                max2 = max1;
                max1 = menuInfos.get(i);
            }else if(menuInfos.get(i).getBrowNumber() > max2.getBrowNumber()){
                max2 = menuInfos.get(i);
            }
        }
        List<MenuInfo> menuInfos1 = new ArrayList<MenuInfo>();
        menuInfos1.add(max1);
        menuInfos1.add(max2);
        this.destory();
        return menuInfos1;
    }

}
