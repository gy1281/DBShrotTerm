package com.guoyue.kitchenhelper;

import com.guoyue.kitchenhelper.control.*;
import com.guoyue.kitchenhelper.itf.*;


public class KitchenHelperUtil {

    public static IUserManager userManager=new UserManager();
    public static IAdminManager adminManager = new AdminManager();
    public static IFoodManager foodManager = new FoodManager();
    public static IMenuManager menuManager = new MenuManager();
    public static IBookFormManager bookFormManager = new BookFormManager();
}
