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
import java.io.File;
import java.util.List;

/**
 * 用户表相关处理实现类
 *
 * @author Boollean
 */
@Repository(value = "userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    //获取日志记录器Logger，名字为本类类名
    private static Logger sLogger = LogManager.getLogger(UserDaoImpl.class);
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public List<UserEntity> getAllUsers() {
        sLogger.info("读取所有用户信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            list = query.list();
            sLogger.info("获取了 " + list.size() + " 条用户信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public int getBestScore4ByName(String name) {
        sLogger.info("读取 " + name + " 的4*4模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore4 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                sLogger.info("名字无效");
                return result;
            }
            result = (int) query.list().get(0);
            sLogger.info(name + "的4*4模式最高分为 " + result + " 分");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public int getBestScore5ByName(String name) {
        sLogger.info("读取 " + name + " 的5*5模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore5 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                sLogger.info("名字无效");
                return result;
            }
            result = (int) query.list().get(0);
            sLogger.info(name + "的5*5模式最高分为 " + result + " 分");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public int getBestScore6ByName(String name) {
        sLogger.info("读取 " + name + " 的6*6模式最高分");
        int result = 0;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.bestscore6 FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            if (query.list().isEmpty()) {
                sLogger.info("名字无效");
                return result;
            }
            result = (int) query.list().get(0);
            sLogger.info(name + "的6*6模式最高分为 " + result + " 分");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return result;
    }

    @Override
    public List<UserEntity> listBest100Users4() {
        sLogger.info("4*4模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore4 > 0 ORDER BY U.bestscore4 DESC ,U.name ASC";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            sLogger.info("共有 " + list.size() + " 条用户信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public List<UserEntity> listBest100Users5() {
        sLogger.info("5*5模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore5 > 0 ORDER BY U.bestscore5 DESC ,U.name ASC";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            sLogger.info("共有 " + list.size() + " 条用户信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public List<UserEntity> listBest100Users6() {
        sLogger.info("6*6模式最高分的前一百人");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.bestscore6 > 0 ORDER BY U.bestscore6 DESC ,U.name ASC";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql);
            query.setMaxResults(100);
            list = query.list();
            sLogger.info("共有 " + list.size() + " 条用户信息");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {

        }
        return list;
    }

    @Override
    public boolean isUserNameAvailable(String name) {
        sLogger.info("判断 " + name + " 是否在数据库内");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.name = ?1";
        try {
            Query query = session.createQuery(hql).setParameter(1, name);
            if (query.list().isEmpty()) {
                sLogger.info("名字可用");
                return true;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
        }
        sLogger.info("名字不可用");
        return false;
    }

    @Override
    public boolean addUser(UserEntity userEntity) {
        sLogger.info("加入一个用户到数据库");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(userEntity);
            transaction.commit();
            sLogger.info("添加用户成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            sLogger.info("添加用户失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public UserEntity getUserByName(String name) {
        sLogger.info("获取 " + name + " 的信息");
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "FROM UserEntity U WHERE U.name = :name";
        List<UserEntity> list = null;
        try {
            Query query = session.createQuery(hql).setParameter("name", name);
            list = query.list();
            if (list.isEmpty()) {
                sLogger.info("查无此人");
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
        sLogger.info("将原来存在的 " + oldName + " 用户信息更新");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U SET U.name = :newName, U.gender = :gender, U.password = :password WHERE U.name = :oldName";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("newName", newName);
            query.setParameter("gender", gender);
            query.setParameter("password", password);
            query.setParameter("oldName", oldName);
            query.executeUpdate();
            transaction.commit();
            sLogger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            sLogger.info("更新用户信息失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Transactional
    @Override
    public boolean updateUserDataByName(String name, int gender, String password) {
        sLogger.info("将原有的 " + name + " 用户信息更新");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U SET U.gender = :gender, U.password = :password "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("gender", gender);
            query.setParameter("password", password);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            sLogger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            sLogger.info("更新用户信息失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateBestScore4ByName(String name, int score) {
        sLogger.info("更新 " + name + " 用户的4*4模式最高分");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U SET U.bestscore4 = :score "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            sLogger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            sLogger.info("更新用户信息失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Transactional
    @Override
    public boolean updateBestScore5ByName(String name, int score) {
        sLogger.info("更新 " + name + " 用户的5*5模式最高分");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U SET U.bestscore5 = :score "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            sLogger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            sLogger.info("更新用户信息失败！");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateBestScore6ByName(String name, int score) {
        sLogger.info("更新 " + name + " 用户的6*6模式最高分");
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U SET U.bestscore6 = :score "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            sLogger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            sLogger.info("更新用户信息失败！");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean deleteUser(String name, String password) {
        sLogger.info("删除 " + name + " 的信息");
        String hql = "DELETE FROM UserEntity U WHERE U.name = :name AND U.password = :password";
        Transaction transaction = null;
        try {
            //取得session对象
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();//开启一个事务
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            query.setParameter("password", password);
            int result = query.executeUpdate();
            transaction.commit();
            if (result > 0) {
                sLogger.info("删除用户成功！");
                String storeDirectory = "D:" + File.separator + "Avatars";
                String filename = name + ".jpg";
                File file = new File(storeDirectory + File.separator + filename);
                if (file.exists()) {
                    file.delete();
                }
                return true;
            } else {
                sLogger.info("没有此用户！");
                return false;
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            sLogger.info("删除用户失败！");
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Transactional
    @Override
    public boolean updateAvatar(String name, String avatarPath) {
        sLogger.info("更新 " + name + " 用户的头像 " + avatarPath);
        //取得session对象
        session = sessionFactory.openSession();
        Transaction transaction = null;
        String hql = "UPDATE UserEntity U SET U.avatar = :path "
                + "WHERE U.name = :name";
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("path", avatarPath);
            query.setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
            sLogger.info("更新用户信息成功！");
            return true;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            sLogger.info("更新用户信息失败！");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public String getAvatarByName(String name) {
        sLogger.info("读取用户头像所在位置");
        String result = null;
        //取得session对象
        session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.avatar FROM UserEntity U WHERE U.name = :name";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("name", name);
            result = (String) query.list().get(0);
            sLogger.info(name + "的头像位置是 " + result);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
