package com.guoyue.kitchenhelper.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author mark
 */
public class HibernateUtil {
    private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public static Session getSession(){
        Session session = factory.openSession();
        return session;
    }

    public static void main(String[] args) {
        Session session = getSession();
        System.out.println(session);
    }

}