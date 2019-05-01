package com.boollean.dao.impl;

import com.boollean.dao.UserDao;
import com.boollean.entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @author Boollean
 */
@Repository(value = "userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    //获取日志记录器Logger，名字为本类类名
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public List<UserEntity> getAllUsers() {
        logger.info("读取所有用户信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            list = query.list();
            logger.info("获取了 "+list.size()+" 条用户信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public int getBestScore4ByName(String name) {
        logger.info("读取 " + name + " 的4*4模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore4 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                logger.info("名字无效");
                return result;
            }
            result = (int) query.list().get(0);
            logger.info(name + "的4*4模式最高分为 "+result+" 分");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public int getBestScore5ByName(String name) {
        logger.info("读取 " + name + " 的5*5模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore5 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                logger.info("名字无效");
                return result;
            }
            result = (int) query.list().get(0);
            logger.info(name + "的5*5模式最高分为 "+result+" 分");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public int getBestScore6ByName(String name) {
        logger.info("读取 " + name + " 的6*6模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore6 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                logger.info("名字无效");
                return result;
            }
            result = (int) query.list().get(0);
            logger.info(name + "的6*6模式最高分为 "+result+" 分");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public List<UserEntity> listBest100Users4() {
        logger.info("4*4模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore4 > 0 ORDER BY U.bestscore4 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            logger.info("共有 "+list.size()+" 条用户信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public List<UserEntity> listBest100Users5() {
        logger.info("5*5模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore5 > 0 ORDER BY U.bestscore5 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            logger.info("共有 "+list.size()+" 条用户信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public List<UserEntity> listBest100Users6() {
        logger.info("6*6模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore6 > 0 ORDER BY U.bestscore6 DESC ,U.name asc";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            logger.info("共有 "+list.size()+" 条用户信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public boolean isUserNameAvailable(String name) {
        logger.info("判断 " + name + " 是否在数据库内");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String queryString = "FROM UserEntity U WHERE U.name = ?1";
        try {
            Query query = session.createQuery(queryString).setParameter(1, name);
            if (query.list().isEmpty()) {
                logger.info("名字可用");
                return true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        logger.info("名字不可用");
        return false;
    }

    @Override
    public boolean addUser(UserEntity userEntity) {
        logger.info("加入一个用户到数据库");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(userEntity);
            transaction.commit();
            logger.info("添加用户成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("添加用户成功！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public UserEntity getUserByName(String name) {
        logger.info("获取 " + name + " 的信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.name = :name";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql).setParameter("name", name);
            list = query.list();
            if (list.isEmpty()) {
                logger.info("查无此人");
                return null;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        UserEntity userEntity = list.get(0);
        return userEntity;
    }

    @Override
    public boolean updateUserByName(String oldName, String newName, int gender, String password) {
        logger.info("将原来存在的 " + oldName + " 用户信息更新");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql2 = "UPDATE UserEntity U set U.name = :newName, U.gender = :gender, U.password = :password WHERE U.name = :oldName";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql2);
            query.setParameter("newName", newName);
            query.setParameter("gender", gender);
            query.setParameter("password", password);
            query.setParameter("oldName", oldName);
            query.executeUpdate();
            transaction.commit();
            logger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("更新用户信息失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Transactional
    @Override
    public boolean updateUserDataByName(String name, int gender, String password) {
        logger.info("将原有的 " + name + " 用户信息更新");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.gender = :gender, U.password = :password "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("gender", gender);
            query.setParameter("password", password);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            logger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("更新用户信息失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateBestScore4ByName(String name, int score) {
        logger.info("更新 " + name + " 用户的4*4模式最高分");
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
            logger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("更新用户信息失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Transactional
    @Override
    public boolean updateBestScore5ByName(String name, int score) {
        logger.info("更新 " + name + " 用户的5*5模式最高分");
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
            logger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("更新用户信息失败！");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateBestScore6ByName(String name, int score) {
        logger.info("更新 " + name + " 用户的6*6模式最高分");
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
            logger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("更新用户信息失败！");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteUser(String name,String password) {
        logger.info("删除 " + name + " 的信息");
        String hql = "DELETE FROM UserEntity U WHERE U.name = :name AND U.password = :password";
        Transaction transaction = null;
        try {
            //取得session对象
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setParameter("password", password);
            int result = query.executeUpdate();
            transaction.commit();
            if(result>0){
                logger.info("删除用户成功！");
                return true;
            }else {
                logger.info("没有此用户！");
                return false;
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("删除用户失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Transactional
    @Override
    public boolean updateAvatar(String name, String avatarPath) {
        logger.info("更新 " + name + " 用户的头像 " + avatarPath);
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U set U.avatar = :path "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("path", avatarPath);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            logger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.info("更新用户信息失败！");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
