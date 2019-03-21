package com.boollean;

import com.boollean.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class UserManageAction {

    private static Configuration configuration;
    private static SessionFactory factory;
    private static Session session;

    private static void init(){
        //1.加载配置文件,设置配置文件,默认加载src目录下的hibernate.cfg.xml的配置文件
        configuration = new Configuration().configure();
        //2.建立SessionFactory对象
        factory = configuration.buildSessionFactory();
        //3.打开session对象
        session = factory.openSession();
    }

    public static List<UserEntity> listAllUser(){
        init();
        String queryString = "FROM UserEntity";
        List<UserEntity> list = null;
        try{
            Query query = session.createQuery(queryString);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.println("Name: " + userEntity.getName());
            }
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return list;
    }

    public static List<UserEntity> listBest100Users4(){
        init();
        String queryString = "FROM UserEntity U WHERE U.bestscore4 > 0 ORDER BY U.bestscore4 DESC ,U.name asc";
        List<UserEntity> list = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(queryString);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  4*4分数： "+userEntity.getBestscore4());
            }
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return list;
    }

    public static List<UserEntity> listBest100Users5(){
        init();
        String queryString = "FROM UserEntity U WHERE U.bestscore5 > 0 ORDER BY U.bestscore5 DESC ,U.name asc";
        List<UserEntity> list = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(queryString);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  5*5分数： "+userEntity.getBestscore5());
            }
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return list;
    }

    public static List<UserEntity> listBest100Users6(){
        init();
        String queryString = "FROM UserEntity U WHERE U.bestscore6 > 0 ORDER BY U.bestscore6 DESC ,U.name asc";
        List<UserEntity> list = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery(queryString);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  6*6分数： "+userEntity.getBestscore6());
            }
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return list;
    }

    public static boolean isUserNameAvailable(String name){
        if(name.isEmpty()){
            System.out.println("名字为空，不可用");
            return false; }
        init();
        Transaction transaction = null;
        String queryString = "FROM UserEntity U WHERE U.name = ?1";
        try{
            transaction =session.beginTransaction();
            Query query = session.createQuery(queryString).setParameter(1,name);
            transaction.commit();
            if(query.list().isEmpty()){
                return true;
            }
        }catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
        return false;
    }

    public static UserEntity addUser(String name, String password, int gender){
        return addUser(name,password,gender,"a");
    }

    public static UserEntity addUser(String name, String password, int gender,String avatar){
       return addUser(name,password,gender,avatar,0);
    }

    public static UserEntity addUser(String name, String password, int gender,String avatar,int score4){
        return addUser(name,password,gender,avatar,score4,0);
    }

    public static UserEntity addUser(String name, String password, int gender,String avatar,int score4,int score5){
        return addUser(name,password,gender,avatar,score4,score5,0);
    }

    public static UserEntity addUser(String name, String password, int gender,String avatar,int score4,int score5,int score6){
        UserEntity userEntity = null;
        if (isUserNameAvailable(name)){
            init();
            Transaction transaction = null;
            try{
                userEntity = new UserEntity();
                transaction = session.beginTransaction();
                userEntity.setName(name);
                userEntity.setPassword(password);
                userEntity.setGender(gender);
                userEntity.setAvatar(avatar);
                userEntity.setBestscore4(score4);
                userEntity.setBestscore5(score5);
                userEntity.setBestscore6(score6);
                session.save(userEntity);
                transaction.commit();
                System.out.println("添加成功！ " + userEntity.getName());
            }catch (HibernateException e) {
                if (transaction!=null) transaction.rollback();
                e.printStackTrace();
            }finally {
                session.close();
                factory.close();
            }
        }else {
            System.out.println(name+" 添加失败");
        }
        return userEntity;
    }


    public static void updateUser(String oldName,String newName,String avatar,int gender,String password){
        if (isUserNameAvailable(newName)){
            init();

            Transaction transaction = null;
            String queryString = "UPDATE UserEntity U set U.name = :newName, U.avatar = :avatar, U.gender = :gender, U.password = :password "
                    +"WHERE U.name = :oldName";
            try{Query query = session.createQuery(queryString);
                query.setParameter("newName",newName);
                query.setParameter("avatar",avatar);
                query.setParameter("gender",gender);
                query.setParameter("password",password);
                query.setParameter("oldName",oldName);
                int result = query.executeUpdate();
                transaction.commit();
                System.out.println("添加成功！ " + result);
            }catch (HibernateException e) {
                if (transaction!=null) transaction.rollback();
                e.printStackTrace();
            }finally {
                session.close();
                factory.close();
            }
        }else {
            System.out.println(" 失败");
        }
    }

    public static void main(String args[]){
        updateUser("xiaoqiang","xiaoqiang123","qweasdzxc",0,"gai");
    }
}
