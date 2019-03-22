package com.boollean.impl;

import com.boollean.dao.UserDao;
import com.boollean.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

@Repository(value = "USER")
@Transactional
public class UserDaoImpl implements UserDao {

    private static Configuration configuration;
    private static SessionFactory factory;
    private static Session session;

    @Override
    public void init() {
        //1.加载配置文件,设置配置文件,默认加载src目录下的hibernate.cfg.xml的配置文件
        configuration = new Configuration().configure();
        //2.建立SessionFactory对象
        factory = configuration.buildSessionFactory();
        //3.打开session对象
        session = factory.openSession();
    }

    @Override
    public List<UserEntity> getAllUser() {
        init();
        String hql = "FROM UserEntity";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.println("Name: " + userEntity.getName());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Test
    public void testGetAllUser() {
        getAllUser();
    }

    @Override
    public List<UserEntity> listBest100Users4() {
        init();
        String hql = "FROM UserEntity U WHERE U.bestscore4 > 0 ORDER BY U.bestscore4 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  4*4分数： " + userEntity.getBestscore4());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Test
    public void testListBest100Users4() {
        listBest100Users4();
    }

    @Override
    public List<UserEntity> listBest100Users5() {
        init();
        String hql = "FROM UserEntity U WHERE U.bestscore5 > 0 ORDER BY U.bestscore5 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  5*5分数： " + userEntity.getBestscore5());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Test
    public void testListBest100Users5() {
        listBest100Users5();
    }

    @Override
    public List<UserEntity> listBest100Users6() {
        init();
        String hql = "FROM UserEntity U WHERE U.bestscore6 > 0 ORDER BY U.bestscore6 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                UserEntity userEntity = (UserEntity) iterator.next();
                System.out.print("Name: " + userEntity.getName());
                System.out.println("  6*6分数： " + userEntity.getBestscore6());
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    @Test
    public void testListBest100Users6() {
        listBest100Users6();
    }

    @Override
    public boolean isUserNameAvailable(String name) {
        name = name.trim();
        if (name.isEmpty()) {
            System.out.println("名字为空，不可用");
            return false;
        }
        init();
        String queryString = "FROM UserEntity U WHERE U.name = ?1";
        try {
            Query query = session.createQuery(queryString).setParameter(1, name);
            if (query.list().isEmpty()) {
                System.out.println("可用");
                return true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
        System.out.println(name + "已存在");
        return false;
    }

    @Test
    public void testName() {
        isUserNameAvailable("小日");
        isUserNameAvailable("  ");
        isUserNameAvailable("小佳");
        isUserNameAvailable("");
    }


    @Override
    public boolean addUser(UserEntity userEntity) {
        if (isUserNameAvailable(userEntity.getName())) {
            init();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(userEntity);
                transaction.commit();
                System.out.println("添加成功！ " + userEntity.getName());
                return true;
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                close();
            }
        } else {
            System.out.println(userEntity.getName() + " 添加失败");
        }
        return false;
    }

    @Test
    public void testAddUser() {
        UserEntity u1 = new UserEntity("小河", "q7a41", 2, "d45sd1");
        UserEntity u2 = new UserEntity("小日", "q7a41", 2, "d45sd1");
        addUser(u1);
        addUser(u2);
    }

    @Override
    public UserEntity getUserByName(String name) {
        name = name.trim();
        if (name.isEmpty()) {
            System.out.println("名字为空，不可查询");
            return null;
        }
        init();
        String hql = "FROM UserEntity U WHERE U.name = ?1";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql).setParameter(1, name);
            list = query.list();
            if (list.isEmpty()) {
                System.out.println("查无此人");
                return null;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        UserEntity userEntity = list.get(0);
        System.out.println(userEntity.getName() + "  " + userEntity.getPassword() + "  " + userEntity.getGender() + "  " + userEntity.getAvatar());
        return userEntity;
    }

    @Test
    public void teatGetByName() {
        getUserByName("");
        getUserByName("小日");
        getUserByName("  ");
        getUserByName("小小");

    }

    @Override
    public boolean updateUserByName(String oldName, UserEntity userEntity) {

        if (isUserNameAvailable(userEntity.getName())) {
            init();
            Transaction transaction = null;
            String hql = "UPDATE UserEntity U set U.name = :newName, U.avatar = :avatar, U.gender = :gender, U.password = :password "
                            + "WHERE U.name = :oldName";

            String hql2 = "UPDATE UserEntity U set U = :user "
                    + "WHERE U.name = :oldName";
            try {
                transaction =  session.beginTransaction();
                Query query = session.createQuery(hql2);
                query.setParameter("user",userEntity);
//                query.setParameter("newName", userEntity.getName());
//                query.setParameter("avatar", userEntity.getAvatar());
//                query.setParameter("gender", userEntity.getGender());
//                query.setParameter("password", userEntity.getPassword());
                query.setParameter("oldName", oldName);

                int result = query.executeUpdate();
                transaction.commit();
                System.out.println("添加成功！ " + result);
                return true;
            } catch (HibernateException e) {
                if (transaction!=null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
                factory.close();
            }
        } else {
            System.out.println(" 失败");
        }
        return false;
    }

    @Test
    public void testUpdate() {
        UserEntity u = new UserEntity("小A", "q7a41", 2, "d45sd1");
        updateUserByName("小Q", u);

    }


    @Transactional
    @Override
    public boolean deleteUserByName(String name) {
        if (!name.trim().isEmpty()) {
            String hql = "DELETE FROM UserEntity U WHERE U.name = ?1";
            Transaction transaction = null;
            try {
                init();
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setParameter(1, name);
                query.executeUpdate();
                transaction.commit();
                System.out.println("成功");
                return true;
            } catch (HibernateException e) {
                if (transaction!=null) transaction.rollback();
                e.printStackTrace();
            } finally {
                close();
            }
        }
        return false;
    }

    @Test
    public void testDelete() {
        deleteUserByName("小河");
    }

    @Override
    public void close() {
        session.close();
        factory.close();
    }
}
