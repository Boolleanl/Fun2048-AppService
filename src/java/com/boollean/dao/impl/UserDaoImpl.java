package com.boollean.dao.impl;

import com.boollean.dao.UserDao;
import com.boollean.entity.UserEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;

    /**
     * 查询所有用户
     *
     * @return 集合所有用户的列表
     */
    @Override
    public List<UserEntity> getAllUsers() {
        //取得session对象
        session = sessionFactory.getCurrentSession();
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

        }
        return list;
    }

    @Override
    public int getBestScore4ByName(String name) {
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore4 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                System.out.println("4*4模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("4*4模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public int getBestScore5ByName(String name) {
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore5 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                System.out.println("5*5模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("5*5模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public int getBestScore6ByName(String name) {
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore6 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                System.out.println("6*6模式最高分： " + result);
                return result;
            }
            result = (int) query.list().get(0);
            System.out.println("6*6模式最高分： " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    /**
     * 查询4*4模式分数最高的前一百个用户
     *
     * @return
     */
    @Override
    public List<UserEntity> listBest100Users4() {
        //取得session对象
        session = sessionFactory.getCurrentSession();
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

        }
        return list;
    }

    /**
     * 查询5*5模式分数最高的前一百个用户
     *
     * @return
     */
    @Override
    public List<UserEntity> listBest100Users5() {
        //取得session对象
        session = sessionFactory.getCurrentSession();
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

        }
        return list;
    }

    /**
     * 查询6*6模式分数最高的前一百个用户
     *
     * @return
     */
    @Override
    public List<UserEntity> listBest100Users6() {
        //取得session对象
        session = sessionFactory.getCurrentSession();
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

        }
        return list;
    }

    /**
     * 检查用户名是否可用，未曾在数据库出现且不为空则可用
     *
     * @return
     */
    @Override
    public boolean isUserNameAvailable(String name) {
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String queryString = "FROM UserEntity U WHERE U.name = ?1";
        try {
            Query query = session.createQuery(queryString).setParameter(1, name);
            if (query.list().isEmpty()) {
                return true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    /**
     * 加入一个用户到数据库中
     *
     * @return
     */
    @Override
    public boolean addUser(UserEntity userEntity) {
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(userEntity);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * 通过姓名来查询用户
     *
     * @return
     */
    @Override
    public UserEntity getUserByName(String name) {
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.name = ?1";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql).setParameter(1, name);
            list = query.list();
            if (list.isEmpty()) {
                return null;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        UserEntity userEntity = list.get(0);
        System.out.println(userEntity.getName());
        return userEntity;
    }

    /**
     * 通过姓名来更新用户信息
     *
     * @param oldName    更改信息前的用户姓名
     * @param userEntity 新的用户信息生成的用户实体
     * @return
     */
    @Transactional
    @Override
    public boolean updateUserByName(String oldName, UserEntity userEntity) {
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql2 = "UPDATE UserEntity U set U = :user WHERE U.name = :oldName";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql2);
            query.setParameter("user", userEntity);
            query.setParameter("oldName", oldName);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * 更新用户4*4模式的最高记录
     *
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    @Override
    public boolean updateBestScore4ByName(String name, int score) {
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.bestscore4 = :score "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * 更新用户5*5模式的最高记录
     *
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    @Transactional
    @Override
    public boolean updateBestScore5ByName(String name, int score) {
            //取得session对象
            session = sessionFactory.openSession();
            Transaction transaction = null;
            String hql = "UPDATE UserEntity U set U.bestscore5 = :score "
                    + "WHERE U.name = :name";
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setParameter("score", score);
                query.setParameter("name", name);
                query.executeUpdate();
                transaction.commit();
                return true;
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        return false;
    }

    /**
     * 更新用户6*6模式的最高记录
     *
     * @param name  用户名
     * @param score 分数
     * @return 结果成功与否
     */
    @Transactional
    @Override
    public boolean updateBestScore6ByName(String name, int score) {
            //取得session对象
            session = sessionFactory.openSession();
            Transaction transaction = null;
            String hql = "UPDATE UserEntity U set U.bestscore6 = :score "
                    + "WHERE U.name = :name";
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery(hql);
                query.setParameter("score", score);
                query.setParameter("name", name);
                query.executeUpdate();
                transaction.commit();
                return true;
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        return false;
    }

    @Transactional
    @Override
    public boolean updateUserDataByName(String oldName, String newName, int gender, String password, String avatar) {
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.name = :newName, U.avatar = :avatar, U.gender = :gender, U.password = :password "
                + "WHERE U.name = :oldName";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("newName", newName);
            query.setParameter("avatar", avatar);
            query.setParameter("gender", gender);
            query.setParameter("password", password);
            query.setParameter("oldName", oldName);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * 通过姓名来删除用户
     *
     * @param name 用户姓名
     * @return
     */
    @Transactional
    @Override
    public boolean deleteUserByName(String name) {
        String hql = "DELETE FROM UserEntity U WHERE U.name = ?1";
        Transaction transaction = null;
        try {
            //取得session对象
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter(1, name);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
